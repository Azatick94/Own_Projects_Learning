
#%% Import libs
import pandas as pd
import numpy as np
import os
import matplotlib.pyplot as plt
from tqdm import tqdm
import matplotlib.patches as patches

from mask_RCNN.mrcnn.utils import Dataset
from mask_RCNN.mrcnn.config import Config
from mask_RCNN.mrcnn.model import MaskRCNN



#%% Load dataset
print('Loading dataset !!!')
df_train = pd.read_csv("global-wheat-detection/train.csv")  

df_train['bbox'] = df_train['bbox'].apply(lambda x: x[1:-1].split(","))
df_train['x_min'] = df_train['bbox'].apply(lambda x: x[0]).astype('float32')
df_train['y_min'] = df_train['bbox'].apply(lambda x: x[1]).astype('float32')
df_train['width'] = df_train['bbox'].apply(lambda x: x[2]).astype('float32')
df_train['height'] = df_train['bbox'].apply(lambda x: x[3]).astype('float32')
df_train = df_train[['image_id','x_min', 'y_min', 'width', 'height']]
df_train["x_max"] = df_train.apply(lambda col: col.x_min + col.width, axis=1)
df_train["y_max"] = df_train.apply(lambda col: col.y_min + col.height, axis = 1)
df_train['image_id'] = df_train['image_id']+'.jpg'




# %% LETS CREATE WHEAT DATASET CLASS
print('Creating Wheat Dataset !!!')
class WheatDataset(Dataset):
	# load the dataset definitions
	def load_dataset(self, dataset_dir, is_train=True):
		# define one class
		self.add_class("dataset", 1, "wheat")
		# define data locations
		images_dir = dataset_dir
		# find all images
		for filename in os.listdir(images_dir):
			# extract image id
			image_id = filename[:-4]
			img_path = images_dir +'\\'+ filename
			# add to dataset
			self.add_image('dataset', image_id=image_id, path=img_path)
			
	def load_mask(self, image_id):
		# firstable it is needed to find filename for defined image_id
		filename = self.image_info[image_id]['id']+'.jpg'
		
		# extracting bounding boxes 
		df_id_filtered = df_train[df_train['image_id']==filename]

		boxes = list()
		print('Iteration through the bounding boxes !!!')
		for i, row in df_id_filtered.iterrows():
			x_min = int(row['x_min'])
			y_min = int(row['y_min'])
			x_max = int(row['x_max'])
			y_max = int(row['y_max'])
			coors = [x_min, y_min, x_max, y_max]
			boxes.append(coors)
		
		# widht and height are fixes for images 
		width = 1024
		height = 1024

		print('Creating Mask array !!!')
		# create one array for all masks, each on a different channel
		masks = np.zeros([height, width, len(boxes)], dtype='uint8')
		# create masks
		class_ids = list()
		for i in range(len(boxes)):
			box = boxes[i]
			row_s, row_e = box[1], box[3]
			col_s, col_e = box[0], box[2]
			masks[row_s:row_e, col_s:col_e, i] = 1
			class_ids.append(self.class_names.index('wheat'))
		return masks, np.asarray(class_ids, dtype='int32'), boxes 
 
	# load an image reference
	def image_reference(self, image_id):
		info = self.image_info[image_id]
		return info['path']



# define a configuration for the model
class WheatConfig(Config):
	# Give the configuration a recognizable name
	NAME = "wheat_cfg"
	# Number of classes (background + wheat)
	NUM_CLASSES = 1 + 1
	# Number of training steps per epoch, which is equal to total number of training images
	STEPS_PER_EPOCH = 3422
 


#%%  Preparing train and test datasets
train_set = WheatDataset()
train_set.load_dataset(dataset_dir=r'global-wheat-detection\train', is_train=True)
train_set.prepare()
 
test_set = WheatDataset()
test_set.load_dataset(dataset_dir=r'global-wheat-detection\test', is_train=False)
test_set.prepare()


#%% Plotting one image

# train set
image_id = 10
image = train_set.load_image(image_id)
mask, class_ids, boxes = train_set.load_mask(image_id)

# plot image
fig,ax = plt.subplots(1, figsize=(8,8))
plt.imshow(image)

for i in range(mask.shape[2]):
	rect = patches.Rectangle((boxes[i][0],boxes[i][1]),
	boxes[i][2]-boxes[i][0],boxes[i][3]-boxes[i][1],
	linewidth=2,edgecolor='r',facecolor='none')	
	ax.add_patch(rect)

# and lets visualize one mask
plt.imshow(mask[:, :, 0], cmap='gray', alpha=0.5)
plt.show()

# %% Visualizing sample 3 images


fig, ax = plt.subplots(ncols=3, nrows=1, figsize=(20,20))
for i in range(3):
	image = train_set.load_image(i)
	mask, class_ids, boxes = train_set.load_mask(i)
	ax[i].imshow(image)

	for j in range(mask.shape[2]):
		rect = patches.Rectangle((boxes[j][0],boxes[j][1]),
		boxes[j][2]-boxes[j][0],boxes[j][3]-boxes[j][1],
		linewidth=2,edgecolor='r',facecolor='none')	
		ax[i].add_patch(rect)

	ax[i].imshow(mask[:, :, 0], cmap='gray', alpha=0.5)

# %% CREATING THE MODEL

# prepare config
config = WheatConfig()

# define the model
model = MaskRCNN(mode='training', model_dir='./', config=config)

# load weights (mscoco)
model.load_weights('mask_rcnn_coco.h5', by_name=True, 
					exclude=["mrcnn_class_logits", "mrcnn_bbox_fc",  "mrcnn_bbox", "mrcnn_mask"])

# train weights (output layers or 'heads')
model.train(train_set, test_set, learning_rate=config.LEARNING_RATE, 
		epochs=5, layers='heads')





# %%
