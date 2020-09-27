package com.tianminghao.mapper;


import com.tianminghao.pojo.Customer;
import com.tianminghao.pojo.Token;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * token mapper
 */
public interface TokenMapper {
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
    int insert(Token record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(Token record);

    /**
     * 通过主键查询
     * @param id
     * @return
     */
    Token selectByPrimaryKey(Integer id);

    /**
     * 选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Token record);

    /**
     * 通过主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Token record);

    /**
     * 查询所有token
     * @return
     */
    List<Token> findAll();


}