package tk.mybatis.mapper;

import com.qpf.spring.cloud.commons.domain.BaseDomain;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public interface BaseProvider<T extends BaseDomain> {
    public String selectByIds(Integer... ids);
    public String deleteByIds(Integer... ids) ;
}
