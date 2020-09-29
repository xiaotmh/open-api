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

import java.lang.reflect.Field;
import java.lang.reflect.Type;
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

        //判空
        boolean flag = objCheckIsNull(recharge);
        if (flag) {
            throw new ClassCastException("输入格式异常");
        }

        int insert = rechargeMapper.insert(recharge);
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
