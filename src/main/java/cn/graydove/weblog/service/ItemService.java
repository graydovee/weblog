package cn.graydove.weblog.service;

import cn.graydove.weblog.pojo.Items;

import java.util.List;

public interface ItemService {

    List<Items> selItemByUserId(int userId);

    int insItem(Items item);

    int delItemByItemId(int itemId);

    Items selItemByItemsId(int itemsId);
}
