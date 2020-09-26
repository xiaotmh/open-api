package com.tianminghao.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
*
*  @author Athena
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1601031762351L;


    /**
    * 主键
    * 主键
    * isNullAble:0
    */
    private Integer id;

    /**
    * 参数名
    * isNullAble:1
    */
    private String name;

    /**
    * 参数介绍
    * isNullAble:1
    */
    private String description;

    /**
    * 状态 1 启用,0 禁用
    * isNullAble:1
    */
    private Integer state;



}
