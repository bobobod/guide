package com.cczu.designpattern.behavior.command;

import com.cczu.designpattern.behavior.command.service.ICuisine;
import com.cczu.designpattern.behavior.command.service.impl.GuangDongCook;
import com.cczu.designpattern.behavior.command.service.impl.GuangdongCuisine;
import com.cczu.designpattern.behavior.command.service.impl.JiangXiCook;
import com.cczu.designpattern.behavior.command.service.impl.JiangXiCuisine;
import com.cczu.designpattern.behavior.command.service.impl.SiChuanCook;
import com.cczu.designpattern.behavior.command.service.impl.SiChuanCuisine;

/**
 * @author yjz
 * @date 2022/1/29
 */
public class Test {
    public static void main(String[] args) {
        // 菜系 + 厨师;⼴广东(粤菜)、江苏(苏菜)、⼭山东(鲁菜)、四川(川菜)
        ICuisine guangDoneCuisine = new GuangdongCuisine(new GuangDongCook());
        JiangXiCuisine jiangSuCuisine = new JiangXiCuisine(new JiangXiCook());
        SiChuanCuisine shanDongCuisine = new SiChuanCuisine(new SiChuanCook());
// 点单
        XiaoEr xiaoEr = new XiaoEr();
        xiaoEr.order(guangDoneCuisine);
        xiaoEr.order(jiangSuCuisine);
        xiaoEr.order(shanDongCuisine);
// 下单
        xiaoEr.placeOrder();
    }
}
