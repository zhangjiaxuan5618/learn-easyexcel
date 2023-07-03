package com.kimmich.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Kimmich
 * @date ：Created in 2023/7/3 14:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {
    private Integer code;
    private String msg;
    private Object data;

    public static ResultVO success() {
        return success("操作成功");
    }

    public static ResultVO success(String msg) {
        return success(msg, null);
    }

    public static ResultVO success(Object data) {
        return success("操作成功", data);
    }

    public static ResultVO success(String msg, Object data) {
        return new ResultVO(200, msg, data);
    }

    public static ResultVO failure() {
        return failure("操作失败");
    }

    public static ResultVO failure(String msg) {
        return failure(msg, null);
    }

    public static ResultVO failure(Object data) {
        return failure("操作失败", data);
    }

    public static ResultVO failure(String msg, Object data) {
        return new ResultVO(500, msg, data);
    }
}