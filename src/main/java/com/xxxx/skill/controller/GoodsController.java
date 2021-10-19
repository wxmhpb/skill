package com.xxxx.skill.controller;

import com.xxxx.skill.pojo.User;
import com.xxxx.skill.service.IGoodsService;
import com.xxxx.skill.service.IGoodsVOService;
import com.xxxx.skill.service.IUserService;
import com.xxxx.skill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*

优化前：
windows:1833
linux:207
优化后：
win：3177
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IGoodsVOService iGoodsVOService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;
    @RequestMapping(value="/toList",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toList(HttpServletRequest request, HttpServletResponse response, Model model, User user){
       ValueOperations valueOperations=redisTemplate.opsForValue();
       //redis中获取页面，如果不为空，直接返回页面
        String html=(String)valueOperations.get("goodsList");
        if(!StringUtils.isEmpty(html)){
            return html;
        }
        model.addAttribute("user",user);
        model.addAttribute("goodsList",iGoodsVOService.list());
       // return "goodsList";
        //如果为空，手动渲染，存入redis并返回
        WebContext context = new WebContext(request, response,
                request.getServletContext(), request.getLocale(),
                model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsList", context);
        if (!StringUtils.isEmpty(html)) {
            valueOperations.set("goodsList", html, 60, TimeUnit.SECONDS);
        }
        return html;
    }
    @RequestMapping(value="/toDetail/{goodsId}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toDetail(HttpServletRequest request,HttpServletResponse response,Model model, User user, @PathVariable Long goodsId){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //Redis中获取页面，如果不为空，直接返回页面
        String html = (String) valueOperations.get("goodsDetail:" + goodsId);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        GoodsVO goodsVO= iGoodsVOService.findGoodsDetailByGoodsId(goodsId);
        model.addAttribute("User",user);
        model.addAttribute("goods", goodsVO);
        Date startDate=goodsVO.getStartDate();
        Date date=new Date();
        Date endDate=goodsVO.getEndDate();
        int remianSeconds=0;
        int skillStatus=0;
        if(date.before(startDate)){
            //秒杀还没开始
            remianSeconds=(int)((startDate.getTime()-date.getTime())/1000);

        }else if(date.after(endDate)){
            //秒杀已经结束

            skillStatus=2;
            remianSeconds=-1;
        }else{
            //秒杀进行中
            skillStatus=1;
            remianSeconds=0;
        }
        model.addAttribute("skillStatus",skillStatus);
        model.addAttribute("remainSeconds",remianSeconds);
        // return "goodsDetail";
        //如果为空，手动渲染，存入Redis并返回
        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsDetail",
                context);
        if (!StringUtils.isEmpty(html)) {
            valueOperations.set("goodsDetail:" + goodsId, html, 60,
                    TimeUnit.SECONDS);
        }
        return html;
        //return "goodsDetail";
    }

}
