package com.lj.music_server.utils;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import java.io.File;


public class AnyUtils {
    /**
     * 获取播放时长
     *
     * @param File pdfFile
     * @return
     */
    public static long getVideoDuration(File pdfFile)  {
        Encoder encoder = new Encoder();
        MultimediaInfo info = null;
        try {
            info = encoder.getInfo(pdfFile);
        } catch (EncoderException e) {
            throw new RuntimeException(e);
        }
        return info.getDuration() / 1000;
    }

}
