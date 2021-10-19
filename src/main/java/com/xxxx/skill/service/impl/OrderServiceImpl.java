package com.xxxx.skill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.skill.pojo.Order;
import com.xxxx.skill.mapper.OrderMapper;
import com.xxxx.skill.pojo.SkillGoods;
import com.xxxx.skill.pojo.SkillOrder;
import com.xxxx.skill.pojo.User;
import com.xxxx.skill.service.IGoodsService;
import com.xxxx.skill.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.skill.service.ISkillGoodsService;
import com.xxxx.skill.service.ISkillOrderService;
import com.xxxx.skill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuxuemei
 * @since 2021-10-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private ISkillGoodsService skillGoodsService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private  OrderMapper orderMapper;
    @Autowired
    private ISkillOrderService skillOrderService;
    @Override
    public Order skill(User user, GoodsVO goodsVO) {
        SkillGoods skillGoods=skillGoodsService.getOne(new QueryWrapper<SkillGoods>().eq("goods_id",
              goodsVO.getId()));
        skillGoods.setStockCount(skillGoods.getStockCount()-1);
        skillGoodsService.updateById(skillGoods);
        //生成订单
        Order order=new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goodsVO.getId());
        order.setAddrId(0L);
        order.setGoodsCount(1);
        order.setGoodsName(goodsVO.getGoodsName());
        order.setGoodsPrice(skillGoods.getSkillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        SkillOrder skillOrder=new SkillOrder();
        skillOrder.setOrderId(order.getId());
        skillOrder.setUserId(user.getId());
        skillOrder.setGoodsId(goodsVO.getId());
        skillOrderService.save(skillOrder);
        return order;
    }
}
