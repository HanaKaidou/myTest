package com.lc.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static Configuration cfg = null;

    private static SessionFactory sf = null;

    static {
        cfg = new Configuration().configure();
        sf = cfg.buildSessionFactory();
    }

    //返回Sessionfactory
    public static SessionFactory getSessionFactory() {
        return sf;
    }
    
    //返回与本地线程绑定的session
    public static Session getCurrentSession() {
        return sf.getCurrentSession();
    }
    
    public static void main(String[] args) {
        
    }
}
