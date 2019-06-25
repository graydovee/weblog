package cn.graydove.blog.service;

import cn.graydove.blog.pojo.User;

import java.util.List;

public interface FansService {

    List<User> selFansList(int id);

    Integer selFansNumber(int id);

    List<User> selFocusList(int id);

    int selFocusNumber(int id);

    int insfocus(int focuser, int focused);

    int delfocus(int focuser, int focused);
}
