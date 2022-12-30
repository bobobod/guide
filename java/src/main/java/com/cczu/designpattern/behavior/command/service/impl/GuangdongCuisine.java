package com.cczu.designpattern.behavior.command.service.impl;

import com.cczu.designpattern.behavior.command.service.ICook;
import com.cczu.designpattern.behavior.command.service.ICuisine;

/**
 * @author yjz
 * @date 2022/1/29
 */
public class GuangdongCuisine implements ICuisine {
    private ICook cook;

    public GuangdongCuisine(ICook cook) {
        this.cook = cook;
    }

    @Override
    public void cook() {
        cook.doCooking();
    }
}
