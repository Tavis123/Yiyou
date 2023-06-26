package com.work;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        //���ܻ�����Ҫ�����г����������ø���
        String url = "jdbc:mysql:///yiyou";
        String username = "root";
        String password = "123456";
        String moduleName = "system";//ģ����
        String tables = "accident_handling,audit_records,blacklist,chat_records,game_accounts,personal_center,transactions,users";

        String mapperLocation = "C:\\Users\\86183\\Desktop\\yi-you\\yiyou\\src\\main\\resources\\mapper\\" + moduleName;

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("wuqi") // ��������
                            //.enableSwagger() // ���� swagger ģʽ
                            //.fileOverride() // �����������ļ�
                            .outputDir("C:\\Users\\86183\\Desktop\\yi-you\\yiyou\\src\\main\\java"); // ָ�����Ŀ¼
                })
                .packageConfig(builder -> {
                    builder.parent("com.work") // ���ø�����
                            .moduleName(moduleName) // ���ø���ģ����
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // ����mapperXml����·��
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // ������Ҫ���ɵı���
                            .addTablePrefix("accident_","audit_","chat_","game_","personal_"); // ���ù��˱�ǰ׺
                })
                .templateEngine(new FreemarkerTemplateEngine()) // ʹ��Freemarker����ģ�壬Ĭ�ϵ���Velocity����ģ��
                .execute();
    }
}
