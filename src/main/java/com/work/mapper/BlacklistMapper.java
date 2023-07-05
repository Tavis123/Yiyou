package com.work.mapper;

import com.work.pojo.Blacklist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface BlacklistMapper extends BaseMapper<Blacklist> {

}
