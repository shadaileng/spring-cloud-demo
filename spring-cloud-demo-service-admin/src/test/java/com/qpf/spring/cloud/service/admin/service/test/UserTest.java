package com.qpf.spring.cloud.service.admin.service.test;

import com.qpf.spring.cloud.service.admin.entity.User;
import org.junit.Test;

import java.lang.reflect.Field;

public class UserTest {
    @Test
    public void property() {
        StringBuffer a = new StringBuffer();
        StringBuffer b = new StringBuffer();
        StringBuffer c = new StringBuffer();
        for (Field field : User.class.getDeclaredFields()) {
            String name = field.getName();
            if (a.length() > 0) {
                a.append(", ");
            }
            a.append(name);

            if (b.length() > 0) {
                b.append("}, #{");
            } else {
                b.append("#{");
            }
            b.append(name);


            if (c.length() > 0) {
                c.append(", ");
            }
            c.append(camelToUnderline(name));
        }
        b.append("}");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    private String camelToUnderline(String param) {
        StringBuffer line = new StringBuffer();

        for (char c: param.toCharArray()) {
            if (Character.isUpperCase(c) || Character.isDigit(c)) {
                line.append("_");
                c = Character.toLowerCase(c);
            }
            line.append(c);
        }

        return line.toString();
    }
}
