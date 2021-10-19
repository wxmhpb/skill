package com.xxxx.skill.service.impl;

import ch.qos.logback.core.pattern.color.RedCompositeConverter;
import com.xxxx.skill.mapper.UserMapper;
import com.xxxx.skill.pojo.User;
import com.xxxx.skill.service.IUserService;
import com.xxxx.skill.utils.CookieUtil;
import com.xxxx.skill.utils.MD5Utils;
import com.xxxx.skill.utils.UUidUtil;
import com.xxxx.skill.utils.ValidatorUtil;
import com.xxxx.skill.vo.LoginVO;
import com.xxxx.skill.vo.RespBean;
import com.xxxx.skill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-10-03
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public RespBean login(HttpServletRequest request, HttpServletResponse response, LoginVO loginVO) {
//        if(StringUtils.isEmpty(loginVO.getMoblie())||StringUtils.isEmpty(loginVO.getPassword())){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//        if(!ValidatorUtil.isMoblie(loginVO.getMoblie())){
//            return RespBean.error(RespBeanEnum.MOBLIE_ERROR);
       // }

        //根据手机号获取用户
        User user=userMapper.selectById(loginVO.getMoblie());
        if(null==user){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if(!MD5Utils.formPassToDBPass(loginVO.getPassword(),user.getSlat()).equals(user.getPassword())) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        //生成cookie
        String ticket= UUidUtil.uuid();
      //request.getSession().setAttribute(ticket,user);
        redisTemplate.opsForValue().set("user:"+ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);


        return RespBean.success(ticket);
    }

    @Override
    public User getByUserTicket(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(userTicket)) {
            return null;
        }
        User user=(User)redisTemplate.opsForValue().get("user:"+userTicket);
        if(null!=user){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
      return user;
    }
}
