/**
 * 
 */
package com.eval.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.eval.dao.ExpressionDao;
import com.eval.entity.Expression;
import com.eval.util.HibernateUtil;

/**
 * This is a Dao implementation
 * 
 * @author BhabaranjanBehera
 *
 */
@Repository
public class ExpressionDaoImpl implements ExpressionDao {

	/**
	 * This method is responsible for persisting expression and its result
	 */
	public void save(Expression entity) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			// start a transaction
			transaction = session.beginTransaction();

			// save the cal objects
			session.save(entity);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			// rollback transaction if exception comes
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * This method is responsible for fetching all the record from database
	 */
	@SuppressWarnings("unchecked")
	public List<Expression> getAll() {
		List<Expression> outputList = new ArrayList<Expression>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			// get all object
			outputList = (List<Expression>) session.createQuery("from Expression order by id desc").list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputList;
	}

}
