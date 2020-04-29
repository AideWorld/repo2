package com.atguigu.springcloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yhj
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonReasult<T> {

    private Integer code;

    private String message;

    private T data;

    public CommonReasult(Integer code, String message) {
        this(code, message, null);
    }

}
