package cn.graydove.weblog.service.impl;

import cn.graydove.weblog.mapper.UserMapper;
import cn.graydove.weblog.pojo.JwtUser;
import cn.graydove.weblog.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

        @Resource
        private UserMapper userMapper;

        @Override
        public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            User user = userMapper.selUserByUserName(s);
            return new JwtUser(user);
        }

}
