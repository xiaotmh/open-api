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
public class Customer implements Serializable {

    private static final long serialVersionUID = 1601031758393L;


    /**
    * 主键
    * 主键
    * isNullAble:0
    */
    private Integer id;

    /**
    * 用户名
    * isNullAble:0
    */
    private String username;

    /**
    * 密码
    * isNullAble:0
    */
    private String password;

    /**
    * 公司名称
    * isNullAble:1
    */
    private String nickname;

    /**
    * 地址
    * isNullAble:1
    */
    private String address;

    /**
    * 余额(毫)
    * isNullAble:1
    */
    private Integer money;

    /**
    * 状态 1 正常,0 停用
    * isNullAble:1
    */
    private Integer state=0;


}
