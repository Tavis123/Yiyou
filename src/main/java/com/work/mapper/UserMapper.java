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
    @Update("update user set commodityNum = #{commodityNum} where userId = #{userId}")
    int updateNum(int commodityNum, String userId);

    //�ı�ĳ���˺ŵ����
    @Update("update user set money = #{money} where userId = #{userId}")
    int updateMoney(double money, String userId);

    //�����û�����ѯ��Ӧ��ͷ��
    @Select("select avatar from user where username = #{username};")
    String selectAvatarByUsername(String username);

}
