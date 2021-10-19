package com.xxxx.skill.controller;


import com.xxxx.skill.pojo.User;
import com.xxxx.skill.vo.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2021-10-03
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/info")
    @ResponseBody
        public RespBean info(User user){
         return RespBean.success(user);
        }

}
