package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.example.common.BaseContext;
import com.example.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 阿谭
 * <p>
 * 2022-10-02 16:37
 *
 * 检查用户是否完成登陆
 */
@Slf4j
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器,支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
//        1、获取本次请求的URI
        String requestURI = req.getRequestURI();
        log.info("本次请求的URI:{}",requestURI);
//        1.1定义不需要拦截的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "common/**"
        };
//        2、判断本次请求是否需要处理
        boolean check = check(urls,requestURI);
//        3、如果不需要处理，则直接放行
        if(check){
            log.info("不需要请求的URI:{}",requestURI);
            chain.doFilter(req,res);
            return;
        }
//        4、判断登录状态，如果已登录，则直接放行
        if(req.getSession().getAttribute("employee") != null){
            log.info("用户已登录,id为:{}",req.getSession().getAttribute("employee"));

            BaseContext.setCurrentID((Long) req.getSession().getAttribute("employee"));

            chain.doFilter(req,res);
            return;
        }
//        5、如果未登录则返回未登录结果,通过输出流的方式向客户端页面响应数据
            log.info("用户未登录");
            res.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
            return;
    }

    /**
     * 路径匹配,检查路径是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
