package com.qpf.spring.cloud.service.admin.service.test;

import com.qpf.spring.cloud.service.admin.entity.User;
import org.junit.Test;

import java.lang.reflect.Field;

public class UserTest {
    @Test
    public void property() {
        StringBuffer a = new StringBuffer();
        StringBuffer b = new StringBuffer();
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
        }
        b.append("}");
        System.out.println(a);
        System.out.println(b);
    }
}
