package com.tianminghao.service;

import com.github.pagehelper.PageInfo;
import com.tianminghao.pojo.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Athena
 * @Date 2020/9/25 20:03
 * @Version 1.0
 * @Description no description
 */

public interface CustomerService {

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
     * 删除
     * @param id
     * @return
     */
    int delete(Integer id) throws Exception;

}
