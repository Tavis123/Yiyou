package com.work.mapper;

import com.work.pojo.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
public interface ReportMapper extends BaseMapper<Report> {
    //根据商品id查看是否被举报
    @Select("select * from report where goodsid = #{goodsid}")
    Report selectByGoodsid(Integer goodsid);
}
