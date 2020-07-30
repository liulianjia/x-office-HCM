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
        FastDFSUtil.upload(fastDFSFile);
        return Result.success(new StatusCode().OK, "上传成功");

    }
}
