package com.tianminghao.mapper;


import com.tianminghao.pojo.Application;
import com.tianminghao.pojo.Customer;
import com.tianminghao.pojo.Recharge;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 充值mapper
 */
public interface RechargeMapper {
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
    int insert(Recharge record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(Recharge record);

    /**
     * 通过主键查询
     * @param id
     * @return
     */
    Recharge selectByPrimaryKey(Integer id);

    /**
     * 选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Recharge record);

    /**
     * 通过主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Recharge record);

    /**
     * 查询所有充值记录
     * @return
     */
    List<Recharge> findAll();


    /**
     * 通过客户查找充值记录
     * @return
     */
    List<Recharge> ferretByCid(@Param("cid") Integer cid, @Param("state") String state);
}