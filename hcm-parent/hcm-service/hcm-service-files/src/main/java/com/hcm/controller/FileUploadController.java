package com.hcm.controller;

import com.hcm.file.FastDFSFile;
import com.hcm.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="/upload")
@CrossOrigin
public class FileUploadController {
    /**
     * 文件上传
     */
    @PostMapping
    public Result upload(@RequestParam(value="file")MultipartFile file) throws Exception {
        //封装文件信息
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(), //文件名字
                file.getBytes(), //文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename()) //获取文件扩展名
        );

        //调用FastDFSUtil工具类将文件传入到FastDFS中
        String[] uplodas = FastDFSUtil.upload(fastDFSFile);
        //拼接返回地址 url = http://192.168.31.254:8080/group1/MOO/00/00/xxx.jpg
       // String url = "http://192.168.31.254:8080/" + uplodas[0] + "/" + uplodas[1];
        String url = FastDFSUtil.getTrackerInfo() + "/" + uplodas[0] + "/" + uplodas[1];
        return Result.success(new StatusCode().OK, "上传成功", url);

    }
}
