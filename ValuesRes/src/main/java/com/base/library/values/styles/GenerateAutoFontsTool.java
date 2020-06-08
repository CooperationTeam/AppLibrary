package com.base.library.values.styles;

import com.base.library.values.GenerateFileTool;

import java.util.HashMap;
import java.util.function.BiConsumer;

import static com.base.library.values.GenerateFileTool.DEFAULT_RES_VALUE_PATH;

/**
 * @author reber
 */
public class GenerateAutoFontsTool {

    private GenerateAutoFontsTool() {
        throw new ExceptionInInitializerError(
                "GenerateAutoFontsTool can't created,this is a tool for generating dimens.xml file");
    }

    public static void main(String[] args) {
        generateDefaultFontStyles();
    }

    private static HashMap<String, String> getDefaultFontStyles() {
        HashMap<String, String> mFontStyles = new HashMap<>();
        mFontStyles.put("RobotoBold", "roboto_bold");
        mFontStyles.put("RobotoItalic", "roboto_italic");
        mFontStyles.put("RobotoRegular", "roboto_regular");
        return mFontStyles;
    }

    private static void generateDefaultFontStyles() {
        HashMap<String, String> mFontStyles = getDefaultFontStyles();

        // 如果没有默认的Dimens.xml,不设置值
        if (!GenerateFileTool.isFileExist(DEFAULT_RES_VALUE_PATH)) {
            return;
        }

        final StringBuilder sBuilder = GenerateFileTool.getStartStringBuilder();

        mFontStyles.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String fontName, String fontValue) {
                insertFontStyleItem(sBuilder, fontName, fontValue);
            }
        });

        String content = GenerateFileTool.getEndStringBuilder(sBuilder).toString();
        GenerateFileTool.saveContentToFile(DEFAULT_RES_VALUE_PATH, "font_styles.xml", content);
    }

    /**
     * <color name="appWhite">#FFFFFF</color>
     */
    private static void insertFontStyleItem(StringBuilder stringBuilder, String fontName, String fontValue) {
        // <style name="RobotoBold">
        stringBuilder
                .append("    <style name=\"")
                .append(fontName)
                .append("\">")
                .append("\n");

        // <item name="android:fontFamily">@font/roboto_bold</item>
        stringBuilder
                .append("        <item name=\"android:fontFamily\">@font/")
                .append(fontValue).append("</item>").append("\n");

        // </style>
        stringBuilder
                .append("    </style>")
                .append("\n");
    }
}
