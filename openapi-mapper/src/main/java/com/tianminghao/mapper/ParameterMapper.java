package com.tianminghao.mapper;


import com.tianminghao.pojo.Customer;
import com.tianminghao.pojo.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 参数mapper
 */
public interface ParameterMapper {
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
    int insert(Parameter record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(Parameter record);

    /**
     * 通过主键查询
     * @param id
     * @return
     */
    Parameter selectByPrimaryKey(Integer id);

    /**
     * 选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Parameter record);

    /**
     * 通过主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Parameter record);

    /**
     * 查询所有参数
     * @return
     */
    List<Parameter> findAll();

    /**
     * 通过参数名查找
     * @param content
     * @param state
     * @return
     */
    List<Parameter> ferretByName(@Param("content") String content, @Param("state") String state);
}