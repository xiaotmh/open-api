package com.tianminghao.controller;

import com.github.pagehelper.PageInfo;
import com.tianminghao.common.TableData;
import com.tianminghao.pojo.Customer;
import com.tianminghao.service.CustomerService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import cn.hutool.core.convert.Convert;
import com.github.pagehelper.PageInfo;
import com.tianminghao.common.Result;
import com.tianminghao.common.TableData;
import com.tianminghao.pojo.Customer;
import com.tianminghao.service.CustomerService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 17:19
 * @description: 客户控制器
 */
@Controller
@RequestMapping("/customer")
@Log4j
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * 获取全部用户
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    @ResponseBody
    public Result list() throws Exception {
        List<Customer> customers = customerService.findAll();
        if (customers == null||customers.size()==0) {
            return new Result(false, "获取失败!");
        }
        return new Result(true, customers);
    }


    /**
     * 通过id获取用户
     * @return
     * @throws Exception
     */
    @GetMapping("/getById")
    @ResponseBody
    public Result getById(@RequestParam("id") Integer id) throws Exception {
        Customer customer = customerService.getById(id);
        if (customer == null) {
            return new Result(false, "获取失败!");
        }
        return new Result(true, customer);
    }


    /**
     * 获取分页
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    @GetMapping("/page")
    @ResponseBody
    public TableData<Customer> page(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit) throws Exception {
        PageInfo<Customer> pageInfo = customerService.findPage(page, limit);
        TableData<Customer> tableData = new TableData<>();
        tableData.setCount(pageInfo.getTotal());  //从数据库获取
        tableData.setData(pageInfo.getList());
        return tableData;
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(Integer id) throws Exception {

        try {
            customerService.drop(id);
            //int i=1/0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new Result(true, "删除成功!");
    }

    /**
     * 批量删除
     * @param customerList
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteBatch")
    @ResponseBody
    public Result deleteBatch(@RequestBody List<Customer> customerList ) throws Exception {
        for (Customer customer : customerList) {
            try {
                customerService.drop(customer.getId());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return new Result(true, "删除成功!");
    }


    /**
     * 更新
     * @param customer
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Customer customer) throws Exception {
        try {
            customerService.alter(customer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new Result(true, "更新成功!");
    }

    /**
     * 插入新的
     * @param customer
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    @ResponseBody
    public Result insert(@RequestBody Customer customer) throws Exception {
        try {
            customerService.add(customer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new Result(true, "添加成功!");
    }

    /**
     * 搜索用户
     * @param page
     * @param limit
     * @param content
     * @param state
     * @return
     * @throws Exception
     */
    @GetMapping("/search")
    @ResponseBody
    public TableData<Customer> search(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      @RequestParam("searchContent") String content,
                                      @RequestParam("searchState") String state) throws Exception {
        log.fatal("searchContent====>"+content);
        log.fatal("searchState====>"+state);
        if(state.equals("")){
            state=null;
        }
        PageInfo<Customer> pageInfo = customerService.searchPage(page, limit,content,state);
        TableData<Customer> tableData = new TableData<>();
        tableData.setCount(pageInfo.getTotal());  //从数据库获取
        tableData.setData(pageInfo.getList());
        return tableData;
    }
}
