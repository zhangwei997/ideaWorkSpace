
package com.xakj.platform.api;

import com.alibaba.fastjson.JSONObject;



import com.xakj.platform.annotation.PassToken;
import com.xakj.platform.annotation.UserLoginToken;
import com.xakj.platform.filter.HttpResponse;
import com.xakj.platform.platform.model.User;
import com.xakj.platform.platform.service.TokenService;
import com.xakj.platform.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Hardy
 * @date 2018-07-08 20:45
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;



    //登录
   // @PassToken //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIn0.SuzbRfjj7cnc9ai_OmxUW4kyvV0cVxV25nJWiXoaozk
    @PostMapping("/login")
    public HttpResponse login(@RequestBody User user){
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findByUsername(user);
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return new HttpResponse(jsonObject);
        }else {
            if (!userForBase.getPassWord().equals(user.getPassWord())){
                jsonObject.put("message","登录失败,密码错误");
                return new HttpResponse(jsonObject);
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);

                return new HttpResponse(jsonObject);
            }
        }
    }


    //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIn0.SuzbRfjj7cnc9ai_OmxUW4kyvV0cVxV25nJWiXoaozk
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
