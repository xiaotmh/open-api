package com.tianminghao.service;

import com.github.pagehelper.PageInfo;
import com.tianminghao.pojo.Customer;

import java.util.List;

/**
 * @Author Athena
 * @Date 2020/9/25 20:03
 * @Version 1.0
 * @Description 客户服务接口
 */

public interface RechargeService {

    /**
     * 增加
     * @param customer
     * @return
     */
    int save(Customer customer) throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Customer> findAll() throws Exception;

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageInfo<Customer> findPage(Integer pageNum, Integer pageSize) throws Exception;


    /**
     * 搜索并分页
     * @param pageNum
     * @param pageSize
     * @param content
     * @param state
     * @return
     * @throws Exception
     */
    PageInfo<Customer> searchPage(Integer pageNum, Integer pageSize, String content, String state) throws Exception;


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
     * 更新客户信息
     * @param customer
     * @return
     * @throws Exception
     */
    int alter(Customer customer) throws Exception;


    /**
     * 添加客户信息
     * @param customer
     * @return
     * @throws Exception
     */
    int add(Customer customer) throws Exception;
}
