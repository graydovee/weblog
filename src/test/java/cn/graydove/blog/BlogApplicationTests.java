package cn.graydove.blog;

import cn.graydove.blog.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({MapperTest.class})
public class BlogApplicationTests {


}
