/**
 * 
 */
package com.eval.service;

import java.util.List;

import com.eval.entity.Expression;

/**
 * This is service interface
 * 
 * @author BhabaranjanBehera
 *
 */
public interface EvalService {
	//This will evaluate and invoke dao
	public void evalAndSave(String expression);
	
	//This will invoke dao's list method
	public List<Expression> getList();
	
	//This will evaluate expression
	public double evaluate(String expression);
}
