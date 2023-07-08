package com.work.service.impl;

import com.work.mapper.DetailPictureMapper;
import com.work.pojo.DetailPicture;
import com.work.service.DetailPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailPictureServiceImpl implements DetailPictureService {
    @Autowired
    DetailPictureMapper detailPictureMapper;

    @Override
    public int addDetailPicture(DetailPicture detailPicture) {
        return detailPictureMapper.addDetailPicture(detailPicture);
    }

    @Override
    public int deleteDetailPicture(String commodityId) {
        return detailPictureMapper.deleteDetailPicture(commodityId);
    }

    @Override
    public int upDataDetailPicture(DetailPicture detailPicture) {
        return detailPictureMapper.updateDetailPicture(detailPicture);
    }

}
