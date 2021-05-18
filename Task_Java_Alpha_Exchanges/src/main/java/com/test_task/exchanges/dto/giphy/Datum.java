
package com.test_task.exchanges.dto.giphy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Datum {
    @JsonProperty("id")
    private String id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("bitly_gif_url")
    private String bitlyGifUrl;
    @JsonProperty("bitly_url")
    private String bitlyUrl;
    @JsonProperty("embed_url")
    private String embedUrl;
    @JsonProperty("username")
    private String username;
    @JsonProperty("source")
    private String source;
    @JsonProperty("title")
    private String title;
}
