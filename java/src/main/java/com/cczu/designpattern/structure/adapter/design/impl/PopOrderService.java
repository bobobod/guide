package com.cczu.designpattern.structure.adapter.design.impl;


import com.cczu.designpattern.structure.adapter.design.OrderAdapterService;

/**
 * @author yjz
 * @date 2021/11/11
 */
public class PopOrderService implements OrderAdapterService {
    private PopOrderService popOrderService = new PopOrderService();

    @Override
    public boolean isFirst(String uid) {
        return popOrderService.isFirst(uid);
    }
}
