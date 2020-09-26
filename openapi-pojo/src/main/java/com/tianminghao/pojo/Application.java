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
public class Application implements Serializable {

    private static final long serialVersionUID = 1601031750074L;


    /**
    * 主键
    * 主键
    * isNullAble:0
    */
    private Integer id;

    /**
    * 关联企业名称,冗余减少查询
    * isNullAble:1
    */
    private String corpName;

    /**
    * 应用名称
    * isNullAble:1
    */
    private String appName;

    /**
    * 应用唯一标示 KEY
    * isNullAble:1
    */
    private String appKey;

    /**
    * 应用签名秘钥
    * isNullAble:1
    */
    private String appSecret;

    /**
    * 应用回调用 URL
    * isNullAble:1
    */
    private String redirectUrl;

    /**
    * 免费接口日调用限制次数
    * isNullAble:1
    */
    private Integer limitCount;

    /**
    * 应用介绍
    * isNullAble:1
    */
    private String description;

    /**
    * 关联客户 id
    * isNullAble:1
    */
    private Integer cusId;

    /**
    * 状态
    * isNullAble:1
    */
    private Integer state;




}
