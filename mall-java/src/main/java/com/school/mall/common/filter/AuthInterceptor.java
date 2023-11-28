package com.school.mall.common.filter;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.school.mall.common.annotation.IgnoreSecurity;
import com.school.mall.common.handler.GlobalException;
import com.school.mall.common.handler.GlobalExceptionEnum;
import com.school.mall.common.utils.*;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return true;
        }
        String token = request.getHeader("Token");

        if (StringUtils.isEmpty(token)) {
            throw new GlobalException(GlobalExceptionEnum.TOKEN_AUTHENTICATION_FAILED.getCode(),"token验证失败");
        }
        getUserByToken(token,request);

        return true;

    }

    /**
     * 更新token
     * @param token
     * @param request
     * @throws Exception
     */
    public void getUserByToken(String token, HttpServletRequest request) throws Exception {

        String contextPath = request.getRequestURI();
        Claims claims = JwtUtils.validateJWT(token);
        String issuedAt = claims.getSubject();
        UserLogInfoDto userLogInfoDto = JSONUtil.toBean(issuedAt, UserLogInfoDto.class);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(userLogInfoDto);
        UserInfoUtil.setUserInfoPutAll(stringObjectMap);

        if(!contextPath.contains("/sys/goods")&&!contextPath.contains("/goods")){
            String o =CacheTemplateUtil.get("login_key_" + userLogInfoDto.getUserId());
            if (!o.equals(userLogInfoDto.getLoginKey())){
                throw new GlobalException(GlobalExceptionEnum.TO_LOG_IN_ELSEWHERE.getCode(), "已在其地方登录");
            }
            Long l = Long.valueOf(userLogInfoDto.getLoginDateLong());
            long l2 = System.currentTimeMillis() + (60 * 1000 * 25);

            long l1 = l + 60 * 1000 * 25;

            if ((l + 60 * 1000 * 25) >= l2 && l2 < (l + 60 * 1000 * 30)) {
                throw new GlobalException(GlobalExceptionEnum.BE_OVERDUE_TOKEN.getCode(), "更新token");
            }
        }

        request.getSession().setAttribute("userInfo", JSONUtil.toJsonStr(userLogInfoDto));

    }
}
