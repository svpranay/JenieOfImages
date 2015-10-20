package com.example.spvenka.jenieofimages.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {
 responseData: {
 results: [],
 cursor: {}
 },
 responseDetails: null,
 responseStatus: 200
 }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GImageResponse {

    public GImageResponseData responseData;
    public int responseStatus;

    @JsonCreator
    public GImageResponse(@JsonProperty("responseData") GImageResponseData gImageResponseData,
                          @JsonProperty("responseStatus") int responseStatus) {
        this.responseData = gImageResponseData;
        this.responseStatus = responseStatus;
    }

}
