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
public class Folder {
    public final static int PUBLIC = 0;
    public final static int PRIVATE = 1;
    public final static int PROTECTED = 2;

    private int folderId;
    private int userId;
    private String name;
    private int type;
    private String password;
    private Date createTime;
}
