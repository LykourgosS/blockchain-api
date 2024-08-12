package com.lykourgoss.blockchainapi.core.helpers;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class ApplicationContextHelper implements ApplicationContextAware {

    @Getter
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setContext(applicationContext);
    }

    private static synchronized void setContext(ApplicationContext context) {
        ApplicationContextHelper.context = context;
    }

    public static <T> T getBean(Class<T> requiredType){
        return context.getBean(requiredType);
    }
}
