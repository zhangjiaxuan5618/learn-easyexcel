package com.kimmich.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Kim
 * @since 2023-06-29
 */
@Data
@EqualsAndHashCode
public class Bayernn implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "name", type = IdType.ASSIGN_ID)


    @ExcelProperty("员工姓名")
    private String name;
    @ExcelProperty("员工工号")
    private Integer id;
    @ExcelProperty("员工年龄")
    private Integer age;


}
