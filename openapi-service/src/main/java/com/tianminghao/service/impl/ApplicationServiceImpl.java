package com.tianminghao.service.impl;/**
 * @Author Athena
 * @Date 2020/9/25 20:03
 * @Version 1.0
 * @Description no description
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianminghao.mapper.ApplicationMapper;
import com.tianminghao.mapper.CustomerMapper;
import com.tianminghao.pojo.Application;
import com.tianminghao.pojo.Customer;
import com.tianminghao.service.ApplicationService;
import com.tianminghao.service.CustomerService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 20:03
 * @description: 应用服务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Log4j
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationMapper applicationMapper;

    /**
     * 增加
     *
     * @param application
     * @return
     */
    @Override
    public int save(Application application) throws Exception {
        int result = applicationMapper.insertSelective(application);
        return result;
    }

    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Application> findAll() throws Exception {
        List<Application> customers = applicationMapper.findAll();
        return customers;
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
    public PageInfo<Application> findPage(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Application> customers = applicationMapper.findAll();
        PageInfo<Application> pageInfo = new PageInfo<>(customers);
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
    public PageInfo<Application> searchPage(Integer pageNum, Integer pageSize, String content, String state) throws Exception {
        //先用用户名搜索，然后用公司名搜索
        PageHelper.startPage(pageNum, pageSize);
        List<Application> customers = applicationMapper.ferretByAppName(content, state);
        PageInfo<Application> pageInfo = null;
        PageHelper.startPage(pageNum, pageSize);
        customers = applicationMapper.ferretByAppName(content, state);
        //如果用户名和公司名都没找到，那么检测字符串长度，如果>1就分割后再查一次
        if (customers.size() == 0) {
            if (content.length() > 1) {
                log.fatal("进入删减搜索");
                for (int i = 0; i < content.length(); i++) {//jack1 01234     5
                    String newContent = content.substring(0, content.length() - i - 1);
                    PageHelper.startPage(pageNum, pageSize);
                    customers = applicationMapper.ferretByAppName(newContent, state);
                    if (customers.size() != 0) {
                        log.fatal("删减搜索成功==>" + newContent);
                        break;
                    }
                }
                //如果这个时候还是空，则需要遍历内容搜索公司名
                if (customers.size() == 0) {
                    log.fatal("单字搜索失败");
                }
                pageInfo = new PageInfo<>(customers);
            } else {
                pageInfo = new PageInfo<>(customers);
            }
        }
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
        int delete = applicationMapper.deleteByPrimaryKey(id);
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
     * 更新应用信息
     *
     * @param application
     * @return
     * @throws Exception
     */
    @Override
    public int alter(Application application) throws Exception {
        int update = applicationMapper.updateByPrimaryKeySelective(application);
        return update;
    }

    /**
     * 添加应用信息
     *
     * @param application
     * @return
     * @throws Exception
     */
    @Override
    public int add(Application application) throws Exception {
        int insert = applicationMapper.insert(application);
        return insert;
    }


}
