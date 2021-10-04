package com.xxxx.skill.service.impl;

import com.xxxx.skill.mapper.UserMapper;
import com.xxxx.skill.pojo.User;
import com.xxxx.skill.service.IUserService;
import com.xxxx.skill.utils.MD5Utils;
import com.xxxx.skill.utils.ValidatorUtil;
import com.xxxx.skill.vo.LoginVO;
import com.xxxx.skill.vo.RespBean;
import com.xxxx.skill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

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
    @Override
    public RespBean login(LoginVO loginVO) {
        if(StringUtils.isEmpty(loginVO.getMoblie())||StringUtils.isEmpty(loginVO.getPassword())){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if(!ValidatorUtil.isMoblie(loginVO.getMoblie())){
            return RespBean.error(RespBeanEnum.MOBLIE_ERROR);
        }

        //根据手机号获取用户
        User user=userMapper.selectById(loginVO.getMoblie());
        if(null==user){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if(!MD5Utils.formPassToDBPass(loginVO.getPassword(),user.getSlat()).equals(user.getPassword())) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }

        return RespBean.success();
    }
}
