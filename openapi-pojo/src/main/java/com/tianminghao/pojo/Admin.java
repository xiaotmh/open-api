package com.tianminghao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员实体类
 * @author Athena
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {

  private static final long serialVersionUID = 1601031723074L;

  /**
   * 主键
   * 主键
   * isNullAble:0
   */
  private long id;

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
   * 昵称
   * isNullAble:0
   */
  private String nickname;

  /**
   * 邮箱
   * isNullAble:0
   */
  private String email;

  /**
   * 最后登录时间
   * isNullAble:0
   */
  private Date loginTime;

  /**
   * 创建时间
   * isNullAble:0
   */
  private Date createTime;



}
