package com.kimmich.util;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 自定义时间格式转换器
 * 格式：yyyy-MM-dd
 * @author ：Kimmich
 * @date ：Created in 2023/6/28 16:31
 */
public class LocalDateConverter implements Converter<LocalDate> {

    @Override
    public Class<LocalDate> supportJavaTypeKey() {
        return LocalDate.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }


    @Override
    public LocalDate convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        return LocalDate.parse(cellData.getStringValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public WriteCellData<String> convertToExcelData(LocalDate value, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        return new WriteCellData<>(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

}