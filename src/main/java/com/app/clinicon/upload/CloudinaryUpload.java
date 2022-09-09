package com.app.clinicon.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class CloudinaryUpload {

    private final Cloudinary cloudinary;

    @SneakyThrows
    public String uploadFile(MultipartFile multipartFile, List<String> extensions){

        if (!extensions.contains(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))) {
            return "";
        }

        File file = convertMultipartToFile(multipartFile);
        Map uploadResut = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();

        return uploadResut.get("url").toString();
    }

    @SneakyThrows
    private File convertMultipartToFile(MultipartFile multipartFile){

        File file = new File(multipartFile.getOriginalFilename());
        
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();

        return file;
    }
    
}
