package com.tianminghao.mapper;

import java.util.List;

import com.tianminghao.pojo.Customer;
import org.apache.ibatis.annotations.Param;


/**
*  @author Athena
*/
public interface CustomerMapper {
    int insertCustomer(Customer object);


    /**
     * 更新客户
     * @return
     */
    int update(Customer object);


    /**
     * 查询所有客户
     * @return
     */
    List<Customer> findAll();

    /**
     * 删除一个客户
     * @param id
     * @return
     */
    int delete(@Param("id") Integer id);
}