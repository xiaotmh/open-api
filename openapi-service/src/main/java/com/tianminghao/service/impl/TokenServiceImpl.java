package com.tianminghao.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianminghao.mapper.TokenMapper;
import com.tianminghao.pojo.Token;
import com.tianminghao.service.TokenService;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianminghao.mapper.TokenMapper;
import com.tianminghao.pojo.Token;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 20:03
 * @description: token服务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Log4j
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenMapper tokenMapper;

    /**
     * 增加
     *
     * @param Token
     * @return
     */
    @Override
    public int save(Token Token) throws Exception {
        int result = tokenMapper.insertSelective(Token);
        return result;
    }

    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Token> findAll() throws Exception {
        List<Token> tokens = tokenMapper.findAll();
        return tokens;
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
    public PageInfo<Token> findPage(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Token> tokens = tokenMapper.findAll();
        PageInfo<Token> pageInfo = new PageInfo<>(tokens);
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
    public PageInfo<Token> searchPage(Integer pageNum, Integer pageSize, Integer uid, String state) throws Exception {
        //先用用户名搜索，然后用公司名搜索
        PageHelper.startPage(pageNum, pageSize);
        List<Token> tokens = tokenMapper.ferretByUid(uid,state);
        PageInfo<Token> pageInfo =new PageInfo<>(tokens);
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
        int delete = tokenMapper.deleteByPrimaryKey(id);
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
     * @param token
     * @param expireDate
     * @param expireTime
     * @param startDate
     * @param startTime
     * @return
     * @throws Exception
     */
    @Override
    public int alter(Token token,String expireDate,String expireTime,String startDate,String startTime) throws Exception {
        //分别将过期时间和开始时间的日期与时间拼接
        StringBuilder stringBuilderExpire=new StringBuilder();
        stringBuilderExpire.append(expireDate);
        stringBuilderExpire.append(" ");
        stringBuilderExpire.append(expireTime);

        StringBuilder stringBuilderStart=new StringBuilder();
        stringBuilderStart.append(startDate);
        stringBuilderStart.append(" ");
        stringBuilderStart.append(startTime);

        //将拼接的日期时间字符串转为UitlDate
        Date finalExpireTime = DateUtil.parse(stringBuilderExpire);
        Date finalStartTime = DateUtil.parse(stringBuilderStart);

        //将插入的日期时间赋值
        token.setExpireTime(finalExpireTime);
        token.setStartTime(finalStartTime);

        //调用mapper插入
        int update = tokenMapper.updateByPrimaryKeySelective(token);
        return update;
    }

    /**
     * 添加信息
     * @param token
     * @param expireDate
     * @param expireTime
     * @param startDate
     * @param startTime
     * @return
     * @throws Exception
     */
    @Override
    public int add(Token token,String expireDate,String expireTime,String startDate,String startTime) throws Exception {
        //分别将过期时间和开始时间的日期与时间拼接
        StringBuilder stringBuilderExpire=new StringBuilder();
        stringBuilderExpire.append(expireDate);
        stringBuilderExpire.append(" ");
        stringBuilderExpire.append(expireTime);

        StringBuilder stringBuilderStart=new StringBuilder();
        stringBuilderStart.append(startDate);
        stringBuilderStart.append(" ");
        stringBuilderStart.append(startTime);

        //将拼接的日期时间字符串转为UitlDate
        Date finalExpireTime = DateUtil.parse(stringBuilderExpire);
        Date finalStartTime = DateUtil.parse(stringBuilderStart);

        //将插入的日期时间赋值
        token.setExpireTime(finalExpireTime);
        token.setStartTime(finalStartTime);

        //判空
        boolean flag = objCheckIsNull(token);
        if (flag) {
            throw new ClassCastException("输入格式异常");
        }

        //调用mapper插入
        int insert = tokenMapper.insert(token);
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
