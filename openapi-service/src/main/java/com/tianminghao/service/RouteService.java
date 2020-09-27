package com.tianminghao.service;

import com.github.pagehelper.PageInfo;
import com.tianminghao.pojo.Customer;
import com.tianminghao.pojo.Route;

import java.util.List;

/**
 * @Author Athena
 * @Date 2020/9/25 20:03
 * @Version 1.0
 * @Description 路由服务接口
 */

public interface RouteService {

    /**
     * 增加
     * @param route
     * @return
     */
    int save(Route route) throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Route> findAll() throws Exception;

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageInfo<Route> findPage(Integer pageNum, Integer pageSize) throws Exception;


    /**
     * 搜索并分页
     * @param pageNum
     * @param pageSize
     * @param content
     * @param state
     * @return
     * @throws Exception
     */
    PageInfo<Route> searchPage(Integer pageNum, Integer pageSize, String content, String state) throws Exception;


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
     * 更新路由信息
     * @param route
     * @return
     * @throws Exception
     */
    int alter(Route route) throws Exception;


    /**
     * 添加路由信息
     * @param route
     * @return
     * @throws Exception
     */
    int add(Route route) throws Exception;
}
