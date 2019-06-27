package cn.graydove.weblog.controller;

import cn.graydove.weblog.pojo.Folder;
import cn.graydove.weblog.pojo.Items;
import cn.graydove.weblog.service.FolderService;
import cn.graydove.weblog.service.ItemService;
import cn.graydove.weblog.uitls.ReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

@RestController
@Slf4j
@PropertySource("classpath:config.properties")
public class DownloadConctoller {

    @Value("${attachment.path}")
    private String path;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Resource
//    private FolderService folderService;

    @Resource
    private ItemService itemService;

    @GetMapping("/down/{id}/{uuid}")
    public void download(@PathVariable("id") Integer id,@PathVariable("uuid")String uuid, HttpServletResponse response){
        Items items = itemService.selItemByItemsId(id);
        if(!items.getUuid().equals(uuid)){
            return;
        }
        ReturnUtil.retFile(response,items,path);

//        Folder folder = folderService.selFolderByFolderId(items.getFolderId());
//        switch (folder.getType()){
//            case Folder.PRIVATE :
//                break;
//            case Folder.PROTECTED:
//                if(password==null)
//                    break;
//                if(!folder.getPassword().equals(bCryptPasswordEncoder.encode(password)))
//                    break;
//            case Folder.PUBLIC:
//                ReturnUtil.retFile(response,items,path);
//        }
    }

//    @GetMapping("/admin/down/{id}")
//    public void adminDownload(@PathVariable("id") Integer id, int userId, HttpServletResponse response){
//        Items items = itemService.selItemByItemsId(id);
//
//        Folder folder = folderService.selFolderByFolderId(items.getFolderId());
//
//        if(folder.getUserId() != userId){
//            response.setStatus(403);
//            return;
//        }
//
//        ReturnUtil.retFile(response,items,path);
//    }
}
