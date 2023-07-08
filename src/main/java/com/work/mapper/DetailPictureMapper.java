package com.work.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.pojo.DetailPicture;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DetailPictureMapper extends BaseMapper<DetailPicture> {

    @Insert("insert into detailpicture(picture1, picture2, picture3, picture4, picture5, commodityId) values (#{picture1}, #{picture2}, #{picture3}, #{picture4}, #{picture5}, #{commodityId})")
    int addDetailPicture(DetailPicture detailPicture);

    @Delete("delete from detailpicture where commodityId = #{commodityId}")
    int deleteDetailPicture(String commodityId);

    @Update("update detailpicture set picture1 = #{picture1}, picture2 = #{picture2}, picture3 = #{picture3}, picture4 = #{picture4}, picture5 = #{picture5} where commodityId = #{commodityId}")
    int updateDetailPicture(DetailPicture detailPicture);
}
