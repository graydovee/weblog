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
public class Items {
    private int itemsId;
    private String uuid;
    private String name;
    private String format;
    private Date createTime;
    private int folderId;
}
