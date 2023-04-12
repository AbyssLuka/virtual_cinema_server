package com.luka.r18.controller;

import com.luka.r18.entity.AnimeFileEntity;
import com.luka.r18.entity.response_object.VideoData;
import com.luka.r18.entity.response_object.FileListData;
import com.luka.r18.service.AnimeInfoLinkService;
import com.luka.r18.service.impl.AnimeFileServiceImpl;
import com.luka.r18.util.CustomUtil;
import com.luka.r18.util.NonStaticResourceHttpRequestHandler;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/file")
public class FileController {

    @Resource
    private AnimeFileServiceImpl animeFileService;

    @ResponseBody

    
    @RequestMapping(path = {"/video"}, method = RequestMethod.GET)
    public String animeList(@RequestParam String uuid) {
        if (uuid == null) {
            return CustomUtil.toJson(400, "参数异常");
        }
        VideoData animeEntity = animeFileService.selectVideoByUuid(uuid);
        return CustomUtil.toJson(200, "", animeEntity);
    }

    @ResponseBody
    @RequestMapping(path = {"/subdirectory"}, method = RequestMethod.POST)
    public String animeListPath(@RequestBody Map<String, String> map) {
        String directoryUuid = map.get("directoryUuid");
        List<AnimeFileEntity> animeFileEntities = animeFileService.selectAnimeByParentFolder(directoryUuid);
        return toJson(animeFileEntities);
    }

    private String toJson(List<AnimeFileEntity> animeFileEntities) {
        List<FileListData> resultList = new ArrayList<>();
        for (AnimeFileEntity animeFileEntity : animeFileEntities) {
            FileListData result = new FileListData();
            result.setFileName(animeFileEntity.getFileName());
            result.setParentFolder(animeFileEntity.getParentFolder());
            result.setFileUuid(animeFileEntity.getUuid());
            result.setFileType(animeFileEntity.getType());
            result.setFileSize(animeFileEntity.getFileSize());
            result.setLastEditTime(animeFileEntity.getLastEditTime().toString());
            resultList.add(result);
        }
        return CustomUtil.toJson(200, "", resultList);
    }

    @ResponseBody
    @RequestMapping(path = {"/searchFile"}, method = RequestMethod.GET)
    public String searchFile(@RequestParam String q) {
        List<AnimeFileEntity> animeFileEntities = animeFileService.selectAnimeByNameKeyword(q);
        return toJson(animeFileEntities);
    }


    @Resource
    private NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    @Value("${warehouse.path}")
    private String warehousePath;

    @Resource
    AnimeFileServiceImpl animeService;

    @RequestMapping(path = {"/video/{uuid}"}, method = RequestMethod.GET)
    public void videoPreview(HttpServletRequest request, HttpServletResponse response, @PathVariable("uuid") String uuid) throws Exception {
        AnimeFileEntity animeEntity = animeService.selectFileByUuid(uuid);
        if (animeEntity == null) return;

        String path = warehousePath + animeEntity.getAbsolutePath();
        Path filePath = Paths.get(path);
        if (Files.exists(filePath)) {
            String mimeType = Files.probeContentType(filePath);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            response.addHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(animeEntity.getFileName(), StandardCharsets.UTF_8));
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }

    @RequestMapping(path = {"/{uuid}"}, method = RequestMethod.GET)
    public void download(@PathVariable("uuid") String uuid, HttpServletResponse response) {
        AnimeFileEntity animeFileEntity = animeService.selectFileByUuid(uuid);
        if (animeFileEntity == null) return;
        System.out.println(animeFileEntity.getAbsolutePath());
        System.out.println(warehousePath + animeFileEntity.getAbsolutePath());

        try {
            byte[] buffer = CustomUtil.getFile(warehousePath + animeFileEntity.getAbsolutePath());
            if (buffer == null) {
                return;
            }
            CustomUtil.setFileResponse(response, animeFileEntity.getFileName(), buffer);
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    /*
    缩略图
     */
    @RequestMapping(path = {"/thumbnail/{uuid}"}, method = RequestMethod.GET)
    public void thumbnail(@PathVariable("uuid") String uuid, HttpServletResponse response) {
        AnimeFileEntity animeSubtitleEntity = animeService.selectFileByUuid(uuid);
        try (OutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {
            Thumbnails.of(new File(warehousePath + animeSubtitleEntity.getAbsolutePath()))
                    .size(160, 160)
//                    .outputFormat("png")
                    .toOutputStream(outputStream); // 写入输出流
        } catch (IOException ex) {
            ex.getMessage();
        }
    }


    @Resource
    AnimeInfoLinkService animeInfoLinkService;

    @RequestMapping(path = {"/subtitle/{videoUuid}"}, method = RequestMethod.GET)
    public void subtitle(@PathVariable("videoUuid") String videoUuid, HttpServletResponse response) {
        String subtitlePath = animeInfoLinkService.querySubtitlePath(videoUuid);
        try (OutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buffer = CustomUtil.getFile(warehousePath + subtitlePath);
            if (buffer == null) return;
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ignored) {
        }
    }
}

