package cn.graydove.blog;

import cn.graydove.blog.mapper.*;
import cn.graydove.blog.pojo.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Resource
    UserMapper userMapper;

    @Resource
    BlogMapper blogMapper;

    @Resource
    CommentMapper commentMapper;

    @Resource
    ItemMapper itemMapper;

    @Resource
    FolderMapper folderMapper;

    @Resource
    LikesMapper likesMapper;

    @Test
    public void userMapperTest(){

        //测试插入
        User u = new User();
        u.setUsername("test1");
        u.setPassword("123");
        u.setNickname("t1");

        int c = userMapper.insUser(u);
        Assert.assertNotEquals(c,0);

        u.setUsername("test2");
        u.setPassword("123");
        u.setNickname("t2");

        c = userMapper.insUser(u);
        Assert.assertNotEquals(c,0);

        //测试查询
        u = new User();
        u.setUsername("test1");
        u.setPassword("123");
        User user1 = userMapper.selUserByUsernameAndPassword(u);

        u = new User();
        u.setUsername("test2");
        u.setPassword("123");
        User user2 = userMapper.selUserByUsernameAndPassword(u);
        Assert.assertNotNull(user1);
        Assert.assertNotNull(user2);

        System.out.println(user1);
        //测试更新
        user1.setPassword("321");
        userMapper.updatePassword(user1);
        User temp = userMapper.selUserByUsernameAndPassword(user1);
        Assert.assertEquals(temp,user1);

        String nickname = "t3";
        int sex = 1;
        Date birth = null;
        try {
            birth = new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2008-8-8").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String sign = "222333AAACBBB";
        user2.setNickname(nickname);
        user2.setSex(sex);
        user2.setBirth(birth);
        user2.setSign(sign);
        userMapper.updateMessage(user2);
        temp = userMapper.selUserByUsernameAndPassword(user2);

        Assert.assertEquals(sex,temp.getSex());
        Assert.assertNotNull(temp.getBirth());
        Assert.assertEquals(sign,temp.getSign());
        Assert.assertEquals(nickname,temp.getNickname());

        User t2 = userMapper.selUserByUserId(temp.getUserId());
        Assert.assertEquals(t2,temp);

        int f1 = userMapper.getFansNumber(user1.getUserId());
        int f2 = userMapper.getFocusNumber(user2.getUserId());

        //测试关注
        userMapper.focus(user2.getUserId(),user1.getUserId());


        //测试查询粉丝
        List<User> l1 = userMapper.getFansList(user1.getUserId());
        List<User> l2 = userMapper.getFocusList(user2.getUserId());

        //测试查询粉丝数
        int c1 = userMapper.getFansNumber(user1.getUserId());
        int c2 = userMapper.getFocusNumber(user2.getUserId());

        Assert.assertNotNull(l1);
        Assert.assertNotNull(l2);
        Assert.assertEquals(c1,f1+1);
        Assert.assertEquals(c2,f2+1);
        Assert.assertEquals(l1.size(),f1+1);
        Assert.assertEquals(l2.size(),f2+1);

        //测试取关
        userMapper.defocus(user2.getUserId(),user1.getUserId());

        c1 = userMapper.getFansNumber(user1.getUserId());
        c2 = userMapper.getFocusNumber(user2.getUserId());

        Assert.assertEquals(c1,f1);
        Assert.assertEquals(c2,f2);

        //测试销毁用户
        c1 = userMapper.delUserByUserId(user1.getUserId());
        c2 = userMapper.delUserByUserId(user2.getUserId());

        Assert.assertEquals(c1,1);
        Assert.assertEquals(c2,1);

    }


    @Test
    public void blogMapperTest(){
        //测试插入
        String content = "TestMessage";
        Blog blog = new Blog();
        blog.setUserId(1);
        blog.setContent(content);

        int c = blogMapper.insBlog(blog);
        Assert.assertNotEquals(c,0);

        //测试查询
        List<Blog> blogs = blogMapper.selBlogsByUserId(1);
        Assert.assertNotNull(blogs);

        Blog b = blogs.get(0);
        Assert.assertEquals(b.getContent(),content);

        //测试更新
        b.setContent(b.getContent()+"suffix");
        c = blogMapper.updateBlog(b);

        Assert.assertNotEquals(c,0);

        blog = blogMapper.selBlogByBlogId(b.getBlogId());
        Assert.assertEquals(b.getContent(),blog.getContent());

        //测试删除
        c = blogMapper.delBlogByBlogId(blog.getBlogId());
        Assert.assertNotEquals(c,0);

        b = blogMapper.selBlogByBlogId(blog.getBlogId());
        Assert.assertNull(b);


    }

    @Test
    public void CommentMapperTest(){
        //测试插入
        String content = "TestMessage";
        Comments comments = new Comments();
        comments.setUserId(1);
        comments.setContent(content);
        comments.setBlogId(1);

        int c = commentMapper.insComment(comments);
        Assert.assertNotEquals(c,0);

        //测试查询
        List<Comments> commentsList = commentMapper.selCommentsByUserId(1);
        Assert.assertNotNull(commentsList);

        commentsList = commentMapper.selCommentsByBlogId(1);
        Assert.assertNotNull(commentsList);

        Comments comments1 = commentsList.get(0);
        Assert.assertEquals(comments1.getContent(),content);

        //测试删除
        c = commentMapper.delByCommentsId(comments1.getCommentsId());
        Assert.assertNotEquals(c,0);

        commentsList = commentMapper.selCommentsByBlogId(comments1.getBlogId());
        Assert.assertTrue(commentsList==null || commentsList.size()==0);

    }

    @Test
    public void folderMapperTest(){
        String name = "t1";
        String pwd = "123321";

        //测试新增
        Folder folder = new Folder();
        folder.setName(name);
        folder.setType(Folder.PRIVATE);
        folder.setPassword(pwd);
        folder.setUserId(1);
        int c = folderMapper.insFloder(folder);
        Assert.assertEquals(c,1);

        //测试查询

        List<Folder> folders = folderMapper.selNotPrivateFolderByUserId(1);
        Assert.assertEquals(folders.size(),0);

        folders = folderMapper.selFolderByUserId(1);
        Assert.assertTrue(folders.size()>0);


        //测试改名
        Folder f = folders.get(0);
        f.setName("222");
        c = folderMapper.updFolderName(f);
        Assert.assertEquals(c,1);

        folder = folderMapper.selFolderByUserId(1).get(0);
        Assert.assertEquals(folder.getName(),f.getName());


        //测试删除

        c = folderMapper.delFolderByFolderId(folder.getFolderId());
        Assert.assertEquals(c,1);
        folders = folderMapper.selFolderByUserId(1);
        Assert.assertTrue(folders==null || folders.size()==0);


   }

   @Test
    public void itemMapperTest(){
        UUID uuid = UUID.randomUUID();
        String name = "321a";
        String format = "jpg";
       //测试插入
       Items item = new Items();
       item.setFolderId(1);
       item.setName(name);
       item.setFormat(format);
       item.setUuid(uuid.toString());
       int c = itemMapper.insItem(item);
       Assert.assertEquals(c,1);


       //测试查询
       List<Items> items = itemMapper.selItemByFolderId(1);
       Assert.assertTrue(items.size()>0);
       item = items.get(0);
       Assert.assertEquals(item.getUuid(),uuid.toString());
       Assert.assertEquals(item.getName(),name);
       Assert.assertEquals(item.getFormat(),format);

       //测试删除
       c = itemMapper.delItemByItemId(item.getItemsId());
       Assert.assertEquals(c,1);
       items = itemMapper.selItemByFolderId(1);
       Assert.assertTrue(items==null || items.size()==0);
   }

   @Test
   public void likesMapperTest(){
        int bid = 1;
        int uid = 2;
        //测试新增
        int c = likesMapper.insLike(bid,uid);
        Assert.assertEquals(c,1);
        //测试查询
        c = likesMapper.selLikeNumberByBlogId(bid);
        Assert.assertEquals(c,1);
        //测试删除
       c = likesMapper.delLike(bid,uid);
       Assert.assertEquals(c,1);
       c = likesMapper.selLikeNumberByBlogId(bid);
       Assert.assertEquals(c,0);

   }
}
