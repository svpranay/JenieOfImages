package com.example.spvenka.jenieofimages.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Params {
    public Map<Integer, String> params;

    @JsonCreator
    public Params(@JsonProperty("params") Map<Integer, String> params) {
        this.params = params;
    }

    public Params() {
        this.params = new HashMap<>();
    }
}
