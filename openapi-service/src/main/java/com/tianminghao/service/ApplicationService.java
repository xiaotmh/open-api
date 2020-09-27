package com.tianminghao.service;

import com.github.pagehelper.PageInfo;
import com.tianminghao.pojo.Application;
import com.tianminghao.pojo.Customer;

import java.util.List;

/**
 * @Author Athena
 * @Date 2020/9/25 20:03
 * @Version 1.0
 * @Description 应用服务接口
 */

public interface ApplicationService {

    /**
     * 增加
     * @param application
     * @return
     */
    int save(Application application) throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Application> findAll() throws Exception;

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageInfo<Application> findPage(Integer pageNum, Integer pageSize) throws Exception;


    /**
     * 搜索并分页
     * @param pageNum
     * @param pageSize
     * @param content
     * @param state
     * @return
     * @throws Exception
     */
    PageInfo<Application> searchPage(Integer pageNum, Integer pageSize, String content, String state) throws Exception;


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
     * 更新应用信息
     * @param application
     * @return
     * @throws Exception
     */
    int alter(Application application) throws Exception;


    /**
     * 添加应用信息
     * @param application
     * @return
     * @throws Exception
     */
    int add(Application application) throws Exception;
}
