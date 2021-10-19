package com.xxxx.skill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.skill.pojo.Order;
import com.xxxx.skill.pojo.SkillOrder;
import com.xxxx.skill.pojo.User;
import com.xxxx.skill.service.IGoodsService;
import com.xxxx.skill.service.IGoodsVOService;
import com.xxxx.skill.service.IOrderService;
import com.xxxx.skill.service.ISkillOrderService;
import com.xxxx.skill.vo.GoodsVO;
import com.xxxx.skill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
优化前：
10000个线程
win：953.9
Linux：170
库存出现负数，出现超卖情况

优化：页面缓存
 */
@Controller
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    private IGoodsVOService goodsVOService;
    @Autowired
    private ISkillOrderService skillOrderService;
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/doSkill")
    public String doSkill(Model model, User user,Long goodsId){
        if(user==null){
            return "login";
        }
        model.addAttribute("user",user);
        GoodsVO goodsVO=goodsVOService.findGoodsDetailByGoodsId(goodsId);
        //判断库存
        if(goodsVO.getStockCount()<1){
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "skillFail";
        }
        //判断是否重复抢购
        SkillOrder skillOrder = skillOrderService.getOne(new QueryWrapper<SkillOrder>().
                eq("user_id", user.getId()).eq("goods_id", goodsId));
        if(skillOrder!=null){
            model.addAttribute("errmsg",RespBeanEnum.REPEATE_ERROR.getMessage());
            return "skillFail";
        }
        Order order=orderService.skill(user,goodsVO);
        model.addAttribute("order",order);
        model.addAttribute("goods",goodsVO);
        return "orderDetail";
    }

}
