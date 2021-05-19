
package com.test_task.exchanges.dto.giphy;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data",
        "pagination",
        "meta"
})
public class Gif {

    @JsonProperty("data")
    private List<Datum> data = null;
}
