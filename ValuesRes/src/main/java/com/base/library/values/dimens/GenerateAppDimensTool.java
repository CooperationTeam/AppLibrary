package com.base.library.values.dimens;

import com.base.library.values.GenerateFileTool;

import static com.base.library.values.GenerateFileTool.DEFAULT_RES_VALUE_PATH;

/**
 * @author reber
 * <p>
 * 生成默认的dp/sp数据
 */
public class GenerateAppDimensTool {

    private GenerateAppDimensTool() {
        throw new ExceptionInInitializerError(
                "GenerateAppDimensTool can't created,this is a tool for generating dimens.xml file");
    }

    public static void main(String[] args) {
        generateDefaultDimens(400, 50, 4);
    }

    private static void generateDefaultDimens(int dpCount, int spCount, int spStartValue) {
        StringBuilder sBuilder = GenerateFileTool.getStartStringBuilder();
        if (dpCount > 0) {
            //    <dimen name="dp_0">0dp</dimen>
            for (int value = 0; value <= dpCount; value++) {
                insertDpValueLine(sBuilder, value, "dp");
            }
        }

        if (spCount > 0) {
            sBuilder.append("\n");
            //    <dimen name="sp_0">0sp</dimen>
            for (int value = spStartValue; value <= spCount; value++) {
                insertDpValueLine(sBuilder, value, "sp");
            }
        }

        String content = GenerateFileTool.getEndStringBuilder(sBuilder).toString();
        GenerateFileTool.saveContentToFile(DEFAULT_RES_VALUE_PATH, "dimens.xml", content);
    }

    /**
     * <dimen name="dp_0">0dp</dimen>
     * <dimen name="sp_0">0sp</dimen>
     *
     * @param value     dp/sp 的值
     * @param valueUnit 区分dp/sp
     */
    private static void insertDpValueLine(StringBuilder stringBuilder, int value, String valueUnit) {
        stringBuilder.append("    <dimen name=\"")
                .append(valueUnit)
                .append("_")
                .append(value)
                .append("\">")
                .append(value)
                .append(valueUnit)
                .append("</dimen>")
                .append("\n");
    }
}
