package com.cityu.defect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cityu.defect.common.ErrorCode;
import com.cityu.defect.mapper.UserMapper;
import com.cityu.defect.model.dto.user.UserQueryRequest;
import com.cityu.defect.model.entity.User;
import com.cityu.defect.exception.BusinessException;
import com.cityu.defect.model.enums.UserRoleEnum;
import com.cityu.defect.model.vo.UserVO;
import com.cityu.defect.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.cityu.defect.constant.UserConstant.USER_LOGIN_STATE;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    /**
     * 盐值：混淆密码
     */
//    private static final String SALT = "DEFECT";
    @Resource
    private UserMapper userMapper;
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
        // 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        long count = this.baseMapper.selectCount(queryWrapper);
        if(count>0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号已注册");
        }
        //2.加密
        String md5Password = DigestUtils.md5DigestAsHex((password).getBytes());
        //3. 插入数据
        User user = new User();
        user.setAccount(account);
        user.setPassword(md5Password);
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        boolean saveResult = this.save(user);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
        }
        return user.getId();
    }

    @Override
    public UserVO userLogin(String account, String password, HttpServletRequest request) {
        //1. 校验
        // 非空
        if (StringUtils.isAnyBlank(account,password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数不能为空");
        }
        //长度
//        if (account.length() < 4) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号错误");
//        }
        if(password.length()<8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度小于8");
        }
        //加密
        String md5Password = DigestUtils.md5DigestAsHex((password).getBytes());
        //查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        queryWrapper.eq("password", md5Password);
        User user = this.baseMapper.selectOne(queryWrapper);
        if (user==null) {
            log.info("user login failed, account doesn't exist");
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号不正确");
        }
        if(!user.getPassword().equals(md5Password)){
            log.info("user login failed, password is wrong");
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码不正确");
        }
        //记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return this.getUserVO(user);
    }

    @Override
    public UserVO getUserVO(User user) {
        if(user == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        long userId = currentUser.getId();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public UserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO loginUserVO = new UserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

//    @Override
//    public boolean isAdmin(HttpServletRequest request) {
//        // 仅管理员可查询
//        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
//        User user = (User) userObj;
//        return isAdmin(user);
//    }

//    @Override
//    public boolean isAdmin(User user) {
//        return user != null && UserRoleEnum.ADMIN.getValue().equals(user.getRole());
//    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(USER_LOGIN_STATE) == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    @Override
    public List<UserVO> getUserVOList(List<User> userList) {
        if (CollectionUtils.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }
    @Override
    public List<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = userQueryRequest.getId();
        String account = userQueryRequest.getAccount();
        String role = userQueryRequest.getRole();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.like(StringUtils.isNotBlank(account), "account", account);
        queryWrapper.eq(StringUtils.isNotBlank(role), "role", role);
        return userMapper.selectList(queryWrapper);
    }

}
