package com.base.library.database.room;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * @author reber
 */
public class ConversionFactory {
    /**
     * 将日期转换成时间戳
     *
     * @param date 日期date：2019-10-10
     * @return 返回将date转换的时间戳：1523142434242
     */
    @TypeConverter
    public static Long fromDateToLong(Date date) {
        return date == null ? null : date.getTime();
    }

    /**
     * 将时间戳转换成date格式
     *
     * @param value 时间戳：1523142434242
     * @return 返回 日期date：2019-10-10
     */
    @TypeConverter
    public static Date fromLongToDate(Long value) {
        return value == null ? null : new Date(value);
    }
}
