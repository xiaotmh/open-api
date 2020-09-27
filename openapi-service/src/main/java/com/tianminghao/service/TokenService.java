package com.tianminghao.service;

import com.github.pagehelper.PageInfo;
import com.tianminghao.pojo.Customer;
import com.tianminghao.pojo.Token;

import java.util.List;

/**
 * @Author Athena
 * @Date 2020/9/25 20:03
 * @Version 1.0
 * @Description token服务接口
 */

public interface TokenService {

    /**
     * 增加
     * @param token
     * @return
     */
    int save(Token token) throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Token> findAll() throws Exception;

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageInfo<Token> findPage(Integer pageNum, Integer pageSize) throws Exception;


    /**
     * 搜索并分页
     * @param pageNum
     * @param pageSize
     * @param uid
     * @param state
     * @return
     * @throws Exception
     */
    PageInfo<Token> searchPage(Integer pageNum, Integer pageSize, Integer uid, String state) throws Exception;


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
     * @param token
     * @return
     * @throws Exception
     */
    int alter(Token token) throws Exception;


    /**
     * 添加客户信息
     * @param token
     * @return
     * @throws Exception
     */
    int add(Token token) throws Exception;
}
