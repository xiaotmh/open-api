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
public class Route implements Serializable {

    private static final long serialVersionUID = 1601031768214L;


    /**
    * 主键
    * 主键
    * isNullAble:0
    */
    private Integer id;

    /**
    * 路由名称标识
    * isNullAble:1
    */
    private String gatewayApiName;

    /**
    * 服务接口地址
    * isNullAble:1
    */
    private String insideApiUrl;

    /**
    * 服务名称
    * isNullAble:1
    */
    private String serviceId;

    /**
    * 介绍信息
    * isNullAble:1
    */
    private String description;

    /**
    * 状态 1 有效,0 无效
    * isNullAble:1
    */
    private Integer state;

    /**
    * 幂等性 1 幂等 0 非幂等
    * isNullAble:1
    */
    private Integer idempotents;

    /**
    * 是否收费 1 收费 0 免费
    * isNullAble:1
    */
    private Integer needfee;



}
