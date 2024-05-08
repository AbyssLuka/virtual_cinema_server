package com.luka.r18.controller;

import com.luka.r18.entity.FileEntity;
import com.luka.r18.entity.response_object.VideoData;
import com.luka.r18.entity.response_object.FileListData;
import com.luka.r18.service.VideoInfoService;
import com.luka.r18.service.impl.FileServiceImpl;
import com.luka.r18.util.CustomUtil;
import com.luka.r18.util.NonStaticResourceHttpRequestHandler;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileServiceImpl fileService;

    @ResponseBody
    @RequestMapping(path = {"/video"}, method = RequestMethod.GET)
    public String videoList(@RequestParam String uuid) {
        if (uuid == null) {
            return CustomUtil.toJson(400, "参数异常");
        }
        VideoData fileEntity = fileService.selectVideoByUuid(uuid);
        return CustomUtil.toJson(200, "", fileEntity);
    }

    @ResponseBody
    @RequestMapping(path = {"/subdirectory"}, method = RequestMethod.GET)
    public String videoListPath(@PathParam("uuid") String uuid) {
        List<FileEntity> fileEntities = fileService.selectFileByParentFolder(uuid);
        return toJson(fileEntities);
    }

    private String toJson(List<FileEntity> fileEntities) {
        List<FileListData> resultList = new ArrayList<>();
        for (FileEntity fileEntity : fileEntities) {
            FileListData result = new FileListData();
            result.setFileName(fileEntity.getFileName());
            result.setParentFolder(fileEntity.getParentFolder());
            result.setFileUuid(fileEntity.getUuid());
            result.setFileType(fileEntity.getType());
            result.setFileSize(fileEntity.getFileSize());
            result.setLastEditTime(fileEntity.getLastEditTime().toString());
            result.setAbsolutePath(fileEntity.getAbsolutePath());
            resultList.add(result);
        }
        return CustomUtil.toJson(200, "", resultList);
    }

    @ResponseBody
    @RequestMapping(path = {"/searchFile"}, method = RequestMethod.GET)
    public String searchFile(@RequestParam String q) {
        List<FileEntity> fileEntities = fileService.selectFileByNameKeyword(q);
        return toJson(fileEntities);
    }


    @Resource
    private NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    @Value("${warehouse.path}")
    private String warehousePath;


    @RequestMapping(path = {"/video/{uuid}"}, method = RequestMethod.GET)
    public void videoPreview(HttpServletRequest request, HttpServletResponse response, @PathVariable("uuid") String uuid) throws Exception {
        FileEntity fileEntity = fileService.selectFileByUuid(uuid);
        if (fileEntity == null) return;

        String path = warehousePath + fileEntity.getAbsolutePath();
        Path filePath = Paths.get(path);
        if (Files.exists(filePath)) {
            String mimeType = Files.probeContentType(filePath);
            if (mimeType != null) {
                response.setContentType(mimeType);
            }
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            response.addHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(fileEntity.getFileName(), StandardCharsets.UTF_8));
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }

    @RequestMapping(path = {"/{uuid}"}, method = RequestMethod.GET)
    public void download(@PathVariable("uuid") String uuid, HttpServletResponse response) {
        FileEntity fileEntity = fileService.selectFileByUuid(uuid);
        if (fileEntity == null) return;
        try {
            byte[] buffer = CustomUtil.getFile(warehousePath + fileEntity.getAbsolutePath());
            if (buffer == null) {
                return;
            }
            CustomUtil.setFileResponse(response, fileEntity.getFileName(), buffer);
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /*
    缩略图
     */
    @RequestMapping(path = {"/thumbnail/{uuid}"}, method = RequestMethod.GET)
    public void thumbnail(@PathVariable("uuid") String uuid, HttpServletResponse response) {
        FileEntity fileEntity = fileService.selectFileByUuid(uuid);
        try (OutputStream outputStream = response.getOutputStream()) {
            Thumbnails.of(new File(warehousePath + fileEntity.getAbsolutePath()))
                    .size(160, 160)
//                    .outputFormat("png")
                    .toOutputStream(outputStream); // 写入输出流
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Resource
    VideoInfoService videoInfoService;

    @RequestMapping(path = {"/subtitle/{videoUuid}"}, method = RequestMethod.GET)
    public void subtitle(@PathVariable("videoUuid") String videoUuid, HttpServletResponse response) {
        String subtitlePath = videoInfoService.querySubtitlePath(videoUuid);
        try (OutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buffer = CustomUtil.getFile(warehousePath + subtitlePath);
            if (buffer == null) return;
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ignored) {
        }
    }
}

