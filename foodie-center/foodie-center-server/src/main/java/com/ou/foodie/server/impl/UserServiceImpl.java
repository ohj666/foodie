package com.ou.foodie.server.impl;


import com.ou.foodie.mapper.UsersMapper;
import com.ou.foodie.pojo.Users;
import com.ou.foodie.pojo.bo.UserInfoMoreBo;
import com.ou.foodie.properties.ProjectConstant;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.server.UserService;
import com.ou.foodie.util.CookieUtils;
import com.ou.foodie.util.DateUtils;
import com.ou.foodie.util.JsonResult;
import com.ou.foodie.util.JsonUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private ProjectProperties properties;
    private UsersMapper usersMapper;
    @Override
    public JsonResult userInfo(String userId) {
        Users users = new Users();
        users.setId(userId);
        Users users1 = usersMapper.selectOne(users);
        return JsonResult.isOk(users1);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public JsonResult update(String userId, UserInfoMoreBo userInfoMore, BindingResult result) {
        if(result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String,String> map=new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                String field = fieldError.getField();
                String defaultMessage = fieldError.getDefaultMessage();
                map.put(field,defaultMessage);
            }
            return JsonResult.error(500,map);

        }
        Users users = new Users();
        users.setId(userId);
        Users users1 = usersMapper.selectOne(users);

        String nickname = userInfoMore.getNickname();
        if(StringUtils.isBlank(nickname)||nickname.length()>=12){
            return JsonResult.error(500,"错误的格式");
        }
        BeanUtils.copyProperties(userInfoMore,users1);
        users1.setUpdatedTime(new Date());
        usersMapper.updateByPrimaryKeySelective(users1);

        return JsonResult.isOk();
    }

    @Override
    public JsonResult uploadFace(String userId, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        if(file==null){
            return JsonResult.error(500,"文件不能为空");
        }
        FileOutputStream fileOutputStream1=null;
        String UserFile=File.separator+userId;
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        String geshi=split[split.length-1];
        if(!geshi.equalsIgnoreCase("png")&&
       !geshi.equalsIgnoreCase("jpg")&&
                !geshi.equalsIgnoreCase("jpeg")
        ){
            return JsonResult.error(500,"图片格式不正确");
        }
        String FileName="face-"+userId+"."+geshi;
        String FinalName= ProjectConstant.IMAGE_URL+UserFile+File.separator+FileName;
        try {
            File outFile = new File(FinalName);
            if(outFile.getParentFile()!=null){
                outFile.getParentFile().mkdir();
            }
            fileOutputStream1 = new FileOutputStream(outFile);
            InputStream inputStream = file.getInputStream();
            IOUtils.copy(inputStream,fileOutputStream1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (fileOutputStream1!=null){
                try {
                    fileOutputStream1.flush();
                    fileOutputStream1.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        String imgFileUrl = properties.getImgFileUrl()+UserFile+"/"+FileName+"?t="+ DateUtils.getDate(DateUtils.DATETIME_FORMAT);
        Users users = new Users();
        users.setId(userId);
        users.setFace(imgFileUrl);
        usersMapper.updateByPrimaryKeySelective(users);
        Users users1 = usersMapper.selectOne(users);

        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(users1),true);

        return JsonResult.isOk();
    }


//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userMapper.selectByPrimaryKey(s);
//        return  new org.springframework.security.core.userdetails.User(user.getId()+"",user.getPassword(),true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
////        return null;
//
//    }
//
//    @Override
//    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
//        System.out.println("hello");
//        User user = userMapper.selectByPrimaryKey(s);
//
////        User user = new User();
////        user.setId(Integer.valueOf(s));
////        User user1 = userMapper.selectOne(user);
////        SocialUserDetails socialUserDetails=new SocialUser(user1.getUsername(),user1.getPassword(),AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
////        return socialUserDetails;
//
//        return  new SocialUser(user.getUsername(),user.getPassword(),true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
//    }
//
//    @Override
//    public Integer userRegister(UserRegisterBo registerBo) {
//        if(!registerBo.getPassword().equals(registerBo.getRePassword())){
//            throw new UsernameNotFoundException("两次密码输入不一致，请重新输入");
//        }
//        User user = new User();
//        user.setPassword(passwordEncoder.encode(registerBo.getPassword()));
//        user.setUsername(registerBo.getUsername());
//        userMapper.insertSelectKey(user);
//        providerSignInUtils.doPostSignUp(user.getId().toString(),new ServletWebRequest(request));
//        return user.getId();
//    }
}
