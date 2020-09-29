package com.tianminghao.controller;

import com.github.pagehelper.PageInfo;
import com.tianminghao.common.Result;
import com.tianminghao.common.TableData;
import com.tianminghao.pojo.Application;
import com.tianminghao.pojo.Customer;
import com.tianminghao.service.ApplicationService;
import com.tianminghao.service.CustomerService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 17:19
 * @description: 客户控制器
 */
@Controller
@RequestMapping("/application")
@Log4j
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;



    /**
     * 获取分页
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    @GetMapping("/page")
    @ResponseBody
    public TableData<Application> page(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit) throws Exception {
        PageInfo<Application> pageInfo = applicationService.findPage(page, limit);
        TableData<Application> tableData = new TableData<>();
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
            applicationService.drop(id);
            //int i=1/0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new Result(true, "删除成功!");
    }

    /**
     * 批量删除
     * @param applications
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteBatch")
    @ResponseBody
    public Result deleteBatch(@RequestBody List<Application> applications ) throws Exception {
        for (Application application : applications) {
            try {
                applicationService.drop(application.getId());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return new Result(true, "删除成功!");
    }

    /**
     * 更新
     * @param application
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Application application) throws Exception {
        try {
            applicationService.alter(application);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new Result(true, "更新成功!");
    }

    /**
     * 插入新的
     * @param application
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    @ResponseBody
    public Result insert(@RequestBody Application application) throws Exception {
        try {
            applicationService.add(application);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new Result(true, "添加成功!");
    }

    /**
     * 搜索
     * @param page
     * @param limit
     * @param content
     * @param state
     * @return
     * @throws Exception
     */
    @GetMapping("/search")
    @ResponseBody
    public TableData<Application> search(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      @RequestParam("searchContent") String content,
                                      @RequestParam("searchState") String state) throws Exception {

        if(state.equals("")){
            state=null;
        }
        PageInfo<Application> pageInfo = applicationService.searchPage(page, limit,content,state);
        TableData<Application> tableData = new TableData<>();
        tableData.setCount(pageInfo.getTotal());
        tableData.setData(pageInfo.getList());
        return tableData;
    }
}
