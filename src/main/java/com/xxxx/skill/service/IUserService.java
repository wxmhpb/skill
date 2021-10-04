package com.xxxx.skill.service;

import com.xxxx.skill.vo.LoginVO;
import com.xxxx.skill.vo.RespBean;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-10-03
 */

public interface IUserService {
  RespBean login(LoginVO loginVO);
}
