package com.cc.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@ConfigurationProperties(prefix = "aliyun.oos.file")
public class OosProperties {
    private String endpoint;
    private String bucketName;
    private String accessKey;
    private String accessKeySecret;

    @PostConstruct
    public void init() {
        OosProperties.END_POINE = this.endpoint;
        OosProperties.ACCESS_KEY = this.accessKey;
        OosProperties.ACCESS_KEY_SECRET = this.accessKeySecret;
        OosProperties.BUCKET_NAME = this.bucketName;
    }

    // static fields
    public static String END_POINE;
    public static String ACCESS_KEY;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
