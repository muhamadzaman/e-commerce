package com.services.cloudinaryservice.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService
{
    Cloudinary cloudinary;
    Map<String, String> cloudinaryCredentials;

    public CloudinaryService()
    {
        cloudinaryCredentials = new HashMap<String, String>();

        cloudinaryCredentials.put("cloud_name", "dbany0njx");
        cloudinaryCredentials.put("api_key", "245573496524586");
        cloudinaryCredentials.put("api_secret", "Tv9-DTQ1jTFGJBB6ZgsgszQFr-I");

        cloudinary = new Cloudinary(cloudinaryCredentials);
    }

    public Map upload(MultipartFile uploadFile) throws IOException
    {
        File file = convert(uploadFile);
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return uploadResult;
    }
    public Map delete(String publicId) throws IOException
    {
        Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        return result;
    }
    private File convert(MultipartFile uploadFile) throws IOException
    {
        File file = new File(uploadFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(uploadFile.getBytes());
        fo.close();
        return file;
    }
}
