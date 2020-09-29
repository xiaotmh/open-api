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


    /**
     * 获取分页
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    @GetMapping("/page")
    @ResponseBody
    public TableData<Token> page(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer limit) throws Exception {
        PageInfo<Token> pageInfo = tokenService.findPage(page, limit);
        TableData<Token> tableData = new TableData<>();
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
            tokenService.drop(id);
            //int i=1/0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return new Result(true, "删除成功!");
    }

    /**
     * 批量删除
     * @param tokenList
     * @return
     * @throws Exception
     */
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

    /**
     * 更新
     * @param paramsMap
     * @return
     * @throws Exception
     */
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

    /**
     * 插入新的
     * @param paramsMap
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    @ResponseBody//@RequestBody Token token @RequestBody Map<String,Object> paramsMap
    public Result insert(@RequestBody Map<String,Object> paramsMap) throws Exception {

        String cusId = (String) paramsMap.get("cusId");
        //String accessToken = (String) paramsMap.get("accessToken");
        String startDate = (String) paramsMap.get("startDate");
        String startTimey = (String) paramsMap.get("startTimey");
        String expireDate = (String) paramsMap.get("expireDate");
        String expireTimey = (String) paramsMap.get("expireTimey");

        Token token=new Token();
        token.setUserId(Convert.toInt(cusId));
        //token.setAccessToken(accessToken);
        //将日期时间分别传给service进行处理
        try {
            tokenService.add(token,expireDate,expireTimey,startDate,startTimey);
        } catch (Exception e) {
            throw new RuntimeException("你大爷的");
        }
        return new Result(true, "添加成功!");
    }

    /**
     * 搜索
     * @param page
     * @param limit
     * @param uid
     * @param state
     * @return
     * @throws Exception
     */
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
