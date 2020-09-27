package com.tianminghao.controller;

import com.github.pagehelper.PageInfo;
import com.tianminghao.common.Result;
import com.tianminghao.common.TableData;
import com.tianminghao.pojo.Recharge;
import com.tianminghao.service.RechargeService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 17:19
 * @description: 充值控制器
 */
@Controller
@RequestMapping("/recharge")
@Log4j
public class RechargeController {

    @Autowired
    RechargeService rechargeService;



    @GetMapping("/page")
    @ResponseBody
    public TableData<Recharge> findLayuiPage(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit) throws Exception {
        PageInfo<Recharge> pageInfo = rechargeService.findPage(page, limit);
        TableData<Recharge> tableData = new TableData<>();
        tableData.setCount(pageInfo.getTotal());  //从数据库获取
        tableData.setData(pageInfo.getList());
        return tableData;
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result delete(Integer id) throws Exception {

        try {
            rechargeService.drop(id);
            //int i=1/0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new Result(true, "删除成功!");
    }

    @PostMapping("/deleteBatch")
    @ResponseBody
    public Result deleteBatch(@RequestBody List<Recharge> rechargeList ) throws Exception {
        for (Recharge recharge : rechargeList) {
            try {
                rechargeService.drop(recharge.getId());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return new Result(true, "删除成功!");
    }


    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Recharge recharge) throws Exception {
        try {
            rechargeService.alter(recharge);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new Result(true, "更新成功!");
    }

    @PostMapping("/insert")
    @ResponseBody
    public Result insert(@RequestBody Recharge recharge) throws Exception {
        try {
            rechargeService.add(recharge);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new Result(true, "添加成功!");
    }

    @GetMapping("/search")
    @ResponseBody
    public TableData<Recharge> search(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      @RequestParam("searchContent") Integer cid,
                                      @RequestParam("searchState") String state) throws Exception {

        if(state.equals("")){
            state=null;
        }
        PageInfo<Recharge> pageInfo = rechargeService.searchPage(page, limit,cid,state);
        TableData<Recharge> tableData = new TableData<>();
        tableData.setCount(pageInfo.getTotal());  //从数据库获取
        tableData.setData(pageInfo.getList());
        return tableData;
    }
}
