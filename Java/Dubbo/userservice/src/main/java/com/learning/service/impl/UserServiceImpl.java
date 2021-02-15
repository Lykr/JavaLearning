package com.learning.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import gmall.bean.UserAddress;
import gmall.service.UserService;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress address1 = new UserAddress(1, "地址1", "1", "老王", "12345678909", "Yes");
        UserAddress address2 = new UserAddress(2, "地址2", "2", "小红", "09876543212", "Yes");
        return Arrays.asList(address1, address2);
    }
}
