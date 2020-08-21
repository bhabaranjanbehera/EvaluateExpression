/**
 * 
 */
package com.eval.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eval.entity.Expression;
import com.eval.service.EvalService;

/**
 * This class is responsible for handing Spring request
 * 
 * @author BhabaranjanBehera
 *
 */

@Controller
public class ExpressionController {
	private static final List<Character> ALLOWED_CHARACTERS = Arrays.asList('(',')','+','-','*','/');
	@Autowired
	private EvalService service;

	@RequestMapping(value = "/evalExp", method = RequestMethod.POST)
	public String evalExp(@ModelAttribute("SpringWeb")Expression exp, ModelMap model) {
		try {
			if(!isValid(exp.getExpression())) {
				throw new Exception("Supplied Expression desn't have any Math Operators");
			}
			service.evalAndSave(exp.getExpression());
			model.addAttribute("list", service.getList());
		}catch(Exception ex) {
			ex.printStackTrace();
			model.addAttribute("errorMessage", ex.getMessage());
			model.addAttribute("command", exp);
			return "input";
		}
		return "output";
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public ModelAndView exp() {
		return new ModelAndView("input", "command", new Expression());
	}

	private boolean isValid(String expression) {
		return expression.chars()
        .mapToObj(x -> (char) x)
        .filter(ch->ALLOWED_CHARACTERS.contains(ch))
        .findAny().isPresent();
	}
}
