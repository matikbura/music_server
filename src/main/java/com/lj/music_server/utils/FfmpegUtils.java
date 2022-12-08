package com.lj.music_server.utils;

import org.bytedeco.javacpp.Loader;

import java.util.ArrayList;

public class FfmpegUtils {
    //转码
    public static void videoChange(String filePath,String targetPath,String type) {
        ArrayList<String> command = new ArrayList<>();
        //新文件路径
        command.add(Loader.load(org.bytedeco.ffmpeg.ffmpeg.class));
        command.add("-i");
        command.add(filePath);
        command.add("-vf");
        switch (type) {
            case "低品质":
                command.add("scale=320:180");
                break;
            case "标清":
                command.add("scale=640:360");
                break;
        }
        command.add("-b:v");
        command.add("200k");
        command.add("-c:v");
        command.add("h264_nvenc");
        command.add("-acodec");
        command.add("copy");
        command.add("-crf");
        command.add("28");
        command.add(targetPath);


        command.add("-hide_banner");
        try {
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}