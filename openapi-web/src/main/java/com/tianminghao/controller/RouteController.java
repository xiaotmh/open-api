package com.tianminghao.controller;

import com.github.pagehelper.PageInfo;
import com.tianminghao.common.Result;
import com.tianminghao.common.TableData;
import com.tianminghao.pojo.Route;
import com.tianminghao.service.RouteService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 17:19
 * @description: 路由控制器
 */
@Controller
@RequestMapping("/route")
@Log4j
public class RouteController {

    @Autowired
    RouteService routeService;


    /**
     * 获取分页
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    @GetMapping("/page")
    @ResponseBody
    public TableData<Route> page(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer limit) throws Exception {
        PageInfo<Route> pageInfo = routeService.findPage(page, limit);
        TableData<Route> tableData = new TableData<>();
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
            routeService.drop(id);
            //int i=1/0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new Result(true, "删除成功!");
    }

    /**
     * 批量删除
     * @param routeList
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteBatch")
    @ResponseBody
    public Result deleteBatch(@RequestBody List<Route> routeList ) throws Exception {
        for (Route route : routeList) {
            try {
                routeService.drop(route.getId());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return new Result(true, "删除成功!");
    }

    /**
     * 更新
     * @param route
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Route route) throws Exception {
        try {
            routeService.alter(route);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new Result(true, "更新成功!");
    }

    /**
     * 插入新的
     * @param route
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    @ResponseBody
    public Result insert(@RequestBody Route route) throws Exception {
        try {
            routeService.add(route);
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
    public TableData<Route> search(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      @RequestParam("searchContent") String content,
                                      @RequestParam("searchState") String state) throws Exception {
        if(state.equals("")){
            state=null;
        }
        PageInfo<Route> pageInfo = routeService.searchPage(page, limit,content,state);
        TableData<Route> tableData = new TableData<>();
        tableData.setCount(pageInfo.getTotal());  //从数据库获取
        tableData.setData(pageInfo.getList());
        return tableData;
    }
}
