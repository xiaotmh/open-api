package com.tianminghao.service;

import com.github.pagehelper.PageInfo;
import com.tianminghao.pojo.Admin;
import com.tianminghao.pojo.Token;

import java.util.List;

/**
 * @Author Athena
 * @Date 2020/9/25 20:03
 * @Version 1.0
 * @Description 管理员服务接口
 */

public interface AdminService {

    /**
     * 登录
     * @param admin
     * @return
     * @throws Exception
     */
    Admin login(Admin admin) throws Exception;







    /**
     * 增加
     * @param admin
     * @return
     */
    int save(Admin admin) throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Admin> findAll() throws Exception;

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageInfo<Admin> findPage(Integer pageNum, Integer pageSize) throws Exception;





    /**
     * 删除
     * @param id
     * @return
     */
    int drop(Integer id) throws Exception;

    /**
     * 批量删除
     * @param id
     * @return
     * @throws Exception
     */
    int dropBatch(Integer id) throws Exception;


    /**
     * 更新信息
     * @param admin
     * @return
     * @throws Exception
     */
    int alter(Admin admin) throws Exception;


    /**
     * 添加信息
     * @param admin
     * @return
     * @throws Exception
     */
    int add(Admin admin) throws Exception;
}
