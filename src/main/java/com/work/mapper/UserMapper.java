package com.work.mapper;

import com.work.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

//һ����˵����ɾ�ķ����ķ���ֵ����int����ʾ�����ɹ���¼�������鷽��һ���Ƿ�����Ӧ������߶����List��
@Mapper
public interface UserMapper extends BaseMapper<User> {

//    //�����û�����ѯ�û�
//    @Select("select * from user where username = #{username}")
//    User selectByUsername(String username);
//
//    //�����û�
//    @Insert("insert into user(username,password,contact_info) values(#{username},#{password},#{contactInfo})")
//    int add(User user);
//
//    //�޸��û���Ϣ
//    @Update("update user set username=#{username},password=#{password},contact_info=#{contactInfo} where id=#{id}")
//    int update(User user);
//
//    //ɾ���û�
//    @Delete("delete from user where id=#{id}")
//    int delete(Integer id);
//
//    //����id��ȡ�û�
//    @Select("select * from user where id=#{id}")
//    User getById(Integer id);
//
//    //�����û�����ȡ�û�
//    @Select("select * from user where username=#{username}")
//    User getByUsername(String username);


}
