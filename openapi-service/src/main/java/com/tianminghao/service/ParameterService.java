package com.tianminghao.service;

import com.github.pagehelper.PageInfo;
import com.tianminghao.pojo.Customer;
import com.tianminghao.pojo.Parameter;

import java.util.List;

/**
 * @Author Athena
 * @Date 2020/9/25 20:03
 * @Version 1.0
 * @Description 参数服务接口
 */

public interface ParameterService {

    /**
     * 增加
     * @param parameter
     * @return
     */
    int save(Parameter parameter) throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Parameter> findAll() throws Exception;

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageInfo<Parameter> findPage(Integer pageNum, Integer pageSize) throws Exception;


    /**
     * 搜索并分页
     * @param pageNum
     * @param pageSize
     * @param content
     * @param state
     * @return
     * @throws Exception
     */
    PageInfo<Parameter> searchPage(Integer pageNum, Integer pageSize, String content, String state) throws Exception;


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
     * 更新参数信息
     * @param parameter
     * @return
     * @throws Exception
     */
    int alter(Parameter parameter) throws Exception;


    /**
     * 添加参数信息
     * @param parameter
     * @return
     * @throws Exception
     */
    int add(Parameter parameter) throws Exception;
}
