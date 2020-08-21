/**
 * 
 */
package com.eval.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.eval.dao.ExpressionDao;
import com.eval.service.impl.EvalServiceImpl;

/**
 * @author BhabaranjanBehera
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ExpressionTest {

	@InjectMocks
	EvalService eval = new EvalServiceImpl();;
	@Mock
	private ExpressionDao baseDao;

	@Test
	public void testExpCalc() {
		double val = eval.evaluate("6+78");
		Assert.assertEquals(84, val, 0);

		double val1 = eval.evaluate("76*6+78");
		Assert.assertEquals(534.0, val1, 0);

	}

	
}
