package com.xxxx.skill.service;

import com.xxxx.skill.vo.LoginVO;
import com.xxxx.skill.vo.RespBean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-10-03
 */

public interface IUserService {
  RespBean login(HttpServletRequest request, HttpServletResponse response, LoginVO loginVO);
}
