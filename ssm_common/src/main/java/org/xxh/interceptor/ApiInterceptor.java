package org.xxh.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.xxh.exception.BadRequestException;
import org.xxh.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization=request.getHeader("Authorization");
        if(authorization == null || !authorization .startsWith("Bearer ")){
            throw new BadRequestException("未携带token!");
        }
        String token=authorization.substring(7);
        try {
            Claims c;
            c = JwtUtil.parseJwt(token);
            System.out.println("用户id" + c.get("jti") + "已是登录状态");
        }catch(Exception e) {
            throw new BadRequestException(e.getMessage());
        }
        return  true;
    }
}
