package cn.thislx.springbootmultisource.dao.primary;

import cn.thislx.springbootmultisource.core.User;
import org.apache.ibatis.annotations.Insert;

public interface UserPrimaryMapper {
//    @Insert("INSERT INTO user(id, userName,password) VALUES(#{id}, #{userName}, #{password})")
    void insert(User user);
}
