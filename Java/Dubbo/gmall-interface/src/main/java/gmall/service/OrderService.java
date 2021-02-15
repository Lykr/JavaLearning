package gmall.service;

import gmall.bean.UserAddress;

import java.util.List;

public interface OrderService {

    List<UserAddress> initOrder(String userId);
}
