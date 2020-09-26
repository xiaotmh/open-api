package com.tianminghao.controller;/**
 * @Author Athena
 * @Date 2020/9/25 17:19
 * @Version 1.0
 * @Description no description
 */

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

//    @GetMapping("/list")
//    public ModelAndView list() throws Exception {
//        System.out.println("list--------------");
//
//        List<Customer> customers = customerService.findAll();
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("customer-list");
//        modelAndView.addObject("customers", customers);
//
//        return modelAndView;
//    }
//
//    @GetMapping("/page")
//    public ModelAndView findPage(@RequestParam(defaultValue = "1") Integer pageNum,
//                                 @RequestParam(defaultValue = "5") Integer pageSize) throws Exception {
//
//        PageInfo<Customer> pageInfo = customerService.findPage(pageNum, pageSize);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("customer-page");
//        modelAndView.addObject("pageInfo", pageInfo);
//
//        return modelAndView;
//    }



    //http://localhost/user/layui/page?page=1&limit=10
    @GetMapping("/page")
    @ResponseBody
    public TableData<Customer> findLayuiPage(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer limit) throws Exception {
        PageInfo<Customer> pageInfo = customerService.findPage(page, limit);
        TableData<Customer> tableData = new TableData<>();
        tableData.setCount(pageInfo.getTotal());  //从数据库获取
        tableData.setData(pageInfo.getList());
        return tableData;
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result delete(Integer id) throws Exception {

        try {
            customerService.delete(id);
            //int i=1/0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new Result(true, "删除成功!");
    }


    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Customer customer) throws Exception {
        log.fatal("前台传入customer================>"+customer);
        System.out.println("前台传入customer================>"+customer);
        try {
            customerService.update(customer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new Result(true, "更新成功!");
    }
}
