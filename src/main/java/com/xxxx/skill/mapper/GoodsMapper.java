package com.xxxx.skill.mapper;

import com.xxxx.skill.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.skill.vo.GoodsVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuxuemei
 * @since 2021-10-11
 */
public interface GoodsMapper extends BaseMapper<Goods> {
  List<GoodsVO> list();
  GoodsVO findGoodsDetailByGoodsId(Long goodsId);
}
