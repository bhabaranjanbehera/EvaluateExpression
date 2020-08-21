/**
 * 
 */
package com.eval.service.impl;

import java.util.List;
import java.util.Stack;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.dao.ExpressionDao;
import com.eval.entity.Expression;
import com.eval.service.EvalService;

/**
 * This is a service class for business logic
 * 
 * @author BhabaranjanBehera
 *
 */

@Service
public class EvalServiceImpl implements EvalService {

	@Autowired
	private ExpressionDao baseDao;

	/**
	 * This method is responsible for saving the data
	 * 
	 * @param expression
	 * @param result
	 */
	public void evalAndSave(String expression) {

		Function<String, Double> eval = (exp) -> evaluate(expression);

		Expression cal = new Expression(expression, eval.apply(expression));

		baseDao.save(cal);
	}

	/**
	 * This method is responsible for evaluate user's expression using LIFO approach
	 */
	public double evaluate(String expression) {
		char[] tokens = expression.toCharArray();

		//stack for storing value
		Stack<Double> value = new Stack<Double>();

		//stack for storing the operator and parenthesis
		Stack<Character> operator = new Stack<Character>();

		//iterate each character
		for (int i = 0; i < tokens.length; i++) {
			
			//if white space then ignore
			if (tokens[i] == ' ') {
				continue;
			}

			//if char is a number between 0 and 9 then capture it in value stack
			if (tokens[i] >= '0' && tokens[i] <= '9') {
				StringBuffer sbuf = new StringBuffer();
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
					sbuf.append(tokens[i++]);
				}
				i--;
				value.push(Double.parseDouble(sbuf.toString()));
			} 
			//if char is a '(' then capture it in operator stack
			else if (tokens[i] == '(') {
				operator.push(tokens[i]);
			} 
			//if char is a ')' then iterate still '(' and compute its values within that and store the result in value stack 
			else if (tokens[i] == ')') {
				while (operator.peek() != '(') {
					value.push(applyOpr(operator.pop(), value.pop(), value.pop()));
				}
				operator.pop();
			} 
			//if char is a operator then based on the precedence compute it
			else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
				while (!operator.empty() && hasPrecedence(tokens[i], operator.peek())) {
					value.push(applyOpr(operator.pop(), value.pop(), value.pop()));
				}
				operator.push(tokens[i]);
			}
		}

		//compute based on the stack operator and values
		while (!operator.empty()) {
			value.push(applyOpr(operator.pop(), value.pop(), value.pop()));
		}

		//return final computed value
		return value.pop();
	}

	public List<Expression> getList() {

		return baseDao.getAll();
	}

	/**
	 * Check operator precedence
	 * 
	 * @param expression
	 * @return
	 */

	private boolean hasPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

	/**
	 * Compute the value
	 * 
	 * @param op
	 * @param b
	 * @param a
	 * @return
	 */
	private double applyOpr(char op, double b, double a) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		}
		return 0;
	}
}
