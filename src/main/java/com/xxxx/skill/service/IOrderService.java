package com.xxxx.skill.service;

import com.xxxx.skill.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.skill.pojo.User;
import com.xxxx.skill.vo.GoodsVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuxuemei
 * @since 2021-10-11
 */
public interface IOrderService extends IService<Order> {
  Order skill(User user, GoodsVO goodsVO);

}
