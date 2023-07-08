package com.work.service;

import com.work.pojo.DetailPicture;

public interface DetailPictureService {
    int addDetailPicture(DetailPicture detailPicture);

    int deleteDetailPicture(String commodityId);

    int upDataDetailPicture(DetailPicture detailPicture);
}
