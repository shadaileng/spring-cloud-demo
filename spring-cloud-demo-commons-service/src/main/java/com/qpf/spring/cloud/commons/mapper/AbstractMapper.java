package com.qpf.spring.cloud.commons.mapper;

import com.qpf.spring.cloud.commons.provider.UserProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.BaseMapper;

import java.util.List;

public interface AbstractMapper<T> {
    @SelectProvider(type = UserProvider.class, method = "selectByIds")
    List<T> selectByIds(@Param("ids") Integer... ids);
}
