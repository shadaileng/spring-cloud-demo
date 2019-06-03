package com.qpf.spring.cloud.service.admin.test.service;

import com.qpf.spring.cloud.commons.domain.User;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        System.out.println(new SimpleDateFormat("YYYYMMddHHmmssSSS").format(new Date()));
        System.out.println(DigestUtils.md5DigestAsHex(new SimpleDateFormat("YYYYMMddHHmmssSSS").format(new Date()).getBytes()));
    }

    private String camelToUnderline(String param) {
        StringBuilder line = new StringBuilder();

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
