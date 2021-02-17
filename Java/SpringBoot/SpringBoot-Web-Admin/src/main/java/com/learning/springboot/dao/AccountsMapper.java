package com.learning.springboot.dao;

import com.learning.springboot.bean.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountsMapper {

    //@Select("select * from accounts where id = #{id}") 或者不写 Mapper.xml 文件，通过注解完成 SQL 语句映射
    Account getById(int id);

    @Select("select * from accounts") // 也可以采用注解和 XML 文件混用的方式（复杂语句写进 XML，简单语句用注解）
    List<Account> getAll();
}
