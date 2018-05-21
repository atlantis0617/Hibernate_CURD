package org.hibernate.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCURD {
	private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
     
    @Before
    public void before() {
        Configuration config = new Configuration().configure();// 加载配置信息
        sessionFactory = config.buildSessionFactory();// 创建SessionFactory对象
        session = sessionFactory.openSession();// 创建Session对象
        transaction = session.beginTransaction();// 开启事务
    }
     
    @After
    public void after() {
        transaction.commit();// 提交事务
        session.close();// 关闭Session
        sessionFactory.close();// 关闭SessionFactory
    }
    
    @Test
    public void testCreate() {
        Student student = new Student("张三", "男", new Date(), "北京市");// 创建Student对象
        session.save(student);// 保存对象
    }
    
    @Test
    public void testRetrieveByGet() {
        Student student = session.get(Student.class, 1L);// 通过get查询信息
        System.out.println(student);
    }
    
    @Test
    public void testRetrieveByLoad() {
        Student student = session.load(Student.class, 1L);// 通过load查询信息
        System.out.println(student);
    }
    
    @Test
    public void testUpdate() {
        Student student = session.load(Student.class, 1L);// 通过load查询信息
        student.setAddress("上海市");// 修改信息
        session.update(student);// 保存修改信息
    }
    
    @Test
    public void testDelete() {
        Student student = session.load(Student.class, 1L);// 通过load查询信息
        session.delete(student);// 删除信息
    }
    
}	
