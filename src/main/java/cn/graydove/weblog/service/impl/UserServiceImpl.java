package cn.graydove.weblog.service.impl;

import cn.graydove.weblog.exceptions.ParamException;
import cn.graydove.weblog.mapper.UserMapper;
import cn.graydove.weblog.pojo.User;
import cn.graydove.weblog.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User selUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User u;
        u = userMapper.selUserByUsernameAndPassword(user);
        return u;
    }

    @Override
    public int insUser(User user) throws ParamException {
        if(user==null)
            throw new ParamException("user is null");
        int c;
        c = userMapper.insUser(user);
        return c;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public int updPwd(int userId, String newPwd) {
        User u = userMapper.selUserByUserId(userId);
        int c = 0;
        if(u!=null){
            u.setPassword(newPwd);
            c = userMapper.updatePassword(u);
        }
        return c;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public int updMsg(User user) throws ParamException {
        if(user==null)
            throw new ParamException("user is null");

        User u = userMapper.selUserByUserId(user.getUserId());
        int c = 0;
        if(u!=null){
            if(user.getBirth()!=null){
                u.setBirth(user.getBirth());
            }
            if(user.getNickname()!=null){
                u.setNickname(user.getNickname());
            }
            if(user.getSign()!=null){
                u.setSign(u.getSign());
            }
            u.setSex(user.getSex());

            c = userMapper.updateMessage(u);
        }
        return c;
    }
}
