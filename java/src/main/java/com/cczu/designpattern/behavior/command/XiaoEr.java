package com.cczu.designpattern.behavior.command;

import com.cczu.designpattern.behavior.command.service.ICuisine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yjz
 * @date 2022/1/29
 */
public class XiaoEr {
    private Logger logger = LoggerFactory.getLogger(XiaoEr.class);
    private List<ICuisine> cuisineList = new ArrayList<>();

    public void order(ICuisine cuisine) {
        cuisineList.add(cuisine);
    }

    public synchronized void placeOrder() {
        for (ICuisine iCuisine : cuisineList) {
            iCuisine.cook();
        }
        cuisineList.clear();
    }
}
