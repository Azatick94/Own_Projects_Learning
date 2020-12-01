

import networkx as nx
import matplotlib.pyplot as plt

dire = r"d:\Users\burkaz\Desktop\homework\week_3"

txt_file = open(r"p2p-Gnutella08.txt", "r")
txt_file = txt_file.readlines()
new_lst = txt_file[4:]

fromNodeId = [int(x.split("\t")[0]) for x in new_lst]
ToNodeId = [int(x.split("\t")[1].split("\n")[0]) for x in new_lst]

G = nx.Graph(name="Social Network")
for i in range(max(ToNodeId)):
    G.add_nodes_from([i])
for k in range(len(fromNodeId)):
    G.add_edges_from([(fromNodeId[k], ToNodeId[k] )])

degrees = G.degree
degree_values = list(dict(degrees).values())
counting = len(list(dict(degrees).values()))
labels_lst = []
for i in range(0, counting):
    dic_key = list(dict(degrees).keys())[i]
    dic_values = list(dict(degrees).values())[i]

    if dic_values > 15:
        labels_lst.append(dic_key)
    else:
        labels_lst.append("")


node_size = [x/max(degree_values)*300 for x in degree_values]
node_color = [x/max(degree_values)*1 for x in degree_values]
layout = nx.fruchterman_reingold_layout(G)

plt.figure(figsize=(12,9))

vmin = min(node_color)
vmax = max(node_color)
cmap = plt.cm.coolwarm

nx.draw(G, pos=layout, with_labels=False, arrows=True, node_size=node_size, width=0.3,edge_color="black",
        node_color=node_color, marker_edge_color="black", cmap=cmap, vmin=vmin, vmax=vmax)
plt.title("SOCIAL NETWORK")

sm = plt.cm.ScalarMappable(cmap=cmap, norm=plt.Normalize(vmin=vmin, vmax=vmax))
sm.set_array([])
cbar = plt.colorbar(sm)

plt.savefig("test_graph.png")


