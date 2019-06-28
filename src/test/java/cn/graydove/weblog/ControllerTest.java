package cn.graydove.weblog;


import cn.graydove.weblog.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

    @Test
    public void test(){
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        for(User u:users){
            u.setPassword("1");
        }
    }
}
