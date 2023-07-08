package com.work.mapper;

import com.work.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//һ����˵����ɾ�ķ����ķ���ֵ����int����ʾ�����ɹ���¼�������鷽��һ���Ƿ�����Ӧ������߶����List��
@Mapper
public interface UserMapper extends BaseMapper<User> {

    //�����û�����ѯ�û�
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    //ͨ���˺�����ѯĳ���˻�
    @Select("select * from user where userId = #{userId}")
    User selectUserById(String userId);

    //�ı�ĳ���˺ŵķ�����Ʒ��Ŀ
    @Update("update user set commodity_num = #{commodity_num} where username = #{username}")
    int updateNum(int commodity_num, String username);

    //�ı�ĳ���˺ŵ����
    @Update("update user set money = #{money} where username = #{username}")
    int updateMoney(double money, String username);

    //�����û�����ѯ��Ӧ��ͷ��
    @Select("select avatar from user where username = #{username};")
    String selectAvatarByUsername(String username);

}
