/**
 * 
 */
package com.eval.tagext;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.eval.service.EvalService;
import com.eval.util.EvalContextProvider;

/**
 * 
 * Custom tag for displaying calculation result
 * 
 * @author BhabaranjanBehera
 *
 */
public class DisplayResultTagHandler extends TagSupport {

	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {

			EvalService service = EvalContextProvider.getApplicationContext().getBean(EvalService.class);
			if (service.getList() != null && service.getList().size() > 0) {
				out.print(service.getList().get(0).getExpression() + "<strong> = </strong>"
						+ service.getList().get(0).getResult());
			} else {
				out.print("No Data");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return SKIP_BODY;
	}

}
