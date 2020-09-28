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

    /**
     * 返回状态
     */
    private boolean status;

    /**
     * 返回的信息或数据
     */
    private Object message;

}
