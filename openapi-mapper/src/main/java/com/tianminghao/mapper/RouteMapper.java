package com.tianminghao.mapper;


import com.tianminghao.pojo.Customer;
import com.tianminghao.pojo.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 路由mapper
 */
public interface RouteMapper {
    /**
     * 通过主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的参数
     * @param record
     * @return
     */
    int insert(Route record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(Route record);

    /**
     * 通过主键查询
     * @param id
     * @return
     */
    Route selectByPrimaryKey(Integer id);

    /**
     * 选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Route record);

    /**
     * 通过主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Route record);

    /**
     * 查询所有路由
     * @return
     */
    List<Route> findAll();

    /**
     * 通过路由名查找
     * @return
     */
    List<Route> ferretByGaName(@Param("content") String content, @Param("state") String state);
}