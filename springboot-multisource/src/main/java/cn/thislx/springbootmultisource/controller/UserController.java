package cn.thislx.springbootmultisource.controller;

import cn.thislx.springbootmultisource.core.User;
import cn.thislx.springbootmultisource.dao.primary.UserPrimaryMapper;
import cn.thislx.springbootmultisource.dao.second.UserSecondMapper;
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
    private UserPrimaryMapper userPrimaryMapper;

    @Resource
    private UserSecondMapper userSecondMapper;



    @RequestMapping("/insertPrimary")
    public String insertPrimary(Model model, User user) {
        userPrimaryMapper.insert(user);
        model.addAttribute("user", user);
        return "success";
    }

    @RequestMapping("/insertSecond")
    public String insertSecond(Model model, User user) {
        userSecondMapper.insert(user);
        model.addAttribute("user", user);
        return "success";
    }

}
