package cn.graydove.weblog.service;

import cn.graydove.weblog.exceptions.ParamException;
import cn.graydove.weblog.pojo.User;

public interface UserService {
    User selUser(String username, String password);

    int insUser(User u) throws ParamException;

    int updPwd(int userId,String newPwd);

    int updMsg(User u) throws ParamException;

    int updProfilePicture(String url,int id);

    User selUserByUserId(int userId);
}
