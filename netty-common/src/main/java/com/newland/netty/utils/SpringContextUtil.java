package com.newland.netty.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文工具
 *
 * @author WRP
 * @since 2019/12/26
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        if (context.containsBean(name)) {
            return context.getBean(name, requiredType);
        } else {
            return null;
        }
    }

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    public static Object getBean(String name) {
        if (context.containsBean(name)) {
            return context.getBean(name);
        } else {
            return null;
        }
    }

    public static String getVaue(String key) {
        return context.getEnvironment().getProperty(key);
    }

}
