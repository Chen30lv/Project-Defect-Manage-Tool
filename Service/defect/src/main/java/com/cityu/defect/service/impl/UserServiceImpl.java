package com.cityu.defect.service.impl;

import com.cityu.defect.common.ErrorCode;
import com.cityu.defect.model.entity.User;
import com.cityu.defect.exception.BusinessException;
import com.cityu.defect.repository.UserRepositoryJPA;
import com.cityu.defect.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.regex.Pattern;
import static com.cityu.defect.constant.UserConstant.PERSON_LOGIN_STATE;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepositoryJPA userRepositoryJPA;
    /**
     * 盐值：混淆密码
     */
    private static final String SALT = "DEFECT";
    @Override
    public long userRegister(String account, String password, String checkPassword) {
        //1. 校验
        // 非空
        if (account.isEmpty() || password.isEmpty() || checkPassword.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        //长度
        if (account.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号长度过短");
        }
        if(password.length()<8 || checkPassword.length()<8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度过短");
        }
        //账户不能包含特殊字符
        String pattern = ".*[*?!&￥$%^#,./@\";:><\\]\\[}{\\-=+_\\\\|》《。，、？’‘“”~`）].*$";
        if(Pattern.matches(pattern, account)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号不能包含特殊字符");
        }
        //密码和校验密码相同
        if(!password.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码和校验密码相同");
        }
        //账户不能重复 (查询了数据库，这个校验应该放到最后校验)
        var userList = userRepositoryJPA.findByAccount(account);
        if(!userList.isEmpty()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号已注册");
        }
        //2.加密
        String md5Password = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        //3. 插入数据
        User user = new User();
        user.setAccount(account);
        user.setPassword(md5Password);
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userRepositoryJPA.save(user);//保存Person成功后，会把id塞给Person
        //TODO: 插入失败判断
//        if(result){
//            throw new BusinessException(ErrorCode.SYSTEM_EXCEPTION,"注册失败");
//        }
        return user.getId();
    }

    @Override
    public User userLogin(String account, String password, HttpServletRequest request) {
        //1. 校验
        // 非空
        if (StringUtils.isAnyBlank(account,password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        //长度
        if (account.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号错误");
        }
        if(password.length()<8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码错误");
        }
        //2.加密
        String md5Password = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        //查询用户是否存在
        var userList = userRepositoryJPA.findByAccount(account);
        if (userList.isEmpty()) {
            log.info("user login failed, account doesn't exist");
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号不正确");
        }
        if(!userList.get(0).getPassword().equals(md5Password)){
            log.info("user login failed, password is wrong");
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码不正确");
        }
        //3.用户脱敏
        User safetyUser = getSaftyPerson(userList.get(0));
        //4.记录用户的登录态
        request.getSession().setAttribute(PERSON_LOGIN_STATE, safetyUser);
        return safetyUser;
    }

    @Override
    public User getSaftyPerson(User user) {
        User saftyUser = new User();
        saftyUser.setCreateTime(user.getCreateTime());
        saftyUser.setAccount(user.getAccount());
        return null;
    }

}
