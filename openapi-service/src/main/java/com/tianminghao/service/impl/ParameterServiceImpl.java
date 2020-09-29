package com.tianminghao.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianminghao.mapper.ParameterMapper;
import com.tianminghao.mapper.ParameterMapper;
import com.tianminghao.pojo.Parameter;
import com.tianminghao.pojo.Parameter;
import com.tianminghao.service.ParameterService;
import com.tianminghao.service.ParameterService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 20:03
 * @description: 客户服务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Log4j
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    ParameterMapper parameterMapper;

    /**
     * 增加
     *
     * @param parameter
     * @return
     */
    @Override
    public int save(Parameter parameter) throws Exception {
        int result = parameterMapper.insertSelective(parameter);
        return result;
    }

    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Parameter> findAll() throws Exception {
        List<Parameter> parameters = parameterMapper.findAll();
        return parameters;
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
    public PageInfo<Parameter> findPage(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Parameter> parameters = parameterMapper.findAll();
        PageInfo<Parameter> pageInfo = new PageInfo<>(parameters);
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
    public PageInfo<Parameter> searchPage(Integer pageNum, Integer pageSize, String content,String state) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Parameter> parameters = parameterMapper.ferretByName(content, state);
        PageInfo<Parameter> pageInfo = new PageInfo<>(parameters);;
        if (parameters.size() == 0) {
            if (content.length() > 1) {
                for (int i = 0; i < content.length(); i++) {
                    String newContent = content.substring(0, content.length() - i - 1);
                    PageHelper.startPage(pageNum, pageSize);
                    parameters = parameterMapper.ferretByName(newContent, state);
                    if (parameters.size() != 0) {
                        break;
                    }
                }
            } else {
                pageInfo = new PageInfo<>(parameters);
                return pageInfo;
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
        int delete = parameterMapper.deleteByPrimaryKey(id);
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
     * @param parameter
     * @return
     * @throws Exception
     */
    @Override
    public int alter(Parameter parameter) throws Exception {
        int update = parameterMapper.updateByPrimaryKeySelective(parameter);
        return update;
    }

    /**
     * 添加信息
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @Override
    public int add(Parameter parameter) throws Exception {

        //判空
        boolean flag = objCheckIsNull(parameter);
        if (flag) {
            throw new ClassCastException("输入格式异常");
        }

        int insert = parameterMapper.insert(parameter);
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
            if(fieldValue != null||field.getName().equals("id")){
                //只要有一个属性值不为null 就返回false 表示对象不为null,除了Id属性
                flag = false;
                break;
            }
        }
        return flag;
    }
}
