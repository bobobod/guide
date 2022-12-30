package com.cczu.designpattern.structure.bridge;



import com.cczu.designpattern.structure.bridge.channel.WxPay;
import com.cczu.designpattern.structure.bridge.mode.PayfaceMode;

import java.math.BigDecimal;

/**
 * @author yjz
 * @date 2021/11/11
 */
public class Test {
    /**
     * 桥接模式的主要作⽤用就是通过将抽象部分与实现部分分离，把多种可匹配的使⽤用进⾏组合
     * <p>
     * 模拟多支付与多模式的融合
     *
     * @param args
     */
    public static void main(String[] args) {
        WxPay wxPay = new WxPay(new PayfaceMode());
        wxPay.transfer("abc","efg",new BigDecimal("1111"));
    }
}
