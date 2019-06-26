package cn.graydove.weblog.service;

import cn.graydove.weblog.pojo.Items;

import java.util.List;

public interface ItemService {

    List<Items> selItemByFolderId(int FolderId);

    int insItem(Items item);

    int delItemByItemId(int itemId);
}
