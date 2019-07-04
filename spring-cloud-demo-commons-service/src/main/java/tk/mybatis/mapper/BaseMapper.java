package tk.mybatis.mapper;

import com.qpf.spring.cloud.commons.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseMapper<T> extends Mapper<T> {
    int deleteByIds(@Param("ids") Integer... ids);
    List<T> selectByIds(@Param("ids") Integer... ids);
}
