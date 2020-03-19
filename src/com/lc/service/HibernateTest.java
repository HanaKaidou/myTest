package com.lc.service;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.lc.entity.Customer;
import com.lc.entity.LinkMan;
import com.lc.entity.User;
import com.lc.utils.HibernateUtils;

public class HibernateTest {

    public static void main(String[] args) {
        // queryUserById();
        //queryUserById_lazy();
        //queryUserById_lazy2();
        queryUserBatch();
        // addUser();
        // addUser3();
        // updateUser();
        // deleteUser();
        // queryUserByHQL();
        // queryUserByCriteria();
        // queryUserBySQLQuery();
    }

    // 根据ID查询（立即查询）
    public static void queryUserById() {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            // 根据主键查询
            User user = session.get(User.class, 2);
            System.out.println(user);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }

    // 根据ID查询（类别别延时查询）
    public static void queryUserById_lazy() {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            // 根据主键查询
            User user = session.load(User.class, 2);
            System.out.println(user.getUid());
            System.out.println(user.getUsername());
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }
    
    // 根据ID查询（关联别延时查询）
    public static void queryUserById_lazy2() {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            
            //因为用的get()是立即查询，所以会立即查询数据库
            Customer customer = session.get(Customer.class, 1);
            
            //用的是导航查询，这里hibernate默认是关联查询为延时的，所以这里不会查询数据库
            Set<LinkMan> linkMan = customer.getSetLinkMan();
            
            //这里使用了延时查询得出的对象的值，所以开始查询数据库
            System.out.println(linkMan.size());
            
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }
    
    // 根据ID查询（关联别延时查询）
    public static void queryUserBatch() {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            
           Criteria criteria = session.createCriteria(Customer.class);
           List<Customer> list = criteria.list();
           for (Customer customer : list) {
            System.out.println(customer.getCustName() + "::" + customer.getCustPhone());
            Set<LinkMan> setLinkMan = customer.getSetLinkMan();
            for (LinkMan linkMan : setLinkMan) {
                System.out.println(linkMan.getLkm_name() + "--" + linkMan.getLkm_gender() );
            }
        }
            
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }

    // 使用hql查询
    public static void queryUserByHQL() {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();
            // 书写hql（hibernate query language）查询
            String hql = "from User";
            // String hql = "from User where username = 'LW' ";
            Query query = session.createQuery(hql);
            List<User> list = query.list();
            for (User user : list) {
                System.out.println(user);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

    }

    // 使用Criteria查询
    public static void queryUserByCriteria() {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            List<User> list = criteria.list();
            for (User user : list) {
                System.out.println(user);
            }

            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    // 通过SQLQuery查询
    public static void queryUserBySQLQuery() {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();

            String sql = "select * from user";
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.addEntity(User.class);
            List<User> list = sqlQuery.list();
            for (User user : list) {
                System.out.println(user);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

    }

    public static void addUser() {

        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.buildSessionFactory();

        Session session = sf.openSession();
        // 开启事务
        Transaction tx = session.beginTransaction();

        // 自定义逻辑
        User user = new User();
        user.setUsername("Kangkang");
        user.setPassword("1321314");
        user.setAddress("北京");
        session.save(user);

        // 事务提交
        tx.commit();

        // 关闭连接
        session.close();
        sf.close();

    }

    public static void addUser2() {
        // 使用工具类创建SessionFactory
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        // 开启事务
        Transaction tx = session.beginTransaction();

        // 自定义逻辑
        User user = new User();
        user.setUsername("Kangkang");
        user.setPassword("1321314");
        user.setAddress("北京");
        session.save(user);

        // 事务提交
        tx.commit();

        // 关闭连接
        session.close();
        sf.close();

    }

    // 通过绑定的本地session 添加
    public static void addUser3() {
        // 使用工具类创建SessionFactory
        Session session = HibernateUtils.getCurrentSession();
        // 开启事务
        Transaction tx = session.beginTransaction();

        // 自定义逻辑
        User user = new User();
        user.setUsername("LW");
        user.setPassword("124111231");
        user.setAddress("河南");
        session.save(user);

        // 事务提交
        tx.commit();
    }

    public static void updateUser() {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();

            User user = session.get(User.class, 1);
            user.setUsername("Meimei2");
            session.update(user);
            // int a = 1/0;

            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }

    }

    public static void deleteUser() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getCurrentSession();
            tx = session.beginTransaction();
            User user = session.get(User.class, 1);
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    // 立即查询

}
