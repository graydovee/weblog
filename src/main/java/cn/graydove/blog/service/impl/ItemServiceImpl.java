package cn.graydove.blog.service.impl;

import cn.graydove.blog.mapper.ItemMapper;
import cn.graydove.blog.pojo.Items;
import cn.graydove.blog.service.ItemService;

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
