package com.example.spvenka.jenieofimages.util;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileManager {

    public static List<String> readItems(Context context) {
        File filesDir = context.getFilesDir();
        File todofile = new File(filesDir, "todo.txt");
        List<String> props = null;
        try {
            props = new ArrayList<String>(FileUtils.readLines(todofile));
        } catch (IOException e) {
            props = new ArrayList<String>();
        }
        return props;
    }

    public static void writeItems(Context context, List<String> props) {
        File filesDir = context.getFilesDir();
        File todofile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todofile, props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
