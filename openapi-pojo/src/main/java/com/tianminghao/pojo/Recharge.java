package com.tianminghao.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    /**
    * 更新时间
    * isNullAble:1
    */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatetime;

    /**
     * 充值金额
     * isNullAble:1
     */
    private BigDecimal money;

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
