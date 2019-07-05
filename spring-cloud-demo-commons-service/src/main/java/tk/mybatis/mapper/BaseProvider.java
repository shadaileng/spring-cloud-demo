package tk.mybatis.mapper;

import com.qpf.spring.cloud.commons.domain.BaseDomain;

public interface BaseProvider<T extends BaseDomain> {
    String selectByIds(Integer... ids);
    String deleteByIds(Integer... ids) ;
}
