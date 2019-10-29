package com.wangjie.cms.common;

public class CmsAssertJson {
	
	/**
	 *  断言处理
	 * @param expression
	 * @param msg
	 */
	public static void Assert(boolean expression,String msg) {
		if(!expression)
			throw new CmsExceptionView(msg);
	}
	

}
