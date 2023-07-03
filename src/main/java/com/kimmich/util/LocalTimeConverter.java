package com.kimmich.util;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * 自定义时间格式转换器
 * 格式：HH:mm:ss
 * @author ：Kimmich
 * @date ：Created in 2023/6/28 16:31
 */
public class LocalTimeConverter implements Converter<LocalTime> {
    @Override
    public Class<LocalTime> supportJavaTypeKey() {
        return LocalTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }


    @Override
    public LocalTime convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        return LocalTime.parse(cellData.getStringValue(), DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    @Override
    public WriteCellData<String> convertToExcelData(LocalTime value, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        return new WriteCellData<>(value.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

}