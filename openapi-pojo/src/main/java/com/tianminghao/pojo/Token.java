package com.tianminghao.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

/**
*
*  @author Athena
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Serializable {

    private static final long serialVersionUID = 1601031770898L;


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
    private Integer userId;

    /**
    * token 内容
    * isNullAble:1
    */
    private String accessToken;

    /**
    * 开始时间
    * isNullAble:1
    */
    private Date startTime;

    /**
    * 结束时间
    * isNullAble:1
    */
    private Date expireTime;



}
