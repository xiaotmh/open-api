package com.tianminghao.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Athena
 * @date: 2020/9/25 17:00
 * @description: 返回前台的结果实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private boolean status;
    private Object message;

}
