package com.lab.account.listeners;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * Created by viko0417 on 7/7/2017.
 */
@WebListener
public class ContextListener  extends ContextLoaderListener {

        @Override
        public void contextInitialized(ServletContextEvent arg0) {
            super.contextInitialized(arg0);

        }

        @Override
        public void contextDestroyed(ServletContextEvent arg0) {
            super.contextDestroyed(arg0);

        }
    }

