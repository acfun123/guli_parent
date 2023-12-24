package com.cc.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.cc.Result;
import com.cc.utils.OosProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/file")
@CrossOrigin
@Slf4j
public class FileController {
    @PostMapping("/upload")
    public Object upload(MultipartFile file){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OosProperties.END_POINE, OosProperties.ACCESS_KEY, OosProperties.ACCESS_KEY_SECRET);

        try {
            log.info("Hello OSS");
            String fileName = DateUtil.format(new Date(), "yyyy/MM/dd/") + UUID.randomUUID().toString()
                    .replace("-", "") + file.getOriginalFilename();
            ossClient.putObject(OosProperties.BUCKET_NAME, fileName, file.getInputStream());
            return Result.ok().data("url", String.format("https://%s.%s/%s",OosProperties.BUCKET_NAME, OosProperties.END_POINE, fileName));
        } catch (OSSException oe) {
            log.info("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.info("Error Message:" + oe.getErrorMessage());
            log.info("Error Code:" + oe.getErrorCode());
            log.info("Request ID:" + oe.getRequestId());
            log.info("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            log.info("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.info("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            log.info("获取文件流错误");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return Result.error();
    }
}
