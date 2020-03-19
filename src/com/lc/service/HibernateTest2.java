package com.lc.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lc.entity.Customer;
import com.lc.entity.LinkMan;
import com.lc.utils.HibernateUtils;


//一对多
public class HibernateTest2 {
    
    public static void main(String[] args) {
        addCus_Lin();
        // deleteCus_Lin();
        //updateCus_Lin();
    }

    // 级联添加
    public static void addCus_Lin() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();

            Customer customer = new Customer();
            customer.setCustLevel("2");
            customer.setCustMobile("35325235");
            customer.setCustName("小米");
            customer.setCustPhone("14432456834");
            customer.setCustSource("广州");

            LinkMan linkman = new LinkMan();
            linkman.setLkm_gender("男");
            linkman.setLkm_name("小雷");
            linkman.setLkm_phone("12321231312");

            customer.getSetLinkMan().add(linkman);
            session.save(customer);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    // 级联删除
    public static void deleteCus_Lin() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();

            Customer customer = session.get(Customer.class, 1);
            session.delete(customer);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    // 级联删除
    public static void updateCus_Lin() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();

            Customer customer = session.get(Customer.class, 2);
            LinkMan linkMan = session.get(LinkMan.class, 5);
            customer.getSetLinkMan().add(linkMan);
            linkMan.setCustomer(customer);
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
}
