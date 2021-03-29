package com.fxz.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadAvatar(MultipartFile file);
}
