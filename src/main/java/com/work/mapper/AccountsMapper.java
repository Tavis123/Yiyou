package com.work.mapper;

import com.work.pojo.Accounts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface AccountsMapper extends BaseMapper<Accounts> {

}
