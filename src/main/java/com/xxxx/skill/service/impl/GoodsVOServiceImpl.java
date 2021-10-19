package com.xxxx.skill.service.impl;

import com.xxxx.skill.mapper.GoodsMapper;
import com.xxxx.skill.service.IGoodsVOService;
import com.xxxx.skill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsVOServiceImpl implements IGoodsVOService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsVO> list() {
        return goodsMapper.list();
    }
    @Override

    public GoodsVO findGoodsDetailByGoodsId(Long goodsId){

        return goodsMapper.findGoodsDetailByGoodsId(goodsId);
    }
}
