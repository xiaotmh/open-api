package com.tianminghao.service.impl;/**
 * @Author Athena
 * @Date 2020/9/25 20:03
 * @Version 1.0
 * @Description 应用服务实现类
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianminghao.mapper.CustomerMapper;
import com.tianminghao.pojo.Customer;
import com.tianminghao.service.CustomerService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    /**
     * 增加
     *
     * @param customer
     * @return
     */
    @Override
    public int save(Customer customer) throws Exception {
        int result = customerMapper.insertCustomer(customer);
        return result;
    }

    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Customer> findAll() throws Exception {
        List<Customer> customers = customerMapper.findAll();
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
    public PageInfo<Customer> findPage(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Customer> customers = customerMapper.findAll();
        PageInfo<Customer> pageInfo = new PageInfo<>(customers);
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
    public PageInfo<Customer> searchPage(Integer pageNum, Integer pageSize, String content, String state) throws Exception {
        //先用用户名搜索，然后用公司名搜索
        PageHelper.startPage(pageNum, pageSize);
        List<Customer> customers = customerMapper.ferretByUsername(content,state);
        PageInfo<Customer> pageInfo =null;
        if (customers.size() == 0) {
            PageHelper.startPage(pageNum, pageSize);
            customers = customerMapper.ferretByNickname(content,state);
            //如果用户名和公司名都没找到，那么检测字符串长度，如果>1就分割后再查一次
            if (customers.size() == 0) {
                if(content.length()>1){
                    log.fatal("进入删减搜索");
                    for (int i = 0; i < content.length() ; i++) {
                        String newContent=content.substring(0,content.length()-i-1);

                        PageHelper.startPage(pageNum, pageSize);
                        customers = customerMapper.ferretByUsername(newContent,state);
                        if (customers.size() != 0) {
                            log.fatal("删减搜索成功==>"+newContent);
                            break;
                        }
                    }

                    if (customers.size() == 0) {
                        for (int i = 0; i < content.length(); i++) {
                            String newContent=content.substring(0,content.length()-i-1);
                            PageHelper.startPage(pageNum, pageSize);
                            customers = customerMapper.ferretByNickname(newContent,state);
                            if (customers.size() != 0) {
                                log.fatal("删减搜索成功==>"+newContent);
                                break;
                            }
                        }
                    }

                    //如果这个时候还是空，则需要遍历内容搜索公司名

                    if (customers.size() == 0) {
                        log.fatal("单字搜索失败");
                    }


                    pageInfo = new PageInfo<>(customers);
                    return pageInfo;
                }else {
                    pageInfo = new PageInfo<>(customers);
                    return pageInfo;
                }
            }
        }else{
            pageInfo = new PageInfo<>(customers);
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
        int delete = customerMapper.delete(id);
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
     * @param customer
     * @return
     * @throws Exception
     */
    @Override
    public int alter(Customer customer) throws Exception {
        int update = customerMapper.update(customer);
        return update;
    }

    /**
     * 添加客户信息
     *
     * @param customer
     * @return
     * @throws Exception
     */
    @Override
    public int add(Customer customer) throws RuntimeException {

        //判空
        boolean flag = objCheckIsNull(customer);
        if (flag) {
            throw new ClassCastException("输入格式异常");
        }

        int insert = customerMapper.insert(customer);
        return insert;
    }

    /**
     * 通过id获取客户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Customer getById(Integer id) throws Exception {
        Customer customer = customerMapper.findById(id);
        return customer;
    }

    /**
     * 检查对象属性是否为空
     * @param object
     * @return
     */
    public  boolean objCheckIsNull(Object object){
        // 得到类对象
        Class clazz = (Class)object.getClass();
        // 得到所有属性
        Field fields[] = clazz.getDeclaredFields();
        boolean flag = false; //定义返回结果，默认为不为空
        for(Field field : fields){
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                //得到属性值
                fieldValue = field.get(object);
                //得到属性类型
                Type fieldType =field.getGenericType();
                // 得到属性名
                String fieldName = field.getName();
                System.out.println("属性类型："+fieldType+",属性名："+fieldName+",属性值："+fieldValue);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(fieldValue == null){
                if(field.getName().toString().trim().equals("id")){
                    continue;
                }
                //只要有一个属性值不为null 就返回false 表示对象不为null,除了Id属性
                if(fieldValue.toString().trim().equals("")){
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
