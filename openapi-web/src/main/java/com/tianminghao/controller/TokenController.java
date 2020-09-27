package com.tianminghao.controller;

import com.github.pagehelper.PageInfo;
import com.tianminghao.common.Result;
import com.tianminghao.common.TableData;
import com.tianminghao.pojo.Token;
import com.tianminghao.service.TokenService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 17:19
 * @description: token控制器
 */
@Controller
@RequestMapping("/token")
@Log4j
public class TokenController {

    @Autowired
    TokenService tokenService;



    @GetMapping("/page")
    @ResponseBody
    public TableData<Token> findLayuiPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer limit) throws Exception {
        PageInfo<Token> pageInfo = tokenService.findPage(page, limit);
        TableData<Token> tableData = new TableData<>();
        tableData.setCount(pageInfo.getTotal());  //从数据库获取
        tableData.setData(pageInfo.getList());
        return tableData;
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result delete(Integer id) throws Exception {

        try {
            tokenService.drop(id);
            //int i=1/0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new Result(true, "删除成功!");
    }

    @PostMapping("/deleteBatch")
    @ResponseBody
    public Result deleteBatch(@RequestBody List<Token> tokenList ) throws Exception {
        for (Token token : tokenList) {
            try {
                tokenService.drop(token.getId());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return new Result(true, "删除成功!");
    }


    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Token token) throws Exception {
        try {
            tokenService.alter(token);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new Result(true, "更新成功!");
    }

    @PostMapping("/insert")
    @ResponseBody
    public Result insert(@RequestBody Token token) throws Exception {
        try {
            tokenService.add(token);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new Result(true, "添加成功!");
    }

    @GetMapping("/search")
    @ResponseBody
    public TableData<Token> search(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      @RequestParam("searchContent") Integer uid,
                                      @RequestParam("searchState") String state) throws Exception {

        if(state.equals("")){
            state=null;
        }
        PageInfo<Token> pageInfo = tokenService.searchPage(page, limit,uid,state);
        TableData<Token> tableData = new TableData<>();
        tableData.setCount(pageInfo.getTotal());  //从数据库获取
        tableData.setData(pageInfo.getList());
        return tableData;
    }
}
