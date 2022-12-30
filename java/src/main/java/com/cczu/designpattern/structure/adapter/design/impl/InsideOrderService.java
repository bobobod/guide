package com.cczu.designpattern.structure.adapter.design.impl;


import com.cczu.designpattern.structure.adapter.design.OrderAdapterService;
import com.cczu.designpattern.structure.adapter.service.OrderService;

/**
 * @author yjz
 * @date 2021/11/11
 */
public class InsideOrderService implements OrderAdapterService {
    private OrderService orderService = new OrderService();

    @Override
    public boolean isFirst(String uid) {
        return orderService.queryUserOrderCount(uid) <= 1;
    }
}
