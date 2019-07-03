package cn.graydove.weblog.service.impl;

import cn.graydove.weblog.mapper.ItemMapper;
import cn.graydove.weblog.pojo.Items;
import cn.graydove.weblog.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    ItemMapper itemMapper;

    @Override
    public List<Items> selItemByUserId(int userId) {
        return itemMapper.selItemByUserId(userId);
    }

    @Override
    public int insItem(Items item) {
        return itemMapper.insItem(item);
    }

    @Override
    public int delItemByItemId(int itemId) {
        return itemMapper.delItemByItemId(itemId);
    }

    @Override
    public Items selItemByItemsId(int itemsId) {
        return itemMapper.selItemByItemsId(itemsId);
    }
}
