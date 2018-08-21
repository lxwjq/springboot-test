package cn.thislx.springbootmybatis.controller;

import cn.thislx.springbootmybatis.core.User;
import cn.thislx.springbootmybatis.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: LX
 * @Description:
 * @Date: Created in 15:16 2018/8/20
 * @Modified by:
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping("/insert")
    public String insert(Model model, User user) {
        userService.insert(user);
        model.addAttribute("user", user);
        return "success";
    }

}
