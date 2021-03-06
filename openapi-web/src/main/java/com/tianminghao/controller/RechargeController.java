package com.tianminghao.controller;

import cn.hutool.core.convert.Convert;
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


    /**
     * 获取分页
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    @GetMapping("/page")
    @ResponseBody
    public TableData<Recharge> page(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit) throws Exception {
        PageInfo<Recharge> pageInfo = rechargeService.findPage(page, limit);
        for (Recharge recharge : pageInfo.getList()) {
            log.fatal("=================>"+recharge.getCreatetime());
            log.fatal("=================>"+recharge.getUpdatetime());
        }
        TableData<Recharge> tableData = new TableData<>();
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
            rechargeService.drop(id);
            //int i=1/0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new Result(true, "删除成功!");
    }

    /**
     * 批量删除
     * @param rechargeList
     * @return
     * @throws Exception
     */
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

    /**
     * 更新
     * @param recharge
     * @return
     * @throws Exception
     */
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

    /**
     * 插入新的
     * @param recharge
     * @return
     * @throws Exception
     */
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

    /**
     * 搜索
     * @param page
     * @param limit
     * @param cid
     * @param state
     * @return
     * @throws Exception
     */
    @GetMapping("/search")
    @ResponseBody
    public TableData<Recharge> search(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      @RequestParam("searchContent") String cid,
                                      @RequestParam("searchState") String state) throws Exception {

        if(state.equals("")){
            state=null;
        }
        PageInfo<Recharge> pageInfo = rechargeService.searchPage(page, limit, Convert.toInt(cid),state);
        TableData<Recharge> tableData = new TableData<>();
        tableData.setCount(pageInfo.getTotal());  //从数据库获取
        tableData.setData(pageInfo.getList());
        return tableData;
    }
}
