package com.base.library.values;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author reber
 */
public class GenerateFileTool {

    public final static String BASE_RES_PATH = System.getProperty("user.dir") + "/Values/src/main/res";

    public final static String DEFAULT_RES_VALUE_PATH = BASE_RES_PATH + File.separator + "values";

    public final static String DEFAULT_RES_VALUE_DIMENS_PATH = DEFAULT_RES_VALUE_PATH + File.separator + "dimens.xml";

    public static StringBuilder getStartStringBuilder() {
        return new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
                .append("\n")
                .append("<resources>")
                .append("\n");
    }

    public static StringBuilder getEndStringBuilder(StringBuilder stringBuilder) {
        stringBuilder
                .append("</resources>")
                .append("\n");
        return stringBuilder;
    }

    public static String getDimensAutoParentPath(int swValue) {
        //parent path: "../main/res/values-sw300dp"
        return "values-sw" + swValue + "dp";
    }

    public static void saveContentToFile(String fileParentPath, String childPath, String content) {
        if (createParentFile(fileParentPath)) {
            if (createChildFile(fileParentPath, childPath)) {
                writeToFile(fileParentPath, childPath, content);
            }
        }
    }

    private static boolean createParentFile(String fileParentPath) {
        File parentFile = new File(fileParentPath);
        if (!parentFile.exists()) {
            return parentFile.mkdir() || parentFile.mkdir();
        }
        return true;
    }

    private static boolean createChildFile(String parentPath, String childPath) {
        File childFile = new File(parentPath, childPath);
        if (!childFile.exists()) {
            try {
                return childFile.createNewFile() || childFile.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    private static void writeToFile(String fileParentPath, String childPath, String content) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(fileParentPath, childPath)));
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean isFileExist(String filePath) {
        return new File(filePath).exists();
    }
}
