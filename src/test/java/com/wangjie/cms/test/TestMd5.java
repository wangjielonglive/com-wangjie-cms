package com.wangjie.cms.test;

import org.junit.Test;

import com.wangjie.utils.Md5Utils;

public class TestMd5 {
	@Test
	public void testMd5() {
		String s = "wo hhhh";
		String m = Md5Utils.md5("s");
		System.out.println("密文是:"+m);
	}
}
