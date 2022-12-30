package com.cczu.designpattern.behavior.observe;

import com.alibaba.fastjson.JSON;

/**
 * @author yjz
 * @date 2022/2/5
 */
public class Test {
    public static void main(String[] args) {
        LotteryServiceImpl lotteryService = new LotteryServiceImpl();
        LotteryResult lotteryResult = lotteryService.draw("111111");
        System.out.printf("结果 %s", JSON.toJSON(lotteryResult));
    }
}
