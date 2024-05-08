
package com.luka.r18;

import com.luka.r18.entity.FileEntity;
import com.luka.r18.entity.VideoInfoEntity;
import com.luka.r18.entity.VideoViewEntity;
import com.luka.r18.entity.ComicViewEntity;
import com.luka.r18.service.FileService;
import com.luka.r18.service.VideoInfoService;
import com.luka.r18.service.VideoViewService;
import com.luka.r18.service.ComicViewService;
import com.luka.r18.util.CustomUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@SpringBootTest
class InsertDatabase {

    @Resource
    FileService fileService;

    @Resource
    VideoViewService videoViewService;

    @Resource
    VideoInfoService videoInfoService;

    @Resource
    ComicViewService comicViewService;

    //开始的路径
    static String basePath = "E:\\CinemaData";

    @Test
    void main_() {
//        String baseUuid = UUID.randomUUID().toString();
//        String baseUuid = "6500fe9e-f8da-4031-a874-b570a3310cf7";
        String baseUuid = "";
        //录入文件
//        setFileTable(baseUuid);
        List<FileEntity> fileEntities = fileService.selectFileByParentFolder(baseUuid);
        for (FileEntity fileEntity : fileEntities) {
            System.out.println(fileEntity.getFileName());
            if (fileEntity.getFileName().equals("Video")) {
                //生成视频视图
                setViewViewTable(fileEntity.getUuid());
            }
            if (fileEntity.getFileName().equals("Comic")) {
                //生成漫画视图
                setComicViewTable(fileEntity.getUuid());
            }
        }
        //关联视频封面和字幕
        updateCoverAndSubtitle("mkv");
        updateCoverAndSubtitle("mp4");
    }

    /*
    动漫集合
     */
    public void setViewViewTable(String baseUuid) {
        List<FileEntity> fileEntities = fileService.selectFileByParentFolder(baseUuid);
        int count = 0;
        for (FileEntity fileEntity : fileEntities) {
            List<FileEntity> videoViewItemList = fileService.selectFileByParentFolder(fileEntity.getUuid());
            for (FileEntity videoFileEntity : videoViewItemList) {
                VideoViewEntity videoViewEntity = new VideoViewEntity();
                videoViewEntity.setUuid(CustomUtil.getUUID());
                videoViewEntity.setPathUuid(videoFileEntity.getUuid());
                videoViewEntity.setCreateTime(new Date());
                videoViewService.insert(videoViewEntity);
                System.out.printf("插入第 %d 条完成！\n", ++count);
            }
        }
        System.out.println("end");
    }

    /*
     漫画集合
      */
    public void setComicViewTable(String baseUuid) {
        List<FileEntity> fileEntities = fileService.selectFileByParentFolder(baseUuid);
        int count = 0;
        for (FileEntity fileEntity : fileEntities) {
            List<FileEntity> comicViewItemList = fileService.selectFileByParentFolder(fileEntity.getUuid());
            for (FileEntity entity : comicViewItemList) {
                ComicViewEntity comicViewEntity = new ComicViewEntity();
                comicViewEntity.setUuid(CustomUtil.getUUID());
                comicViewEntity.setPathUuid(entity.getUuid());
                comicViewEntity.setInfo("");
                comicViewEntity.setClicks(0);
                comicViewEntity.setCreateTime(new Date());
                comicViewService.insert(comicViewEntity);
                System.out.printf("插入第 %d 条完成！\n", ++count);
            }
        }
        System.out.println("end");
    }

//------------------------------------------------------插入文件------------------------------------------------------

    //文件数量
    static int count = 0;

    //录入文件
    public void setFileTable(String baseUuid) {
        File fs = new File(basePath);
        insertVideoDataRow(basePath, fs.getName(), "", "directory", "", baseUuid, "");
        writeVideoDataTest(fs, baseUuid);
        System.out.println("录入完成。。。");
        System.out.println("共" + count + "个文件。。。");
    }

    /*
    递归查找所有文件
     */
    public void writeVideoDataTest(File files, String parentfolderUuid) {
        for (File f : Objects.requireNonNull(files.listFiles())) {
            //文件夹
            if (f.isDirectory()) {
                //生成UUID
                String uuid = UUID.randomUUID().toString();
                //插入数据
                insertVideoDataRow(f.getAbsolutePath(), f.getName(), parentfolderUuid,
                        "directory", "", uuid, "");
                //递归调用
                writeVideoDataTest(f, uuid);
            }
            //文件
            if (f.isFile()) {
                //文件后缀
                String[] split = f.getName().split("\\.");
                String suffix = split[split.length - 1];
                try {
                    //生成UUID
                    String uuid = UUID.randomUUID().toString();
                    //文件绝对路径
                    File file = new File(f.getAbsolutePath());
                    //生成MD5摘要信息
                    String md5 = DigestUtils.md5Hex(new FileInputStream(file));
                    System.out.println(md5 + "\t" + ++count);
                    //插入数据
                    insertVideoDataRow(f.getAbsolutePath(), f.getName(), parentfolderUuid,
                            suffix, md5, uuid, String.valueOf(f.length()));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /*
    插入数据
     */
    public void insertVideoDataRow(String path, String filename, String parentfolder, String type,
                                   String md5, String uuid, String fileSize) {
        //文件实体类
        FileEntity fileEntity = new FileEntity();
        fileEntity.setUuid(uuid);
        fileEntity.setParentFolder(parentfolder);
        fileEntity.setFileName(filename);
        fileEntity.setType(type);
        //去除盘符
        fileEntity.setAbsolutePath(path.substring(path.indexOf("\\")));
        fileEntity.setMd5(md5);
        fileEntity.setFileSize(fileSize);
        fileEntity.setCreateTime(new Date());
        fileEntity.setLastEditTime(new Date());
        fileService.insert(fileEntity);
    }

    /*
    关联视频的封面和字幕
     */
    public void updateCoverAndSubtitle(String type) {
        //记录关联封面的数量
        int coverCount = 0;
        //记录关联ASS字幕的数量
        int subtitleAssCount = 0;
        //记录关联SSA字幕的数量
        int subtitleSsaCount = 0;
        //MKV视频数量
        int mkvCount = 0;
        //查找所有MKV视频文件记录
        List<FileEntity> mkv = fileService.selectFileByType(type);
        for (FileEntity fileEntity : mkv) {
            //文件名
            String filename = fileEntity.getFileName();
            //关联表对象
            VideoInfoEntity videoInfoEntity = new VideoInfoEntity();
            videoInfoEntity.setUuid(CustomUtil.getUUID());
            //写入VideoInfo视频UUID
            videoInfoEntity.setVideoUuid(fileEntity.getUuid());
            //写入数据库
            videoInfoService.insert(videoInfoEntity);
            coverCount += updateVideoEntity(fileEntity, videoInfoEntity, ".jpg", filename);
            coverCount += updateVideoEntity(fileEntity, videoInfoEntity, ".jpeg", filename);
            coverCount += updateVideoEntity(fileEntity, videoInfoEntity, ".png", filename);
            subtitleSsaCount += updateVideoEntity(fileEntity, videoInfoEntity, ".ssa", filename);
            subtitleAssCount += updateVideoEntity(fileEntity, videoInfoEntity, ".ass", filename);
            System.out.println("更新第\t" + ++mkvCount + "\t条完成。。。");
        }
        System.out.println("VideoCount：\t" + mkv.size());
        System.out.println("CoverCount: \t" + coverCount);
        System.out.println("SubtitleAssCount：\t" + subtitleAssCount);
        System.out.println("SubtitleSsaCount：\t" + subtitleSsaCount);
    }


    /*
    关联视频的封面和字幕
     */
    public int updateVideoEntity(FileEntity fileEntity, VideoInfoEntity videoInfoEntity, String suffix, String filename) {
        String fileName = fileEntity.getFileName().substring(0, filename.lastIndexOf('.')) + suffix;
        List<FileEntity> entityListByName = fileService.selectFileByFileName(fileName);
        boolean isImage = suffix.equals(".jpg") || suffix.equals(".png") || suffix.equals(".jpeg");
        for (FileEntity entityByName : entityListByName) {
            //判断是否再同一文件内
            if (fileEntity.getParentFolder().equals(entityByName.getParentFolder())) {
                if (isImage) {
                    //关联封面
                    videoInfoEntity.setCoverUuid(entityByName.getUuid());
                } else {
                    //关联字幕
                    videoInfoEntity.setSubtitleUuid(entityByName.getUuid());
                }
                //更新数据
                VideoInfoEntity update = videoInfoService.update(videoInfoEntity);
                if (update != null) return 1;
            }
        }
        return 0;
    }
}
