package com.qpf.spring.cloud.commons.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qpf.spring.cloud.commons.domain.BaseDomain;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.service.AbstractService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.BaseMapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
public class AbstractServiceImpl<T extends BaseDomain, M extends BaseMapper<T>> implements AbstractService<T> {
    @Autowired
    private M mapper;

    private Class<?> clazz;

    private final static Logger logger = LoggerFactory.getLogger(AbstractService.class);

    protected AbstractServiceImpl() {
        Type type = getClass().getGenericSuperclass();
        Type[] arguments = ((ParameterizedType) type).getActualTypeArguments();
        clazz = (Class)arguments[0];
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public PageInfo page(int start, int length, T entity) {
        PageHelper.startPage(start, length);
        return new PageInfo<>(mapper.select(entity));
    }

    @Override
    @Transactional
    public BaseResult save(T entity, String... fields) {
        BaseResult result = checkUser(entity, Arrays.asList(fields));
        if (!result.getResult()) return result;

        Integer id = entity.getId();
        int save;
        String now = String.valueOf(new Date().getTime());
        if (id != null) {
            entity.setUpdateDate(now);
            save = mapper.updateByPrimaryKeySelective(entity);
        } else {
            entity.setCreateDate(now);
            entity.setUpdateDate(now);
            save = mapper.insert(entity);
        }
        return save > 0 ? BaseResult.OK(entity, "保存成功") : BaseResult.ER("保存失败");
    }

    @Override
    @Transactional
    public BaseResult delete(Integer... ids) {
        int delete = mapper.deleteByIds(ids);
        return delete > 0 ? BaseResult.OK(Arrays.asList(ids), "删除成功") : BaseResult.ER("删除失败");
    }

    @Override
    public BaseResult getById(T entity) {
        entity = mapper.selectByPrimaryKey(entity);
        return entity != null ? BaseResult.OK(entity, "查询成功") : BaseResult.ER("查询失败");
    }

    @Override
    public BaseResult getByBean(T entity) {
        List<T> select = mapper.select(entity);
        return select != null && select.size() > 0 ? BaseResult.OK(select, String.format("查询成功 | size: %s", select.size())) : BaseResult.ER("查询失败");
    }

    @Override
    public BaseResult getByIds(Integer... ids) {
        List<T> entities = mapper.selectByIds(ids);
        return entities != null && entities.size() > 0 ? BaseResult.OK(entities, String.format("查询成功 | size: %s", entities.size())) : BaseResult.ER("查询失败");
    }

    @Override
    public BaseResult checkUser(T entity, List<String> fields) {
        BaseResult result = BaseResult.OK();
        for (String field: fields) {
            String methodName_get = String.format("get%s%s", Character.toUpperCase(field.charAt(0)), field.substring(1));
            try {
                Method method_get = clazz.getMethod(methodName_get);
                String val = (String)method_get.invoke(entity);
                if (StringUtils.isEmpty(val)) {
                    if (result.getResult()) {
                        result = BaseResult.ER("字段错误");
                    }
                    String msg = String.format("%s[%s]不能为空", field, val);
                    BaseResult.Error error = new BaseResult.Error();
                    error.setField(field);
                    error.setMessage(msg);
                    result.errors(error);
                } else {
                    Example example = new Example(clazz);
                    Integer id = entity.getId();
                    if (id != null && id > 0) {
                        example.createCriteria().andNotEqualTo("id", id).andEqualTo(field, val);
                    } else {
                        example.createCriteria().andEqualTo(field, val);
                    }
                    List<T> users = mapper.selectByExample(example);
                    if (users != null && users.size() > 0) {
                        if (result.getResult()) {
                            result = BaseResult.ER("字段错误");
                        }
                        String msg = String.format("%s[%s]已存在", field, val);
                        BaseResult.Error error = new BaseResult.Error();
                        error.setField(field);
                        error.setMessage(msg);
                        result.errors(error);
                    }
                }
            } catch (Exception e) {
                logger.error(String.format("ERROR: %s", e.getMessage()));
                result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
                break;
            }
        }

        return result;
    }
}
