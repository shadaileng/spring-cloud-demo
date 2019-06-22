package com.qpf.spring.cloud.commons.mapper;

import com.qpf.spring.cloud.commons.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.BaseMapper;
@Component
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Insert("insert into user (id, user_code, login_code, user_name, password, email, mobile, phone, sex, avatar, sign, wx_openid, mobile_imei, user_type, ref_code, ref_name, mgr_type, pwd_security_level, pwd_update_date, pwd_update_record, pwd_question, pwd_question_answer, pwd_question_2, pwd_question_answer_2, pwd_question_3, pwd_question_answer_3, pwd_quest_update_date, last_login_ip, last_login_date, freeze_date, freeze_cause, user_weight, status, create_by, create_date, update_by, update_date, remarks, corp_code, corp_name, extend_s1, extend_s2, extend_s3, extend_s4, extend_s5, extend_s6, extend_s7, extend_s8, extend_i1, extend_i2, extend_i3, extend_i4, extend_f1, extend_f2, extend_f3, extend_f4, extend_d1, extend_d2, extend_d3, extend_d4) values(#{id}, #{userCode}, #{loginCode}, #{userName}, #{password}, #{email}, #{mobile}, #{phone}, #{sex}, #{avatar}, #{sign}, #{wxOpenid}, #{mobileImei}, #{userType}, #{refCode}, #{refName}, #{mgrType}, #{pwdSecurityLevel}, #{pwdUpdateDate}, #{pwdUpdateRecord}, #{pwdQuestion}, #{pwdQuestionAnswer}, #{pwdQuestion2}, #{pwdQuestionAnswer2}, #{pwdQuestion3}, #{pwdQuestionAnswer3}, #{pwdQuestUpdateDate}, #{lastLoginIp}, #{lastLoginDate}, #{freezeDate}, #{freezeCause}, #{userWeight}, #{status}, #{createBy}, #{createDate}, #{updateBy}, #{updateDate}, #{remarks}, #{corpCode}, #{corpName}, #{extendS1}, #{extendS2}, #{extendS3}, #{extendS4}, #{extendS5}, #{extendS6}, #{extendS7}, #{extendS8}, #{extendI1}, #{extendI2}, #{extendI3}, #{extendI4}, #{extendF1}, #{extendF2}, #{extendF3}, #{extendF4}, #{extendD1}, #{extendD2}, #{extendD3}, #{extendD4})")
    @Options(keyColumn = "id", useGeneratedKeys = true)
    int insert(User user);
}