package com.tianminghao.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 17:00
 * @description: 返回前台的表数据实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableData<T> implements Serializable {

    private Integer code = 0;
    private String msg = "";
    private Long count;
    private List<T> data;

}
