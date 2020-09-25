package com.tianminghao.service.impl;/**
 * @Author Athena
 * @Date 2020/9/25 20:03
 * @Version 1.0
 * @Description no description
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianminghao.mapper.CustomerMapper;
import com.tianminghao.pojo.Customer;
import com.tianminghao.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 20:03
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    /**
     * 增加
     *
     * @param customer
     * @return
     */
    @Override
    public int save(Customer customer) throws Exception {
        int result = customerMapper.insertCustomer(customer);
        return result;
    }

    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Customer> findAll() throws Exception {
        List<Customer> customers = customerMapper.findAll();
        return customers;
    }

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<Customer> findPage(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Customer> customers = customerMapper.findAll();
        PageInfo<Customer> pageInfo = new PageInfo<>(customers);
        return pageInfo;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Integer id) throws Exception {
        int delete = customerMapper.delete(id);
        return delete;
    }
}
