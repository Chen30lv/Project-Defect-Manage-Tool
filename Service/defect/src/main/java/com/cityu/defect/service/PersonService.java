package com.cityu.defect.service;

import com.cityu.defect.common.ErrorCode;
import com.cityu.defect.entity.Person;
import com.cityu.defect.exception.BusinessException;
import com.cityu.defect.repository.PersonRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    /**
     * 盐值：混淆密码
     */
    private static final String SALT = "Defect";

    @Transactional(isolation= Isolation.SERIALIZABLE)
    public int addPerson(String account, String password){

        try {
            personRepository.save(new Person(account, password, new Timestamp(System.currentTimeMillis())));
        }catch (Exception e){
            e.printStackTrace();

        }
        return 1;

    }
//    public long addPerson(String account, String password, String checkPassword){
//
//        //1. 校验
//        // TODO:非空
//        if (StringUtils.isAnyBlank(account, password, checkPassword)) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
//        }
//        //长度
//        if (account.length() < 4) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号长度过短");
//        }
//        if(password.length()<8 || checkPassword.length()<8){
//            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度过短");
//        }
//        //账户不能包含特殊字符
//        String pattern = ".*[*?!&￥$%^#,./@\";:><\\]\\[}{\\-=+_\\\\|》《。，、？’‘“”~`）].*$";
//        if(Pattern.matches(pattern, account)) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号不能包含特殊字符");
//        }
//        //密码和校验密码不同
//        if(!password.equals(checkPassword)){
//            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码和校验密码不同");
//        }
//        //TODO:账户不能重复 (查询了数据库，这个校验应该放到最后校验)
////        long count = ;
////        if(count>0){
////            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号已注册");
////        }
//        //2.加密
//        String md5Password = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
//        //3. 插入数据
//        Person person = new Person();
//        person.setAccount(account);
//        person.setPassword(md5Password);
//        person.setCreateTime(new Timestamp(System.currentTimeMillis()));
//        var result = personRepository.save(person);
//        //TODO
//        if(result!=null){
//            throw new BusinessException(ErrorCode.SYSTEM_EXCEPTION,"注册失败");
//        }
//        return person.getId();
//    }
}
