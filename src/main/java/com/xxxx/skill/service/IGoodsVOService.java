package com.xxxx.skill.service;

import com.xxxx.skill.vo.GoodsVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IGoodsVOService {
    List<GoodsVO> list();
    GoodsVO findGoodsDetailByGoodsId(Long goodsId);
}
