package com.example.spvenka.jenieofimages.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GImageResponseData {

    public List<GImage> results;

    @JsonCreator public GImageResponseData(@JsonProperty("results") List<GImage> results) {
        this.results = results;
    }
}
