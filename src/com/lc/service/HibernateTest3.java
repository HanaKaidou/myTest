package com.lc.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lc.entity2.Role;
import com.lc.entity2.User;
import com.lc.utils.HibernateUtils;

//多对多
public class HibernateTest3 {

    public static void main(String[] args) {
        //addUserRole();
        //whTable();
        //whTable2();
        whTable3();
        
    }

    // 级联保存
    public static void addUserRole() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();

            User user1 = new User();
            user1.setUser_name("小王");
            user1.setUser_password("12345");

            User user2 = new User();
            user2.setUser_name("小常");
            user2.setUser_password("12345");

            Role role1 = new Role();
            role1.setRole_name("经理");
            role1.setRole_memo("经理");

            Role role2 = new Role();
            role2.setRole_name("秘书");
            role2.setRole_memo("秘书");

            Role role3 = new Role();
            role3.setRole_name("保安");
            role3.setRole_memo("保安");

            user1.getSetRole().add(role1);
            user1.getSetRole().add(role2);

            user2.getSetRole().add(role2);
            user2.getSetRole().add(role3);

            session.save(user1);
            session.save(user2);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
    
    // 维护中间表
    public static void whTable() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();
            
            //让某用户有某个角色
            User user = session.get(User.class, 1);
            Role role = session.get(Role.class, 3);
            
            user.getSetRole().add(role);
           
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
    
    // 维护中间表
    public static void whTable2() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();
            
            //让某用户去除某个角色
            User user = session.get(User.class, 1);
            Role role = session.get(Role.class, 3);
            
            user.getSetRole().remove(role);
           
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
    
    // 维护中间表
    public static void whTable3() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();
            
            //让某用户更改某个角色
            User user = session.get(User.class, 1);
            Role role1 = session.get(Role.class, 2);
            Role role2 = session.get(Role.class, 3);
            
            user.getSetRole().remove(role1);
            user.getSetRole().add(role2);
           
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

}
