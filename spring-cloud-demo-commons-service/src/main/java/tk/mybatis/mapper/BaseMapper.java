package tk.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseMapper<T> extends Mapper<T> {
    int deleteByIds(@Param("ids") Integer... ids);
    List<T> selectByIds(@Param("ids") Integer... ids);
}
