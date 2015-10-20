package com.example.spvenka.jenieofimages.util;

import android.content.Context;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParamManager {

    private static final String FILENAME = "settings.txt";

    public static void updateParam(Context context,
                                   int id,
                                   String newValue) throws IOException {
        if (newValue.isEmpty())
            return;
        ObjectMapper objectMapper = new ObjectMapper();
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, FILENAME);
        Params params = null;
        if (file.exists()) {
            params = objectMapper.readValue(file, Params.class);
        } else {
            params = new Params();
        }
        params.params.put(id, newValue);
        FileUtils.write(file, objectMapper.writeValueAsString(params));
    }

    public static Map<Integer, String> getParams(Context context) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, FILENAME);
        if (!file.exists())
            return new HashMap<>();
        Params params = objectMapper.readValue(file, Params.class);
        return params.params;
    }
}
