package cn.graydove.weblog.service.impl;

import cn.graydove.weblog.mapper.UserMapper;
import cn.graydove.weblog.pojo.User;
import cn.graydove.weblog.service.FansService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FansServiceImpl implements FansService {

    @Resource
    UserMapper userMapper;

    @Override
    public List<User> selFansList(int id) {
        return userMapper.getFansList(id);
    }

    @Override
    public Integer selFansNumber(int id) {
        return userMapper.getFansNumber(id);
    }

    @Override
    public List<User> selFocusList(int id) {
        return userMapper.getFocusList(id);
    }

    @Override
    public int selFocusNumber(int id) {
        return userMapper.getFocusNumber(id);
    }

    @Override
    public int insfocus(int focuser, int focused) {
        return userMapper.focus(focuser, focused);
    }

    @Override
    public int delfocus(int focuser, int focused) {
        return userMapper.defocus(focuser, focused);
    }
}
