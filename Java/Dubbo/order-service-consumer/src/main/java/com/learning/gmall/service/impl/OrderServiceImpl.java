package com.learning.gmall.service.impl;

import gmall.bean.UserAddress;
import gmall.service.OrderService;
import gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1. 将服务提供者注册到注册中心（暴露服务）
 *   1.1. 导入 Dubbo 依赖
 *   2.2. 配置服务提供者
 * 2. 让服务消费者去注册中心订阅服务提供者的服务地址
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserService userService;

    @Override
    public void initOrder(String userId) {
        System.out.println("用户 ID：" + userId);
        List<UserAddress> addressList = userService.getUserAddressList(userId);
        System.out.println(addressList);
    }
}
