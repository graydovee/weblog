package cn.graydove.blog.service;

import cn.graydove.blog.exceptions.ParamException;
import cn.graydove.blog.pojo.User;

public interface UserService {
    User selUser(String username, String password);

    int insUser(User u) throws ParamException;

    int updPwd(int userId,String newPwd);

    int updMsg(User u) throws ParamException;
}
