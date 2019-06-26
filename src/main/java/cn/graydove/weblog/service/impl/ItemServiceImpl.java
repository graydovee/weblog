package cn.graydove.weblog.service.impl;

import cn.graydove.weblog.mapper.ItemMapper;
import cn.graydove.weblog.pojo.Items;
import cn.graydove.weblog.service.ItemService;

import javax.annotation.Resource;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Resource
    ItemMapper itemMapper;

    @Override
    public List<Items> selItemByFolderId(int FolderId) {
        return itemMapper.selItemByFolderId(FolderId);
    }

    @Override
    public int insItem(Items item) {
        return itemMapper.insItem(item);
    }

    @Override
    public int delItemByItemId(int itemId) {
        return itemMapper.delItemByItemId(itemId);
    }
}
