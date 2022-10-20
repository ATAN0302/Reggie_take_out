package com.example.common;

/**
 * 阿谭
 * <p>
 * 2022-10-04 16:18
 */
public class BaseContext {
//1、编写BaseContext工具类，基于ThreadLocal封装的工具类
//2、在LoginCheckFilter的doFilter方法中调用BaseContext来设置当前登录用户的id
//3、在MyMetaObjectHandler的方法中调用BaseContext获取登录用户的id

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentID(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentID(){
        return threadLocal.get();
    }
}
