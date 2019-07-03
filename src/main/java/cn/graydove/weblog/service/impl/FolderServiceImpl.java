//package cn.graydove.weblog.service.impl;
//
//import cn.graydove.weblog.exceptions.NotFoundException;
//import cn.graydove.weblog.exceptions.ParamException;
//import cn.graydove.weblog.mapper.FolderMapper;
//import cn.graydove.weblog.pojo.Folder;
//import cn.graydove.weblog.service.FolderService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@Service
//public class FolderServiceImpl implements FolderService {
//
//    @Resource
//    FolderMapper folderMapper;
//
//    @Override
//    public List<Folder> selFolderByUserId(int userId) {
//        return folderMapper.selFolderByUserId(userId);
//    }
//
//    @Override
//    public List<Folder> selNotPrivateFolderByUserId(int userId) {
//        return folderMapper.selNotPrivateFolderByUserId(userId);
//    }
//
//    @Override
//    public Folder selFolderByFolderId(int FolderId) {
//        return folderMapper.selFolderByFolderId(FolderId);
//    }
//
//    @Override
//    public int insFloder(Folder folder) throws ParamException {
//        if(folder==null)
//            throw new ParamException("folder is null");
//        return folderMapper.insFloder(folder);
//    }
//
//    @Override
//    public int delFolderByFolderId(int folderId) {
//        return folderMapper.delFolderByFolderId(folderId);
//    }
//
//    @Override
//    public int updFolderName(Folder folder) throws ParamException, NotFoundException {
//        if(folder==null)
//            throw new ParamException("folder is null");
//        Folder f = folderMapper.selFolderByFolderId(folder.getFolderId());
//
//        if(f==null)
//            throw new NotFoundException("folder is not exist");
//        return folderMapper.updFolderName(folder);
//    }
//}
