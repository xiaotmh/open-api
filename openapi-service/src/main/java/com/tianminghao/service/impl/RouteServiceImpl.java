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

import java.lang.reflect.Field;
import java.lang.reflect.Type;
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
        List<Route> routes = routeMapper.ferretByGaName(content, state);
        PageInfo<Route> pageInfo = new PageInfo<>(routes);;
        if (routes.size() == 0) {
            if (content.length() > 1) {
                log.fatal("进入删减搜索");
                for (int i = 0; i < content.length(); i++) {//jack1 01234     5
                    String newContent = content.substring(0, content.length() - i - 1);

                    PageHelper.startPage(pageNum, pageSize);
                    routes = routeMapper.ferretByGaName(newContent, state);
                    if (routes.size() != 0) {
                        log.fatal("删减搜索成功==>" + newContent);
                        break;
                    }
                }
                pageInfo = new PageInfo<>(routes);
            } else {
                pageInfo = new PageInfo<>(routes);
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
        //判空
        boolean flag = objCheckIsNull(route);
        if (!flag) {
            throw new ClassCastException("输入格式异常");
        }
        int insert = routeMapper.insert(route);
        return insert;
    }

    /**
     * 检查对象属性是否为空
     * @param object
     * @return
     */
    public  boolean objCheckIsNull(Object object){
        Class clazz = (Class)object.getClass(); // 得到类对象
        Field fields[] = clazz.getDeclaredFields(); // 得到所有属性
        boolean flag = true; //定义返回结果，默认为true
        for(Field field : fields){
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(object); //得到属性值
                Type fieldType =field.getGenericType();//得到属性类型
                String fieldName = field.getName(); // 得到属性名
                System.out.println("属性类型："+fieldType+",属性名："+fieldName+",属性值："+fieldValue);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(fieldValue != null){  //只要有一个属性值不为null 就返回false 表示对象不为null,除了Id属性
                if(!field.getName().equals("Id")){
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
