package top.jwtzkl.backend.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.jwtzkl.backend.entity.Result;
import top.jwtzkl.backend.utils.AliOSSUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        //log.info("文件上传: {}, {}, {}", username, age, image);
//        //获取原始文件名 - 1.jpg  123.0.0.jpg
//        String originalFilename = image.getOriginalFilename();
//            //构造唯一的文件名 (不能重复) - uuid(通用唯一识别码) de49685b-61c0-4b11-80fa-c71e95924018
//        if (originalFilename != null) {
//            int index = originalFilename.lastIndexOf(".");
//            String newFileName = UUID.randomUUID().toString() + originalFilename.substring(index);
//
//            log.info("新的文件名: {}", newFileName);
//            image.transferTo(new File("D:\\Project\\Springboot\\utils\\"+newFileName));
//        }
//
//        return Result.success();
//    }

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传, 文件名: {}", image.getOriginalFilename());

        //调用阿里云OSS工具类进行文件上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成,文件访问的url: {}", url);

        return Result.success(url);
    }


}
