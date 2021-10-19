package com.xxxx.skill.vo;

import com.xxxx.skill.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVO  extends Goods {
    private BigDecimal skillPrice;

    /**
     * 库存数量
     */
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

}
