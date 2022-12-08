package com.lj.music_server.config;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Component
public class NonStaticResourceHttpRequestHandler extends ResourceHttpRequestHandler {
    public final static String FILE_PATH="file_path";
    @Override
    protected Resource getResource(HttpServletRequest request) throws IOException {
        String path = (String) request.getAttribute(FILE_PATH);
        return new FileSystemResource(path);
    }
}
