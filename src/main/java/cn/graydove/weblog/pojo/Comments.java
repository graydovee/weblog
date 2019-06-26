package cn.graydove.weblog.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Comments {
    private int commentsId;
    private String content;
    private Date CreateTime;
    private int userId;
    private int blogId;
}
