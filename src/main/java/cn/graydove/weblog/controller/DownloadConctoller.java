package cn.graydove.weblog.controller;

import cn.graydove.weblog.enums.ServerStatus;
import cn.graydove.weblog.pojo.Items;
import cn.graydove.weblog.service.ItemService;
import cn.graydove.weblog.uitls.ReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Slf4j
@PropertySource("classpath:config.properties")
public class DownloadConctoller {

    @Value("${attachment.path}")
    private String path;

    @Resource
    private ItemService itemService;

    @GetMapping("/down/{id}/{uuid}")
    public void download(@PathVariable("id") Integer id,@PathVariable("uuid")String uuid, HttpServletResponse response){
        Items items = itemService.selItemByItemsId(id);
        if(!items.getUuid().equals(uuid)){
            return;
        }
        ReturnUtil.retFile(response,items,path);
    }

    @GetMapping("/file")
    public String getpubFile(int id){
        List<Items> list = itemService.selItemByUserId(id);
        if(list!=null)
            return ReturnUtil.retJson(list);
        return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
    }

}
