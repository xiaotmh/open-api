package com.tianminghao.mapper;

import java.util.List;

import com.tianminghao.pojo.Customer;
import org.apache.ibatis.annotations.Param;


/**
*  @author Athena
*/
public interface CustomerMapper {
    int insertCustomer(Customer customer);


    /**
     * 更新客户
     * @return
     */
    int update(Customer customer);


    /**
     * 查询所有客户
     * @return
     */
    List<Customer> findAll();

    /**
     * 通过用户名查找
     * @return
     */
    List<Customer> ferretByUsername(@Param("content") String content,@Param("state") String state);

    /**
     * 通过公司名查找
     * @return
     */
    List<Customer> ferretByNickname(@Param("content") String content,@Param("state") String state);

    /**
     * 删除一个客户
     * @param id
     * @return
     */
    int delete(@Param("id") Integer id);


    int insert(Customer customer);
}