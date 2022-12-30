package com.cczu.designpattern.behavior.strategy;

import com.cczu.designpattern.behavior.strategy.impl.MJCouponDiscount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yjz
 * @date 2022/2/14
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        // 满100减10，商品100元
        Context<Map<String, String>> context = new Context<Map<String, String>>
                (new MJCouponDiscount());
        Map<String, String> mapReq = new HashMap<String, String>();
        mapReq.put("x", "100");
        mapReq.put("n", "10");
        BigDecimal discountAmount = context.discountAmount(mapReq, new
                BigDecimal(100));
        logger.info("测试结果:满减优惠后⾦金金额 {}", discountAmount);
    }
}
