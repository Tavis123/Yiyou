package com.work;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        //可能会变的量要单独列出来，这样好更改
        String url = "jdbc:mysql:///yiyou";
        String username = "root";
        String password = "123456";
        String moduleName = "system";//模块名
        String tables = "accident_handling,audit_records,blacklist,chat_records,game_accounts,personal_center,transactions,users";

        String mapperLocation = "C:\\Users\\86183\\Desktop\\yi-you\\yiyou\\src\\main\\resources\\mapper\\" + moduleName;

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("wuqi") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\86183\\Desktop\\yi-you\\yiyou\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.work") // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix("accident_","audit_","chat_","game_","personal_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
