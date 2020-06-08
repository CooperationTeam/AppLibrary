package com.base.library.values.dimens;

import com.base.library.values.GenerateFileTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

import static com.base.library.values.GenerateFileTool.BASE_RES_PATH;
import static com.base.library.values.GenerateFileTool.DEFAULT_RES_VALUE_DIMENS_PATH;

/**
 * @author reber
 */
public class GenerateAutoDimensTool {

    private final static int[] SW_VALUES = {384, 411};
    private final static int DEFAULT_SW_VALUE = 360;

    private GenerateAutoDimensTool() {
        throw new ExceptionInInitializerError(
                "GenerateAutoDimensTool can't created,this is a tool for generating dimens.xml file");
    }

    public static void main(String[] args) {
        for (int swValue : SW_VALUES) {
            generateDimens(swValue);
        }
    }

    private static void generateDimens(int swValue) {
        // 如果没有默认的Dimens.xml,不设置值
        if (!GenerateFileTool.isFileExist(DEFAULT_RES_VALUE_DIMENS_PATH) || DEFAULT_SW_VALUE == swValue) {
            return;
        }
        StringBuilder sBuilder = new StringBuilder();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File(DEFAULT_RES_VALUE_DIMENS_PATH)));
            while (true) {
                String dimenLine = reader.readLine();
                if (dimenLine.contains("</dimen>")) {
                    //<dimen name="dp_400">400dp</dimen>
                    double defaultDimenValue = getDefaultDimenValue(dimenLine);
                    sBuilder.append(getStartDimenContent(dimenLine)) //<dimen name="dp_353">
                            .append(getNewDimenValue(defaultDimenValue, swValue)) //16.66
                            .append(getDimenUnit(dimenLine)) // dp or sp
                            .append("</dimen>") //</dimen>
                            .append("\n");
                } else {
                    sBuilder.append(dimenLine)
                            .append("\n");
                }
                if (dimenLine.contains("</resources>")) {
                    break;
                }
            }
            // values-sw300dp
            String fileParentPath = BASE_RES_PATH + File.separator + GenerateFileTool.getDimensAutoParentPath(swValue);
            GenerateFileTool.saveContentToFile(fileParentPath, "dimens.xml", sBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 如：<dimen name="dp_353">
     */
    private static String getStartDimenContent(String dimenLine) {
        return dimenLine.substring(0, dimenLine.indexOf(">") + 1);
    }

    private static double getDefaultDimenValue(String dimenLine) {
        //<dimen name="dp_353">
        String dimenLineStart = dimenLine.substring(0, dimenLine.indexOf(">") + 1);
        //353
        String dimenValue = dimenLine.substring(dimenLineStart.length(), dimenLine.indexOf("</") - 2);
        return Double.parseDouble(dimenValue);
    }

    private static String getNewDimenValue(double oldDimenValue, int swValue) {
        double newDimenValue = swValue * oldDimenValue / DEFAULT_SW_VALUE;
        return String.format(Locale.CHINA, "%.2f", newDimenValue);
    }

    private static String getDimenUnit(String dimenLine) {
        return dimenLine.substring(dimenLine.indexOf("</") - 2,
                dimenLine.indexOf("</"));
    }
}
