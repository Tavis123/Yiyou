package com.work.utils;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

//对象字段互补。
//传入一个同类型被补充对象和完整对象，如果被补充对象中有字段为null或者字符串为空，就用完整对象对应的字段值补上去；
//如果被补充对象中某字段不为空则保留它自己的值。
public class ClassExamine {
    //origin是被补充对象，intactObject是完整对象
    public static <T> void objectOverlap(T origin,T intactObject) throws Exception{
        Field[] fields = origin.getClass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == String.class) {
                if (StringUtils.hasText((String) field.get(origin))) {
                    field.set(origin, field.get(intactObject));
                }
            }else {
                if (field.get(origin) == null) {
                    field.set(origin, field.get(intactObject));
                }
            }
        }
    }

}
