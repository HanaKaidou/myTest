package com.lc.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lc.utils.HibernateUtils;

//HQL多表查询
public class HibernateTest4 {

    public static void main(String[] args) {
        // queryByInner();
        // queryByFetchInner();
        // queryByLeft();
        // queryByFetchLeft();
        queryByRight();
    }

    // 内连接查询
    public static void queryByInner() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();

            String hql = "from Customer c join c.setLinkMan";
            Query query = session.createQuery(hql);
            List<Object> list = query.list();
            for (Object object : list) {
                System.out.println(object);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    // 迫切内连接查询
    public static void queryByFetchInner() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();

            String hql = "from Customer c join fetch c.setLinkMan";
            Query query = session.createQuery(hql);
            List<Object> list = query.list();
            for (Object object : list) {
                System.out.println(object);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    // 左外连接查询
    public static void queryByLeft() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();

            String hql = "from Customer c left join c.setLinkMan";
            Query query = session.createQuery(hql);
            List<Object> list = query.list();
            for (Object object : list) {
                System.out.println(object);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    // 迫切左外连接查询
    public static void queryByFetchLeft() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();

            String hql = "from Customer c left join fetch c.setLinkMan";
            Query query = session.createQuery(hql);
            List<Object> list = query.list();
            for (Object object : list) {
                System.out.println(object);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    // 右外连接查询
    public static void queryByRight() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();

            String hql = "from Customer c right join c.setLinkMan";
            Query query = session.createQuery(hql);
            List<Object> list = query.list();
            for (Object object : list) {
                System.out.println(object);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
}
