package cn.thislx.springbootjta.controller;

import cn.thislx.springbootjta.core.User;
import cn.thislx.springbootjta.service.UserPrimaryService;
import cn.thislx.springbootjta.service.UserSecondService;
import cn.thislx.springbootjta.service.dao.primary.UserPrimaryMapper;
import cn.thislx.springbootjta.service.dao.second.UserSecondMapper;
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
@RestController
public class UserController {


    @Resource
    private UserPrimaryService userPrimaryService;

    @Resource
    private UserSecondService userSecondService;


    @RequestMapping("/insertPrimary")
    public String insertPrimary(Model model, User user) {
        userPrimaryService.insert(user);
        model.addAttribute("user", user);
        return "success";
    }

    @RequestMapping("/insertSecond")
    public String insertSecond(Model model, User user) {
        userSecondService.insert(user);
        model.addAttribute("user", user);
        return "success";
    }

}
