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
public class Recharge implements Serializable {

    private static final long serialVersionUID = 1601031765208L;


    /**
    * 主键
    * 主键
    * isNullAble:0
    */
    private Integer id;

    /**
    * 客户 id
    * isNullAble:1
    */
    private Integer cusId;

    /**
    * 创建订单时间
    * isNullAble:1
    */
    private java.time.LocalDateTime createtime;

    /**
    * 更新时间
    * isNullAble:1
    */
    private java.time.LocalDateTime updatetime;

    /**
    * 状态,0未支付,1 已支付,2 已取消
    * isNullAble:1
    */
    private Integer state;

    /**
    * 支付类型 0 银联,1 微信,2支付宝
    * isNullAble:1
    */
    private Integer paymentType=0;


}
