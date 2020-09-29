package com.tianminghao.controller;

import com.github.pagehelper.PageInfo;
import com.tianminghao.common.Result;
import com.tianminghao.common.TableData;

import com.tianminghao.pojo.Parameter;

import com.tianminghao.service.ParameterService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 17:19
 * @description: 参数控制器
 */
@Controller
@RequestMapping("/parameter")
@Log4j
public class ParameterController {

    @Autowired
    ParameterService parameterService;

    /**
     * 获取分页
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    @GetMapping("/page")
    @ResponseBody
    public TableData<Parameter> page(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit) throws Exception {
        PageInfo<Parameter> pageInfo = parameterService.findPage(page, limit);
        TableData<Parameter> tableData = new TableData<>();
        tableData.setCount(pageInfo.getTotal());
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
            parameterService.drop(id);
            //int i=1/0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new Result(true, "删除成功!");
    }

    /**
     * 批量删除
     * @param parameterList
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteBatch")
    @ResponseBody
    public Result deleteBatch(@RequestBody List<Parameter> parameterList ) throws Exception {
        for (Parameter parameter : parameterList) {
            try {
                parameterService.drop(parameter.getId());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return new Result(true, "删除成功!");
    }

    /**
     * 更新
     * @param parameter
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Parameter parameter) throws Exception {
        try {
            parameterService.alter(parameter);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new Result(true, "更新成功!");
    }


    /**
     * 插入新的
     * @param parameter
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    @ResponseBody
    public Result insert(@RequestBody Parameter parameter) throws Exception {
        try {
            parameterService.add(parameter);
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
    public TableData<Parameter> search(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      @RequestParam("searchContent") String content,
                                      @RequestParam("searchState") String state) throws Exception {
        log.fatal("searchContent====>"+content);
        log.fatal("searchState====>"+state);
        if(state.equals("")){
            state=null;
        }
        PageInfo<Parameter> pageInfo = parameterService.searchPage(page, limit,content,state);
        TableData<Parameter> tableData = new TableData<>();
        tableData.setCount(pageInfo.getTotal());  //从数据库获取
        tableData.setData(pageInfo.getList());
        return tableData;
    }
}
