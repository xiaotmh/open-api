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

import java.util.Date;
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
     * @param recharge
     * @return
     */
    @Override
    public int save(Recharge recharge) throws Exception {
        int result = rechargeMapper.insertSelective(recharge);
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
        List<Recharge> recharges = rechargeMapper.findAll();
        return recharges;
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
        List<Recharge> recharges = rechargeMapper.findAll();
        PageInfo<Recharge> pageInfo = new PageInfo<>(recharges);
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
        //直接用客户id搜索
        PageHelper.startPage(pageNum, pageSize);
        List<Recharge> recharges = rechargeMapper.ferretByCid(cid,state);
        PageInfo<Recharge> pageInfo =new PageInfo<>(recharges);
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
     * @param recharge
     * @return
     * @throws Exception
     */
    @Override
    public int alter(Recharge recharge) throws Exception {
        //修改时避免修改原创建日期先查询原值
        Recharge selectByPrimaryKey = rechargeMapper.selectByPrimaryKey(recharge.getId());
        recharge.setCreatetime(selectByPrimaryKey.getCreatetime());
        //更新修改日期
        recharge.setUpdatetime(new Date());
        int update = rechargeMapper.updateByPrimaryKeySelective(recharge);
        return update;
    }

    /**
     * 添加客户信息
     *
     * @param recharge
     * @return
     * @throws Exception
     */
    @Override
    public int add(Recharge recharge) throws Exception {
        //创建时修改日期和创建日期都是当前时间
        recharge.setCreatetime(new Date());
        recharge.setUpdatetime(new Date());
        int insert = rechargeMapper.insert(recharge);
        return insert;
    }


}
