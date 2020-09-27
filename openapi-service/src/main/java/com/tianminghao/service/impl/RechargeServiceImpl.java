package com.tianminghao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianminghao.mapper.RechargeMapper;
import com.tianminghao.pojo.Recharge;
import com.tianminghao.service.RechargeService;
import com.tianminghao.service.RechargeService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;




import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianminghao.mapper.RechargeMapper;
import com.tianminghao.pojo.Recharge;
import com.tianminghao.service.RechargeService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 20:03
 * @description: 充值服务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Log4j
public class RechargeServiceImpl implements RechargeService {

    @Autowired
    RechargeMapper rechargeMapper;

    /**
     * 增加
     *
     * @param Recharge
     * @return
     */
    @Override
    public int save(Recharge Recharge) throws Exception {
        int result = rechargeMapper.insertSelective(Recharge);
        return result;
    }

    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Recharge> findAll() throws Exception {
        List<Recharge> Recharges = rechargeMapper.findAll();
        return Recharges;
    }

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<Recharge> findPage(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Recharge> Recharges = rechargeMapper.findAll();
        PageInfo<Recharge> pageInfo = new PageInfo<>(Recharges);
        return pageInfo;
    }

    /**
     * 搜索并分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<Recharge> searchPage(Integer pageNum, Integer pageSize, Integer cid, String state) throws Exception {
        //先用用户名搜索，然后用公司名搜索
        PageHelper.startPage(pageNum, pageSize);
        List<Recharge> Recharges = rechargeMapper.ferretByCid(cid,state);
        PageInfo<Recharge> pageInfo =new PageInfo<>(Recharges);
        return pageInfo;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public int drop(Integer id) throws Exception {
        int delete = rechargeMapper.deleteByPrimaryKey(id);
        return delete;
    }

    /**
     * 批量删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int dropBatch(Integer id) throws Exception {
        return 0;
    }

    /**
     * 更新客户信息
     *
     * @param Recharge
     * @return
     * @throws Exception
     */
    @Override
    public int alter(Recharge Recharge) throws Exception {
        int update = rechargeMapper.updateByPrimaryKeySelective(Recharge);
        return update;
    }

    /**
     * 添加客户信息
     *
     * @param Recharge
     * @return
     * @throws Exception
     */
    @Override
    public int add(Recharge Recharge) throws Exception {
        int insert = rechargeMapper.insert(Recharge);
        return insert;
    }


}
