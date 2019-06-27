package cn.graydove.weblog.controller.admin;

import cn.graydove.weblog.enums.ServerStatus;
import cn.graydove.weblog.exceptions.ParamException;
import cn.graydove.weblog.pojo.Folder;
import cn.graydove.weblog.pojo.Items;
import cn.graydove.weblog.service.FolderService;
import cn.graydove.weblog.service.ItemService;
import cn.graydove.weblog.uitls.ReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@Slf4j
@PropertySource("classpath:config.properties")
public class AdminFileController {

    @Value("${attachment.path}")
    private String path;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private ItemService itemService;

    @Resource
    private FolderService folderService;

    /**
     * 创建文件夹
     */
    @PostMapping("/folder")
    public String createFolder(Folder folder,int userId){
        if(folder==null)
            return ReturnUtil.retJson(ServerStatus.NULL_PARAM);

        folder.setUserId(userId);
        if(folder.getType()==Folder.PROTECTED){
            String pwd = folder.getPassword();
            if(pwd==null)
                return ReturnUtil.retJson(ServerStatus.PARAM_ERROR);
            else
                folder.setPassword(bCryptPasswordEncoder.encode(pwd));
        }
        try {
            int c = folderService.insFloder(folder);

            if(c>0)
                return ReturnUtil.retJson(ServerStatus.OK);
        } catch (ParamException e) {
            log.error(e.toString(), e);
            return ReturnUtil.retJson(ServerStatus.PARAM_ERROR);
        }
        return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
    }

    /**
     * 获取自己的所有文件夹
     */
    @GetMapping("/folder")
    public String getFolderList(int userId){
        List<Folder> folders= folderService.selFolderByUserId(userId);
        return ReturnUtil.retJson(folders);
    }

    /**
     * 获取自己的该文件夹所有的文件
     */
    @GetMapping("/file")
    public String getFileList(int folderId,int userId){
        Folder f = folderService.selFolderByFolderId(folderId);
        if(f==null || f.getUserId()!=userId)
            return ReturnUtil.retJson(ServerStatus.PARAM_ERROR);

        List<Items> items= itemService.selItemByFolderId(folderId);
        return ReturnUtil.retJson(items);
    }

    /**
     * 上传文件
     */
    @PostMapping("/file")
    public String upload(@RequestParam("file") MultipartFile file,Integer folderId,int userId){
        if (folderId==null || file==null || file.isEmpty()) {
            return ReturnUtil.retJson(ServerStatus.NULL_PARAM);
        }
        String uuid = UUID.randomUUID().toString();

        //文件信息
        String fileName = file.getOriginalFilename();
        File parent = new File(path);
        if(!parent.exists()){
            parent.mkdirs();
        }
        int p = fileName.lastIndexOf(".");
        String suffix = fileName.substring(p+1);
        File dest = new File(parent.getAbsolutePath(),uuid+"."+suffix);


        List<Folder> list = folderService.selFolderByUserId(userId);
        Folder folder = null;
        for(Folder f:list){
            if(folderId.equals(f.getFolderId())){
                folder = f;
                break;
            }
        }

        if(folder==null)
            return ReturnUtil.retJson(ServerStatus.PARAM_ERROR);

        //存入数据库
        Items items = new Items();
        items.setFolderId(folder.getFolderId());
        items.setUuid(uuid);
        items.setFormat(suffix);
        items.setName(fileName.substring(0,p));

        int c = itemService.insItem(items);
        if(c==0)
            return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);

        //保存到本地
        try {
            log.info(items.toString());
            file.transferTo(dest);
            return ReturnUtil.retJson(ServerStatus.OK);
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
    }
}
