package com.learning.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Component //只有在容器中的组件才能用 SpringBoot 提供的功能
@ConfigurationProperties(prefix = "mycar")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Car {

    private String brand;
    private Integer price;
}
