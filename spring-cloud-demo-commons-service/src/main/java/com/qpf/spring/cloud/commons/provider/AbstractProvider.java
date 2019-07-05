package com.qpf.spring.cloud.commons.provider;

import com.qpf.spring.cloud.commons.domain.BaseDomain;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.BaseProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public abstract class AbstractProvider<T extends BaseDomain> implements BaseProvider<T> {

    private String tableName;

    public AbstractProvider() {
        Type type = getClass().getGenericSuperclass();
        Type[] arguments = ((ParameterizedType) type).getActualTypeArguments();
        Class clazz = (Class)arguments[0];
        tableName = clazz != null ? clazz.getSimpleName().toLowerCase() : "";
    }

    @Override
    public String selectByIds(@Param("ids") Integer... ids) {
        return new SQL(){
            {
                SELECT("*");
                FROM(tableName);
                WHERE(String.format(" id in %s ", Arrays.asList(ids).toString().replace('[', '(').replace(']', ')')));
            }
        }.toString();
    }

    @Override
    public String deleteByIds(@Param("ids")Integer... ids) {
        return new SQL(){
            {
                DELETE_FROM(tableName);
                WHERE(String.format(" id in %s ", Arrays.asList(ids).toString().replace('[', '(').replace(']', ')')));
            }
        }.toString();
    }
}
