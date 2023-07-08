package com.work.controller;


import com.work.pojo.DetailPicture;
import com.work.service.DetailPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/DetailPicture")
public class DetailPictureController {
    @Autowired
    DetailPictureService detailPictureService;

    @PostMapping("/addDetailPicture")
    public Map<String, String> addDetailPicture(MultipartFile file1,
                                                MultipartFile file2,
                                                MultipartFile file3,
                                                MultipartFile file4,
                                                MultipartFile file5,
                                                String commodityId,
                                                DetailPicture detailPicture) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        String image1 = "", image2 = "", image3 = "", image4 = "", image5 = "";
        if (file1 != null) {
            image1 = encoder.encode(file1.getBytes());
        }
        if (file2 != null) {
            image2 = encoder.encode(file2.getBytes());
        }
        if (file3 != null) {
            image3 = encoder.encode(file3.getBytes());
        }
        if (file4 != null) {
            image4 = encoder.encode(file4.getBytes());
        }
        if (file5 != null) {
            image5 = encoder.encode(file5.getBytes());
        }
        detailPicture.setPicture1(image1);
        detailPicture.setPicture2(image2);
        detailPicture.setPicture3(image3);
        detailPicture.setPicture4(image4);
        detailPicture.setPicture5(image5);
        detailPicture.setCommodityId(commodityId);
        detailPictureService.addDetailPicture(detailPicture);
        Map<String, String> map = new HashMap<>();
        map.put("msg", "´æ·Å³É¹¦");
        return map;
    }


}
