package com.lj.music_server.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.http.server.HttpServerBase;
import com.lj.music_server.config.NonStaticResourceHttpRequestHandler;
import com.lj.music_server.utils.FfmpegUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class CompressionController {
    @Autowired
    private NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;
    //图片压缩
    @GetMapping(value = {"/img/{type}/{path}"})
    public void compressImage(@PathVariable(required = true) String path, @PathVariable(required = true) String type, String cpNum, HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException {
        //默认品质最低
        if (cpNum == null) {
            cpNum = "1";
        }
        //获取文件后缀
        String suffix = path.substring(path.lastIndexOf(".") + 1);
        //获取文件名
        String fileName = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
        String tageFileName = "";
        //如果品质不是最高
        if (!cpNum.equals("height")) {
            tageFileName = fileName + "_" + cpNum + "." + suffix;
        } else {
            tageFileName = fileName + "." + suffix;
        }
        String outPutFilePath = "C:\\music_data\\img" + File.separator + type + File.separator + tageFileName;
        //文件是否存在
        if (FileUtil.exist(outPutFilePath)) {
            System.out.println("文件存在");
            request.setAttribute(NonStaticResourceHttpRequestHandler.FILE_PATH, outPutFilePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            System.out.println("文件不存在");
            String heightFilePath = "C:\\music_data\\img" + File.separator + type + File.separator + path;
            File file = FileUtil.file(heightFilePath);
            if (FileUtil.exist(heightFilePath)) {
                System.out.println("文件存在");
                float cpNumFloat = Float.parseFloat(cpNum) / 10;
                ImgUtil.scale(file, FileUtil.file(outPutFilePath), cpNumFloat);
                request.setAttribute(NonStaticResourceHttpRequestHandler.FILE_PATH, outPutFilePath);
                nonStaticResourceHttpRequestHandler.handleRequest(request, response);
            } else {
                throw new RuntimeException("最高品质的图片不存在");
            }
        }

        //TODO
    }

    @GetMapping(value = {"/music/{path}"})
    public void compressMusic(@PathVariable String path, HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException {
        String filePath = "C:\\music_data\\music" + File.separator + path;
        request.setAttribute(NonStaticResourceHttpRequestHandler.FILE_PATH, filePath);
        nonStaticResourceHttpRequestHandler.handleRequest(request, response);
    }

    @GetMapping(value = {"/mv/{path}"})
    public void compressVideo(@PathVariable String path, String type, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {

        String filePath = "C:\\music_data\\mv" + File.separator + path;
        String suffix = path.substring(path.lastIndexOf(".") + 1);
        String fileName = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
        String tageFileName = "";
        if (type==null){
            type="低品质";
        }
        //如果品质不是最高
        if (!type.equals("高品质")) {
            tageFileName = fileName + "_" + type + "." + suffix;
        } else {
            tageFileName = fileName + "." + suffix;
        }
        String outPutFilePath = "C:\\music_data\\mv" + File.separator + tageFileName;
        if (FileUtil.exist(outPutFilePath)) {
            System.out.println("文件存在");
            request.setAttribute(NonStaticResourceHttpRequestHandler.FILE_PATH, outPutFilePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            System.out.println("文件不存在");
            String heightFilePath = "C:\\music_data\\mv" + File.separator + path;
            File file = FileUtil.file(heightFilePath);
            if (FileUtil.exist(heightFilePath)) {
                System.out.println("文件存在");
                FfmpegUtils.videoChange(filePath, outPutFilePath, type);
                request.setAttribute(NonStaticResourceHttpRequestHandler.FILE_PATH, outPutFilePath);
                nonStaticResourceHttpRequestHandler.handleRequest(request, response);
            } else {
                throw new RuntimeException("最高品质的图片不存在");
            }
        }

    }
}
