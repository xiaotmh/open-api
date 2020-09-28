package com.tianminghao.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianminghao.mapper.AdminMapper;
import com.tianminghao.mapper.TokenMapper;
import com.tianminghao.pojo.Admin;
import com.tianminghao.pojo.Token;
import com.tianminghao.service.AdminService;
import com.tianminghao.service.TokenService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 20:03
 * @description: 管理员实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Log4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    /**
     * 登录
     *
     * @param admin
     * @return
     * @throws Exception
     */
    @Override
    public Admin login(Admin admin) throws Exception {
        //密码转md5
        Admin loginAdmin = adminMapper.selectByUsernameAndPassword(admin.getUsername(), SecureUtil.md5(admin.getPassword()));
        return loginAdmin;
    }

    /**
     * 增加
     *
     * @param admin
     * @return
     */
    @Override
    public int save(Admin admin) throws Exception {
        int result = adminMapper.insertSelective(admin);
        return result;
    }

    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Admin> findAll() throws Exception {
        List<Admin> admins = adminMapper.findAll();
        return admins;
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
    public PageInfo<Admin> findPage(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> admins = adminMapper.findAll();
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);
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
        int delete = adminMapper.deleteByPrimaryKey(id);
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
     * 更新信息
     *
     * @param admin
     * @return
     * @throws Exception
     */
    @Override
    public int alter(Admin admin) throws Exception {
        int update = adminMapper.updateByPrimaryKeySelective(admin);
        return update;
    }

    /**
     * 添加信息
     *
     * @param admin
     * @return
     * @throws Exception
     */
    @Override
    public int add(Admin admin) throws Exception {
        int insert = adminMapper.insert(admin);
        return insert;
    }


}
