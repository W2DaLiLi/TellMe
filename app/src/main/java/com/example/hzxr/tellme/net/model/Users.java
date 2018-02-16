package com.example.hzxr.tellme.net.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Hzxr on 2018/2/16.
 */
@Root(name = "users", strict = false)
public class Users {
    @ElementList(entry = "user", inline = true)
    public List<User> userList;

    public Users() {}

    public Users(List<User> list) {
        this.userList = list;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }
}
