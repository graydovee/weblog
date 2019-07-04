package cn.graydove.weblog.uitls;

import cn.graydove.weblog.enums.ServerStatus;
import cn.graydove.weblog.pojo.Items;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ReturnUtil {


    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static void retFile(HttpServletResponse response, Items items, String path){

        if(items==null)
            return;


        File parent = new File(path);
        File file;
        if(parent.exists() && parent.isDirectory()){
            file = new File(path,items.getStoreName());
            if(!file.exists()){
                return;
            }
        }else
            return;

        try {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition","attachment;filename="+new String(items.getFileName().getBytes("GBK"),"ISO8859_1"));

            OutputStream os = response.getOutputStream();
            byte[] bytes = FileUtils.readFileToByteArray(file);
            os.write(bytes);
            os.flush();
            os.close();
        } catch (IOException e) {
            log.error(e.toString(),e);
        }

    }

    public static String retJson(ServerStatus serverStatus){
        try {
            return objectMapper.writeValueAsString(setValues(serverStatus));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String retJson(Object obj){
        Map<String, Object> map = setValues(ServerStatus.OK);
        map.put("data",obj);
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static Map<String, Object> setValues(ServerStatus status){
        Map<String, Object> map = new HashMap<>();
        map.put("code",status.getValue());
        map.put("message",status.getReason());
        return map;
    }

}
