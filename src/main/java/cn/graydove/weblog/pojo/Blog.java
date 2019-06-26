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
public class Blog {
    private int blogId;
    private String Content;
    private Date createTime;
    private int UserId;

}
