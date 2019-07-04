package com.qpf.spring.cloud.service.admin.test.service;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.utils.JsonUtils;
import com.qpf.spring.cloud.service.admin.mapper.UserProvider;
import org.junit.Test;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.BaseProvider;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserTest {
    @Test
    public void testProvider() {
        BaseProvider<User> userBaseProvider = new UserProvider();
        System.out.println(userBaseProvider);
        System.out.println(userBaseProvider.selectByIds(1, 2, 3));
    }

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

    @Test
    public void getJson() throws Exception {
        User user = new User();
//        user.setId(1);
        user.setUserName("0002");
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        user.setUserCode("0002");
        user.setLoginCode("0002");
        user.setMobile("13800000000");
        user.setPhone("13800000000");
        user.setSex("1");
        user.setUserType("1");
        user.setMgrType("1");
        user.setStatus("0");
        user.setCorpCode("B");
        user.setCorpName("Bank1");
        user.setCreateBy("1");
        user.setUpdateBy("1");

        String json = JsonUtils.obj2json(user);
        System.out.println(json);
    }
}
