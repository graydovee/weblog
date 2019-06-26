package cn.graydove.blog.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    public final static String ROLE = "USER";
    private int userId;
    private String username;
    private String password;
    private String nickname;
    /**
     * 0为男,1为女
     */
    private int sex;
    private Date birth;
    private String sign;
}
