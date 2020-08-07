package com.tissaniffo.nations.utils;

import java.io.File;

public class FileUtils {

    public static String getFileNameWithoutExtension(File file){
        String fileName = "";
        try{
            if (file != null && file.exists()) {
                String name = file.getName();
                fileName = name.replaceFirst("[.][^.]+$", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            fileName = "";
        }
        return fileName;
    }

}
