package com.tianminghao.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.tianminghao.common.Result;
import com.tianminghao.common.TableData;
import com.tianminghao.pojo.Token;
import com.tianminghao.service.TokenService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public Result update(@RequestBody Map<String,Object> paramsMap) throws Exception {
        String id = (String) paramsMap.get("id");
        String cusId = (String) paramsMap.get("cusId");
        //String accessToken = (String) paramsMap.get("accessToken");
        String startDate = (String) paramsMap.get("startDate");
        String startTimey = (String) paramsMap.get("startTimey");
        String expireDate = (String) paramsMap.get("expireDate");
        String expireTimey = (String) paramsMap.get("expireTimey");
        Token token=new Token();
        token.setId(Convert.toInt(id));
        token.setUserId(Convert.toInt(cusId));
        //设置不允许修改token
        token.setAccessToken(null);
        try {
            tokenService.alter(token,expireDate,expireTimey,startDate,startTimey);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new Result(true, "更新成功!");
    }

    @PostMapping("/insert")
    @ResponseBody//@RequestBody Token token @RequestBody Map<String,Object> paramsMap
    public Result insert(@RequestBody Map<String,Object> paramsMap) throws Exception {
        //@RequestParam("accessToken") String accessToken,@RequestParam("expireDate") String expireDate
        //            ,@RequestParam("expireTimey") String expireTimey,@RequestParam("startDate") String startDate
        //            ,@RequestParam("startTimey") String startTimey

        //Set<Map.Entry<String, Object>> entries = paramsMap.entrySet();
//        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
//            log.fatal("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }

        String cusId = (String) paramsMap.get("cusId");
        String accessToken = (String) paramsMap.get("accessToken");
        String startDate = (String) paramsMap.get("startDate");
        String startTimey = (String) paramsMap.get("startTimey");
        String expireDate = (String) paramsMap.get("expireDate");
        String expireTimey = (String) paramsMap.get("expireTimey");

        log.fatal("cusId===========>"+cusId);
//        log.fatal("accessToken===========>"+accessToken);
//        log.fatal("expireDate===========>"+expireDate);
//        log.fatal("expireTime===========>"+expireTimey);
//        log.fatal("startDate===========>"+startDate);
//        log.fatal("startTime===========>"+startTimey);
        Token token=new Token();
        token.setUserId(Convert.toInt(cusId));
        token.setAccessToken(accessToken);
        //将日期时间分别传给service进行处理
        try {
            tokenService.add(token,expireDate,expireTimey,startDate,startTimey);
        } catch (Exception e) {
            throw new RuntimeException("你大爷的");
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
