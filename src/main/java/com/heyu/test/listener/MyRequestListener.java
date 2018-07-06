package com.heyu.test.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("销毁request...");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("创建request...");
    }
}
