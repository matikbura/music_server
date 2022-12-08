package com.lj.music_server.controller;

import com.lj.music_server.enums.HttpStatusEnum;
import com.lj.music_server.utils.AnyUtils;
import com.lj.music_server.utils.SnowflakeUtils;
import com.lj.music_server.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("upload")
@RestController
public class UploadController {
    String filePath = "C:\\music_data\\" ;
    @RequestMapping("uploadImg")
    public Result<Object> uploadImg(MultipartFile file, String type) {
        String savePath = filePath+"img\\" + type + "\\";
        //保存文件
        String old = file.getOriginalFilename();
        assert old != null;
        String newFileName = "IMG"+SnowflakeUtils.snowflake()+old.substring(old.lastIndexOf("."));
        //保存文件
        try {
            file.transferTo(new java.io.File(savePath+newFileName));
        } catch (Exception e) {
           new Result<>(false, HttpStatusEnum.INTERNAL_SERVER_ERROR, "上传失败");
        }
        return new Result<>(true, HttpStatusEnum.OK, "/img/"+type+"/"+newFileName);
    }
    @RequestMapping("removeImg")
    public Result<Object> removeImg(String path) {
        String savePath = filePath+path;
        //保存文件
        try {
            java.io.File file = new java.io.File(savePath);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            new Result<>(false, HttpStatusEnum.INTERNAL_SERVER_ERROR, "删除失败");
        }
        return new Result<>(true, HttpStatusEnum.OK, "删除成功");
    }


    @RequestMapping("uploadMusic")
    public Result<Object> uploadMusic(MultipartFile file) {
        String savePath = filePath+"music\\";
        //保存文件
        String old = file.getOriginalFilename();
        assert old != null;
        String newFileName = "MUSIC"+SnowflakeUtils.snowflake()+old.substring(old.lastIndexOf("."));
        File saveFile = new File(savePath + newFileName);
        //保存文件
        try {
            file.transferTo(saveFile);
        } catch (Exception e) {
            new Result<>(false, HttpStatusEnum.INTERNAL_SERVER_ERROR, "上传失败");
        }
        //获取音乐文件的时长
        Long duration = AnyUtils.getVideoDuration(saveFile);
        Map<String, Object> map = new HashMap<>();
        map.put("path", "/music/"+newFileName);
        map.put("duration", duration);
        return new Result<>(true, HttpStatusEnum.OK, map);
    }


    @RequestMapping("uploadVideo")
    public Result<Object> uploadVideo(MultipartFile file) {
        String savePath = filePath+"mv\\";
        //保存文件
        String old = file.getOriginalFilename();
        assert old != null;
        String newFileName = "MV"+SnowflakeUtils.snowflake()+old.substring(old.lastIndexOf("."));
        File saveFile = new File(savePath + newFileName);
        //保存文件
        try {
            file.transferTo(saveFile);
        } catch (Exception e) {
            new Result<>(false, HttpStatusEnum.INTERNAL_SERVER_ERROR, "上传失败");
        }
        //获取音乐文件的时长
        Long duration = AnyUtils.getVideoDuration(saveFile);
        Map<String, Object> map = new HashMap<>();
        map.put("path", "/music/"+newFileName);
        map.put("duration", duration);
        return new Result<>(true, HttpStatusEnum.OK, map);
    }
}
