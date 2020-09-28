package com.tianminghao.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.tianminghao.common.Result;
import com.tianminghao.common.TableData;
import com.tianminghao.pojo.Admin;
import com.tianminghao.pojo.Application;
import com.tianminghao.service.AdminService;
import com.tianminghao.service.ApplicationService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: Athena
 * @date: 2020/9/25 17:19
 * @description: 管理员控制器
 */
@Controller
@RequestMapping("/admin")
//@SessionAttributes(value = {"logined"},types = Admin.class)
@Log4j
public class AdminController {

    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }




    /**
     * 后台登录
     * @param admin
     * @param session
     * @return 登录结果
     * @throws Exception
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody Admin admin, HttpSession session) throws Exception {
        Admin logined = adminService.login(admin);
        if (logined != null) {
            //登录成功
            //model.addAttribute(logined);
            session.setAttribute("user",logined);
            return new Result(true, logined);
        }else{
            //登录失败
            return new Result(false, "用户名或密码不正确!");
        }
    }


    /**
     * ajax获取登录用户 状态和信息
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getLoginState",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getLoginState(HttpSession session) throws Exception {
        //Admin loginedAdmin = (Admin) session.getAttribute("logined");
// @SessionAttribute("logined") Admin admin
        //判断是否登录并返回登录的用户昵称，貌似没登录也访问不了哈，喵喵喵
        Admin admin = (Admin) session.getAttribute("user");
        return JSONUtil.toJsonStr(admin.getNickname());
    }

    /**
     * ajax获取登录用户 状态和信息
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) throws Exception {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login.html";
    }

}
