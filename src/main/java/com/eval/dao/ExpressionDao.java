/**
 * 
 */
package com.eval.dao;

import java.util.List;

import com.eval.entity.Expression;

/**
 * This is a Dao interface
 * 
 * @author BhabaranjanBehera
 *
 */
public interface ExpressionDao {
	//For saving the data thru hibernate
	public void save(Expression entity);
	
	//For getting the list of record thru hibernate
	public List<Expression> getAll();
}
