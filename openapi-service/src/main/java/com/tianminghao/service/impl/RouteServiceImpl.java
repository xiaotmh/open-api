package com.tianminghao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianminghao.mapper.RouteMapper;
import com.tianminghao.mapper.RouteMapper;
import com.tianminghao.pojo.Route;
import com.tianminghao.pojo.Route;
import com.tianminghao.service.RouteService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianminghao.mapper.RouteMapper;
import com.tianminghao.pojo.Route;
import com.tianminghao.service.RouteService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 20:03
 * @description: 路由服务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Log4j
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteMapper routeMapper;

    /**
     * 增加
     *
     * @param route
     * @return
     */
    @Override
    public int save(Route route) throws Exception {
        int result = routeMapper.insertSelective(route);
        return result;
    }

    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Route> findAll() throws Exception {
        List<Route> routes = routeMapper.findAll();
        return routes;
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
    public PageInfo<Route> findPage(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Route> routes = routeMapper.findAll();
        PageInfo<Route> pageInfo = new PageInfo<>(routes);
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
    public PageInfo<Route> searchPage(Integer pageNum, Integer pageSize, String content, String state) throws Exception {
        //先用用户名搜索，然后用公司名搜索
        PageHelper.startPage(pageNum, pageSize);
        List<Route> routes = routeMapper.ferretByGaName(content,state);
        PageInfo<Route> pageInfo =null;
        if (routes.size() == 0) {
            PageHelper.startPage(pageNum, pageSize);
            routes = routeMapper.ferretByGaName(content,state);
            //如果用户名和公司名都没找到，那么检测字符串长度，如果>1就分割后再查一次
            if (routes.size() == 0) {
                if(content.length()>1){
                    log.fatal("进入删减搜索");
                    for (int i = 0; i < content.length() ; i++) {//jack1 01234     5
                        String newContent=content.substring(0,content.length()-i-1);

                        PageHelper.startPage(pageNum, pageSize);
                        routes = routeMapper.ferretByGaName(newContent,state);
                        if (routes.size() != 0) {
                            log.fatal("删减搜索成功==>"+newContent);
                            break;
                        }
                    }

                    if (routes.size() == 0) {
                        for (int i = 0; i < content.length(); i++) {
                            String newContent=content.substring(0,content.length()-i-1);
                            PageHelper.startPage(pageNum, pageSize);
                            routes = routeMapper.ferretByGaName(newContent,state);
                            if (routes.size() != 0) {
                                log.fatal("删减搜索成功==>"+newContent);
                                break;
                            }
                        }
                    }

                    //如果这个时候还是空，则需要遍历内容搜索公司名

                    if (routes.size() == 0) {
                        log.fatal("单字搜索失败");
                    }


                    pageInfo = new PageInfo<>(routes);
                    return pageInfo;
                }else {
                    pageInfo = new PageInfo<>(routes);
                    return pageInfo;
                }
            }
        }else{
            pageInfo = new PageInfo<>(routes);
            return pageInfo;
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
        int delete = routeMapper.deleteByPrimaryKey(id);
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
     * @param route
     * @return
     * @throws Exception
     */
    @Override
    public int alter(Route route) throws Exception {
        int update = routeMapper.updateByPrimaryKeySelective(route);
        return update;
    }

    /**
     * 添加信息
     *
     * @param route
     * @return
     * @throws Exception
     */
    @Override
    public int add(Route route) throws Exception {
        int insert = routeMapper.insert(route);
        return insert;
    }


}
