
package com.luka.r18;

import com.luka.r18.entity.AnimeFileEntity;
import com.luka.r18.entity.AnimeInfoLinkEntity;
import com.luka.r18.entity.AnimeViewEntity;
import com.luka.r18.entity.ComicViewEntity;
import com.luka.r18.service.AnimeFileService;
import com.luka.r18.service.AnimeInfoLinkService;
import com.luka.r18.service.AnimeViewService;
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
    AnimeFileService animeFileService;

    @Resource
    AnimeViewService animeViewService;

    @Resource
    AnimeInfoLinkService animeInfoLinkService;

    @Resource
    ComicViewService comicViewService;

    @Test
    void main_() {
        //录入文件
//        setFileTable();
        //生成漫画视图
//        setComicViewTable("bb7b01bb-1a96-4281-9fb2-4db1e1529eee");
        //生成动漫视图
        setAnimeViewTable("01de3d74-d5aa-44f8-9d16-af203f44ec3a");
        //关联视频封面和字幕
//        updateCoverAndSubtitle();
    }

    //开始的路径
    static String basePath = "E:\\CinemaDemoData";

    /*
    动漫集合
     */
    public void setAnimeViewTable(String baseUuid) {
        List<AnimeFileEntity> animeFileEntities = animeFileService.selectAnimeByParentFolder(baseUuid);
        int count = 0;
        for (AnimeFileEntity animeFileEntity : animeFileEntities) {
            List<AnimeFileEntity> animeViewItemList = animeFileService.selectAnimeByParentFolder(animeFileEntity.getUuid());
            for (AnimeFileEntity fileEntity : animeViewItemList) {
                AnimeViewEntity animeViewEntity = new AnimeViewEntity();
                animeViewEntity.setUuid(CustomUtil.getUUID());
                animeViewEntity.setPathUuid(fileEntity.getUuid());
                animeViewEntity.setCreateTime(new Date());
                animeViewService.insert(animeViewEntity);
                System.out.printf("插入第 %d 条完成！\n", ++count);
            }
        }
        System.out.println("end");
    }

    /*
     漫画集合
      */
    public void setComicViewTable(String baseUuid) {
        List<AnimeFileEntity> fileEntities = animeFileService.selectAnimeByParentFolder(baseUuid);
        int count = 0;
        for (AnimeFileEntity fileEntity : fileEntities) {
            ComicViewEntity comicViewEntity = new ComicViewEntity();
            comicViewEntity.setUuid(CustomUtil.getUUID());
            comicViewEntity.setPathUuid(fileEntity.getUuid());
            comicViewEntity.setInfo("");
            comicViewEntity.setClick(0);
            comicViewEntity.setCreateTime(new Date());
            comicViewService.insert(comicViewEntity);
            System.out.printf("插入第 %d 条完成！\n", ++count);
        }
        System.out.println("end");
    }

//------------------------------------------------------插入文件------------------------------------------------------

    //文件数量
    static int count = 0;

    //录入文件
    public void setFileTable() {
        File fs = new File(basePath);
        String baseUuid = UUID.randomUUID().toString();
        insertAnimeDataRow(basePath, fs.getName(), "", "directory", "", baseUuid, "");
        writeAnimeDataTest(fs, baseUuid);
        System.out.println("录入完成。。。");
        System.out.println("共" + count + "个文件。。。");
    }

    /*
    递归查找所有文件
     */
    public void writeAnimeDataTest(File files, String parentfolderUuid) {
        for (File f : Objects.requireNonNull(files.listFiles())) {
            //文件夹
            if (f.isDirectory()) {
                //生成UUID
                String uuid = UUID.randomUUID().toString();
                //插入数据
                insertAnimeDataRow(f.getAbsolutePath(), f.getName(), parentfolderUuid,
                        "directory", "", uuid, "");
                //递归调用
                writeAnimeDataTest(f, uuid);
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
                    insertAnimeDataRow(f.getAbsolutePath(), f.getName(), parentfolderUuid,
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
    public void insertAnimeDataRow(String path, String filename, String parentfolder, String type,
                                   String md5, String uuid, String fileSize) {
        //文件实体类
        AnimeFileEntity animeFileEntity = new AnimeFileEntity();
        animeFileEntity.setUuid(uuid);
        animeFileEntity.setParentFolder(parentfolder);
        animeFileEntity.setFileName(filename);
        animeFileEntity.setType(type);
        //去除盘符
        animeFileEntity.setAbsolutePath(path.substring(path.indexOf("\\")));
        animeFileEntity.setMd5(md5);
        animeFileEntity.setFileSize(fileSize);
        animeFileEntity.setCreateTime(new Date());
        animeFileEntity.setLastEditTime(new Date());
        animeFileService.insert(animeFileEntity);
    }

    /*
    关联视频的封面和字幕
     */
    public void updateCoverAndSubtitle() {
        //记录关联封面的数量
        int coverCount = 0;
        //记录关联ASS字幕的数量
        int subtitleAssCount = 0;
        //记录关联SSA字幕的数量
        int subtitleSsaCount = 0;
        //MKV视频数量
        int mkvCount = 0;
        //查找所有MKV视频文件记录
        List<AnimeFileEntity> mkv = animeFileService.selectAnimeFileByType("mkv");
        for (AnimeFileEntity animeFileEntity : mkv) {
            //文件名
            String filename = animeFileEntity.getFileName();
            //关联表对象
            AnimeInfoLinkEntity animeInfoLinkEntity = new AnimeInfoLinkEntity();
            animeInfoLinkEntity.setUuid(CustomUtil.getUUID());
            //写入AnimeInfoLink视频UUID
            animeInfoLinkEntity.setVideoUuid(animeFileEntity.getUuid());
            //写入数据库
            animeInfoLinkService.insert(animeInfoLinkEntity);
            coverCount += updateAnimeEntity(animeFileEntity, animeInfoLinkEntity, ".jpg", filename);
            subtitleSsaCount += updateAnimeEntity(animeFileEntity, animeInfoLinkEntity, ".ssa", filename);
            subtitleAssCount += updateAnimeEntity(animeFileEntity, animeInfoLinkEntity, ".ass", filename);
            System.out.println("更新第\t" + ++mkvCount + "\t条完成。。。");
        }
        System.out.println("AnimeCount：\t" + mkv.size());
        System.out.println("CoverCount: \t" + coverCount);
        System.out.println("SubtitleAssCount：\t" + subtitleAssCount);
        System.out.println("SubtitleSsaCount：\t" + subtitleSsaCount);
    }


    /*
    关联视频的封面和字幕
     */
    public int updateAnimeEntity(AnimeFileEntity animeEntity, AnimeInfoLinkEntity animeInfoLinkEntity, String suffix, String filename) {
        String fileName = animeEntity.getFileName().substring(0, filename.lastIndexOf('.')) + suffix;
        List<AnimeFileEntity> entityListByName = animeFileService.selectAnimeFileByFileName(fileName);
        boolean isImage = suffix.equals(".jpg") || suffix.equals(".png") || suffix.equals(".jpeg");
        for (AnimeFileEntity entityByName : entityListByName) {
            //判断是否再同一文件内
            if (animeEntity.getParentFolder().equals(entityByName.getParentFolder())) {
                if (isImage) {
                    //关联封面
                    animeInfoLinkEntity.setCoverUuid(entityByName.getUuid());
                } else {
                    //关联字幕
                    animeInfoLinkEntity.setSubtitleUuid(entityByName.getUuid());
                }
                //更新数据
                AnimeInfoLinkEntity update = animeInfoLinkService.update(animeInfoLinkEntity);
                if (update != null) return 1;
            }
        }
        return 0;
    }
}
