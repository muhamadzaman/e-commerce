package com.services.cloudinaryservice.services;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;

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
}
