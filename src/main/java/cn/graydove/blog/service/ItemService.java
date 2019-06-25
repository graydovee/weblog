package cn.graydove.blog.service;

import cn.graydove.blog.pojo.Items;

import java.util.List;

public interface ItemService {

    List<Items> selItemByFolderId(int FolderId);

    int insItem(Items item);

    int delItemByItemId(int itemId);
}
