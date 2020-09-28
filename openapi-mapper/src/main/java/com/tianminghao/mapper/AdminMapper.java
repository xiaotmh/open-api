package com.tianminghao.mapper;


import com.tianminghao.pojo.Admin;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * token mapper
 */
public interface AdminMapper {
    /**
     * 通过主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的参数
     * @param admin
     * @return
     */
    int insert(Admin admin);

    /**
     * 选择性插入
     * @param admin
     * @return
     */
    int insertSelective(Admin admin);

    /**
     * 通过主键查询
     * @param id
     * @return
     */
    Admin selectByPrimaryKey(Integer id);

    /**
     * 通过用户名密码查询
     * @param username,password
     * @return
     */
    Admin selectByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    /**
     * 选择性更新
     * @param admin
     * @return
     */
    int updateByPrimaryKeySelective(Admin admin);

    /**
     * 通过主键更新
     * @param admin
     * @return
     */
    int updateByPrimaryKey(Admin admin);

    /**
     * 查询所有token
     * @return
     */
    List<Admin> findAll();



}