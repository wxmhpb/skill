package com.xxxx.skill.vo;

import com.xxxx.skill.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVO {
    private User user;
    private GoodsVO goodsVO;
    private int sKillStatus;
    private int remainSeconds;
}
