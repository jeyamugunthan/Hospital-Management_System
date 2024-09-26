package com.daoimpl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dao.AdminDao;
import com.entity.Admin;
import com.helper.Demo;

public class AdminDaoImpl implements AdminDao {

	public boolean login(String username, String password) {
		Session session = null;
		try {
			session = Demo.getSessionFactory().openSession();
			String hql = "FROM Admin WHERE username = :username AND password = :password";
			Query query = session.createQuery(hql);
			query.setParameter("username", username);
			query.setParameter("password", password);

			Admin admin = (Admin) query.uniqueResult();

			return admin != null;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (session != null)
				session.close();
		}
	}

}
