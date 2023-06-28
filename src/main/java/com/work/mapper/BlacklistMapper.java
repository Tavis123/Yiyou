package com.work.mapper;

import com.work.pojo.Blacklist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuqi
 * @since 2023-05-13
 */
@Mapper
public interface BlacklistMapper extends BaseMapper<Blacklist> {

}
