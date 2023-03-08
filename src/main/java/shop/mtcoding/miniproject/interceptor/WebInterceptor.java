package shop.mtcoding.miniproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import shop.mtcoding.miniproject.model.User;

public class WebInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    // 요청 들어가기 전
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("================ URL 요청 전 인터셉터 ==================");

        User principal = (User) redisTemplate.opsForValue().get("principal");
        if (principal == null) {
            response.sendRedirect("/");
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    // 요청 완료 후
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        System.out.println("================ URL 요청 후 인터셉터 ==================");
    }

}