package com.example.com.redcrad;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

public class real {
	String num;
	real(String num)
	{
		String str=num.trim();
		if(str.contains("^"))
			str=str.replace("^","**");
		if(str.contains("∏"))
			str=str.replace("∏","pi");
		if(str.contains("√"))
			str=str.replace("√","sqrt");
		this.num=str;
	}
	real()
	{}
	real add(real r1)
	{
		real r=new real();
		Python py = Python.getInstance();
		PyObject obj1 = py.getModule("real").callAttr("add", this.num,r1.num);
		String expr = obj1.toJava(String.class);
		r.num=expr;
		return r;
	}
	real sub(real r1)
	{
		real r=new real();
		Python py = Python.getInstance();
		PyObject obj1 = py.getModule("real").callAttr("sub", this.num,r1.num);
		String expr = obj1.toJava(String.class);
		r.num=expr;
		return r;
	}
	real mul(real r1)
	{
		real r=new real();
		Python py = Python.getInstance();
		PyObject obj1 = py.getModule("real").callAttr("mul", this.num,r1.num);
		String expr = obj1.toJava(String.class);
		r.num=expr;
		return r;
	}
	real div(real r1)
	{
		real r=new real();
		Python py = Python.getInstance();
		PyObject obj1 = py.getModule("real").callAttr("div", this.num,r1.num);
		String expr = obj1.toJava(String.class);
		r.num=expr;
		return r;
	}
	public String toString()
	{
		String str=num;
		if(str.contains("**"))
			str=str.replace("**","^");
		if(str.contains("pi"))
			str=str.replace("pi","∏");
		if(str.contains("sqrt"))
			str=str.replace("sqrt","√");
		return str.trim();
	}
}