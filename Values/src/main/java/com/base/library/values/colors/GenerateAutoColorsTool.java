package com.base.library.values.colors;

import com.base.library.values.GenerateFileTool;

import java.util.HashMap;

import static com.base.library.values.GenerateFileTool.DEFAULT_RES_VALUE_PATH;

/**
 * @author reber
 */
public class GenerateAutoColorsTool {

    private GenerateAutoColorsTool() {
        throw new ExceptionInInitializerError(
                "GenerateAutoColorsTool can't created,this is a tool for generating dimens.xml file");
    }

    public static void main(String[] args) {
        generateDefaultColor();
    }

    private static HashMap<String, String> getDefaultColors() {
        HashMap<String, String> mColors = new HashMap<>();
        mColors.put("appWhite", "#FFFFFF");
        mColors.put("appBlack", "#000000");
        mColors.put("appRed", "#F14880");
        mColors.put("appGray", "#999999");
        return mColors;
    }

    private static void generateDefaultColor() {
        HashMap<String, String> mColors = getDefaultColors();

        // 如果没有默认的Dimens.xml,不设置值
        if (!GenerateFileTool.isFileExist(DEFAULT_RES_VALUE_PATH)) {
            return;
        }

        final StringBuilder sBuilder = GenerateFileTool.getStartStringBuilder();

        for (String key : mColors.keySet()) {
            insertColorLine(sBuilder, key, mColors.get(key));
        }

        String content = GenerateFileTool.getEndStringBuilder(sBuilder).toString();
        GenerateFileTool.saveContentToFile(DEFAULT_RES_VALUE_PATH, "colors.xml", content);
    }

    /**
     * <color name="appWhite">#FFFFFF</color>
     */
    private static void insertColorLine(StringBuilder stringBuilder, String colorName, String colorValue) {
        stringBuilder
                .append("    <color name=\"")
                .append(colorName)
                .append("\">")
                .append(colorValue)
                .append("</color>")
                .append("\n");
    }
}
