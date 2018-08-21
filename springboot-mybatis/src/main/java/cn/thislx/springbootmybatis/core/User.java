package cn.thislx.springbootmybatis.core;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: LX
 * @Description:
 * @Date: Created in 15:14 2018/8/20
 * @Modified by:
 */
@Data
@ToString
public class User {

    private String id;

    private String userName;

    private String password;
}
