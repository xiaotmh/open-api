package com.tianminghao.service;

import com.github.pagehelper.PageInfo;
import com.tianminghao.pojo.Customer;
import com.tianminghao.pojo.Recharge;

import java.util.List;

/**
 * @Author Athena
 * @Date 2020/9/25 20:03
 * @Version 1.0
 * @Description 充值服务接口
 */

public interface RechargeService {

    /**
     * 增加
     * @param recharge
     * @return
     */
    int save(Recharge recharge) throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Recharge> findAll() throws Exception;

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageInfo<Recharge> findPage(Integer pageNum, Integer pageSize) throws Exception;


    /**
     * 搜索并分页
     * @param pageNum
     * @param pageSize
     * @param cid
     * @param state
     * @return
     * @throws Exception
     */
    PageInfo<Recharge> searchPage(Integer pageNum, Integer pageSize, Integer cid, String state) throws Exception;


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
     * 更新充值信息
     * @param recharge
     * @return
     * @throws Exception
     */
    int alter(Recharge recharge) throws Exception;


    /**
     * 添加充值信息
     * @param recharge
     * @return
     * @throws Exception
     */
    int add(Recharge recharge) throws Exception;
}
