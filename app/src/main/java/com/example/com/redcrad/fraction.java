package com.example.com.redcrad;

public class fraction {
	String numerator;
	String denominator;
	fraction(String s)
	{
		s=s.trim();
		if(s.indexOf("/")!=-1)
		{
			int sign=s.indexOf("/");
			numerator=s.substring(0, sign);
			denominator=s.substring(sign+1,s.length());
		}
		else if(s.indexOf("√")!=-1)
		{
			numerator=String.valueOf(Double.parseDouble(s.substring(s.indexOf("√")+1,s.length())));
			denominator=s;
		}
		else
		{
			numerator=String.valueOf(Double.parseDouble(s)*Double.parseDouble(s));
			denominator=s;
		}
	}
	fraction(String n,String d)
	{
		numerator=n.trim();
		denominator=d.trim();
	}
	fraction()
	{}
	fraction add(fraction f)                                                    //加
	{
		String one,two;
		fraction f1=new fraction();
		int root_n=f.getnumerator().indexOf("√");
		int root_d=f.getdenominator().indexOf("√");
		if(numerator.indexOf('√')==-1)                                                           //当f1的分子不为根号时
		{
			if(root_d==-1)                                                                      //当f2的分母不为根号时
			{
				one=String.valueOf(Double.parseDouble(numerator)*Double.parseDouble(f.getdenominator()));
			}
			else                                                                               //当f2的分母为根号时
			{
				double num_f=Double.parseDouble(numerator)*Double.parseDouble(numerator);
				num_f*=Double.parseDouble(f.getdenominator().substring(root_d,f.getdenominator().length()));
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					one=String.valueOf((int)Math.sqrt(num_f));
				}
				else
				{
					one="√"+(double)num_f;
				}
			}

		}
		else                                                                     //当f1的分子为根号时
		{
			if(root_d==-1)                                                                      //当f2的分母不为根号时
			{
				double num_f=Double.parseDouble(numerator.substring(numerator.indexOf("√")+1,numerator.length()));
				num_f*=Double.parseDouble(f.getdenominator())*Double.parseDouble(f.getdenominator());
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					one=String.valueOf((int)Math.sqrt(num_f));
				}
				else
				{
					one="√"+(double)num_f;
				}
			}
			else                                                                               //当f2的分母为根号时
			{
				double num_f=Double.parseDouble(numerator.substring(numerator.indexOf("√")+1,numerator.length()));
				num_f*=Double.parseDouble(f.getdenominator().substring(root_d,f.getdenominator().length()));
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					one=String.valueOf((int)Math.sqrt(num_f));
				}
				else
				{
					one="√"+(double)num_f;
				}
			}
		}
		if(denominator.indexOf('√')==-1)                                                           //当f1的分母不为根号时
		{
			if(root_n==-1)                                                                      //当f2的分子不为根号时
			{
				two=String.valueOf(Double.parseDouble(denominator)*Double.parseDouble(f.getnumerator()));
			}
			else                                                                               //当f2的分子为根号时
			{
				double num_f=Double.parseDouble(denominator)*Double.parseDouble(denominator);
				num_f*=Double.parseDouble(f.getnumerator().substring(root_n+1,f.getnumerator().length()));
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					two=String.valueOf((int)Math.sqrt(num_f));
				}
				else
				{
					two="√"+(double)num_f;
				}
			}
			if(root_d==-1)                                                                //分母处理
			{
				f1.setdenominator(String.valueOf(Double.parseDouble(denominator)*Double.parseDouble(f.getdenominator())));
			}
			else
			{
				double num_f=Double.parseDouble(f.getdenominator().substring(root_d+1,f.getdenominator().length()));
				num_f*=Double.parseDouble(denominator)*Double.parseDouble(denominator);
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setdenominator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setdenominator("√"+(double)num_f);
				}
			}
		}
		else                                                                     //当f1的分母为根号时
		{
			if(root_n==-1)                                                                      //当f2的分子不为根号时
			{
				double num_f=Double.parseDouble(denominator.substring(denominator.indexOf("√")+1,denominator.length()));
				num_f*=Double.parseDouble(f.getnumerator())*Double.parseDouble(f.getnumerator());
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					two=String.valueOf((int)Math.sqrt(num_f));
				}
				else
				{
					two="√"+(double)num_f;
				}
			}
			else                                                                               //当f1的分母为根号时
			{
				double num_f=Double.parseDouble(denominator.substring(denominator.indexOf("√")+1,denominator.length()));
				num_f*=Double.parseDouble(f.getnumerator().substring(root_n,f.getnumerator().length()));
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					two=String.valueOf((int)Math.sqrt(num_f));
				}
				else
				{
					two="√"+(double)num_f;
				}
			}
			if(root_d==-1)                                                                //分母处理
			{
				double num_f=Double.parseDouble(denominator.substring(denominator.indexOf("√")+1,denominator.length()));
				num_f*=Double.parseDouble(f.getnumerator())*Double.parseDouble(f.getnumerator());
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setdenominator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setdenominator("√"+(double)num_f);
				}
			}
			else
			{
				double num_f=Double.parseDouble(f.getdenominator().substring(root_d+1,f.getdenominator().length()));
				num_f*=Double.parseDouble(denominator);
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setdenominator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setdenominator("√"+(double)num_f);
				}
			}
		}
		if(one.indexOf("√")==-1&&two.indexOf("√")==-1)
		{
			f1.setnumerator(String.valueOf(Double.parseDouble(one)+Double.parseDouble(two)));
		}
		else
		{
			f1.setnumerator(one+"+"+two);
		}
		return f1;

	}
	fraction add(String s)                                                       //重载加，适应分数，根号，数值
	{
		fraction f;
		if(s.indexOf("/")!=-1)
		{
			fraction f2=new fraction(s);
			f=this.add(f2);
		}
		else if(s.indexOf("√")!=-1)
		{
			fraction f2=new fraction(Double.parseDouble(s.substring(s.indexOf("√")+1,s.length()))+"/"+s);
			f=this.add(f2);
		}
		else
		{
			fraction f2=new fraction(Double.parseDouble(s)*Double.parseDouble(s)+"/"+Double.parseDouble(s));
			f=this.add(f2);
		}
		return f;
	}
	fraction subtract(fraction f)                                        //减
	{
		String one,two;
		fraction f1=new fraction();
		int root_n=f.getnumerator().indexOf("√");
		int root_d=f.getdenominator().indexOf("√");
		if(numerator.indexOf('√')==-1)                                                           //当f1的分子不为根号时
		{
			if(root_d==-1)                                                                      //当f2的分母不为根号时
			{
				one=String.valueOf(Double.parseDouble(numerator)*Double.parseDouble(f.getdenominator()));
			}
			else                                                                               //当f2的分母为根号时
			{
				double num_f=Double.parseDouble(numerator)*Double.parseDouble(numerator);
				num_f*=Double.parseDouble(f.getdenominator().substring(root_d,f.getdenominator().length()));
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					one=String.valueOf((int)Math.sqrt(num_f));
				}
				else
				{
					one="√"+(double)num_f;
				}
			}

		}
		else                                                                     //当f1的分子为根号时
		{
			if(root_d==-1)                                                                      //当f2的分母不为根号时
			{
				double num_f=Double.parseDouble(numerator.substring(numerator.indexOf("√")+1,numerator.length()));
				num_f*=Double.parseDouble(f.getdenominator())*Double.parseDouble(f.getdenominator());
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					one=String.valueOf((int)Math.sqrt(num_f));
				}
				else
				{
					one="√"+(double)num_f;
				}
			}
			else                                                                               //当f2的分母为根号时
			{
				double num_f=Double.parseDouble(numerator.substring(numerator.indexOf("√")+1,numerator.length()));
				num_f*=Double.parseDouble(f.getdenominator().substring(root_d,f.getdenominator().length()));
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					one=String.valueOf((int)Math.sqrt(num_f));
				}
				else
				{
					one="√"+(double)num_f;
				}
			}
		}
		if(denominator.indexOf('√')==-1)                                                           //当f1的分母不为根号时
		{
			if(root_n==-1)                                                                      //当f2的分子不为根号时
			{
				two=String.valueOf(Double.parseDouble(denominator)*Double.parseDouble(f.getnumerator()));
			}
			else                                                                               //当f2的分子为根号时
			{
				double num_f=Double.parseDouble(denominator)*Double.parseDouble(denominator);
				num_f*=Double.parseDouble(f.getnumerator().substring(root_n+1,f.getnumerator().length()));
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					two=String.valueOf((int)Math.sqrt(num_f));
				}
				else
				{
					two="√"+(double)num_f;
				}
			}
			if(root_d==-1)                                                                //分母处理
			{
				f1.setdenominator(String.valueOf(Double.parseDouble(denominator)*Double.parseDouble(f.getdenominator())));
			}
			else
			{
				double num_f=Double.parseDouble(f.getdenominator().substring(root_d+1,f.getdenominator().length()));
				num_f*=Double.parseDouble(denominator)*Double.parseDouble(denominator);
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setdenominator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setdenominator("√"+(double)num_f);
				}
			}
		}
		else                                                                     //当f1的分母为根号时
		{
			if(root_n==-1)                                                                      //当f2的分子不为根号时
			{
				double num_f=Double.parseDouble(denominator.substring(denominator.indexOf("√")+1,denominator.length()));
				num_f*=Double.parseDouble(f.getnumerator())*Double.parseDouble(f.getnumerator());
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					two=String.valueOf((int)Math.sqrt(num_f));
				}
				else
				{
					two="√"+(double)num_f;
				}
			}
			else                                                                               //当f1的分母为根号时
			{
				double num_f=Double.parseDouble(denominator.substring(denominator.indexOf("√")+1,denominator.length()));
				num_f*=Double.parseDouble(f.getnumerator().substring(root_n,f.getnumerator().length()));
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					two=String.valueOf((int)Math.sqrt(num_f));
				}
				else
				{
					two="√"+(double)num_f;
				}
			}
			if(root_d==-1)                                                                //分母处理
			{
				double num_f=Double.parseDouble(denominator.substring(denominator.indexOf("√")+1,denominator.length()));
				num_f*=Double.parseDouble(f.getnumerator())*Double.parseDouble(f.getnumerator());
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setdenominator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setdenominator("√"+(double)num_f);
				}
			}
			else
			{
				double num_f=Double.parseDouble(f.getdenominator().substring(root_d+1,f.getdenominator().length()));
				num_f*=Double.parseDouble(denominator);
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setdenominator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setdenominator("√"+(double)num_f);
				}
			}
		}
		if(one.indexOf("√")==-1&&two.indexOf("√")==-1)
		{
			f1.setnumerator(String.valueOf(Double.parseDouble(one)-Double.parseDouble(two)));
		}
		else
		{
			f1.setnumerator(one+"-"+two);
		}
		return f1;
	}
	fraction subtract(String s)                                                       //重载减，适应分数，根号，数值
	{
		fraction f;
		if(s.indexOf("/")!=-1)
		{
			fraction f2=new fraction(s);
			f=this.subtract(f2);
		}
		else if(s.indexOf("√")!=-1)
		{
			fraction f2=new fraction(Double.parseDouble(s.substring(s.indexOf("√")+1,s.length()))+"/"+s);
			f=this.subtract(f2);
		}
		else
		{
			fraction f2=new fraction(Double.parseDouble(s)*Double.parseDouble(s)+"/"+Double.parseDouble(s));
			f=this.subtract(f2);
		}
		return f;
	}
	fraction multiply(fraction f)                                           //乘
	{
		fraction f1=new fraction();
		int root_n=f.getnumerator().indexOf("√");
		int root_d=f.getdenominator().indexOf("√");
		if(root_n==-1)                                                                                //当f2的分子不为根号时
		{
			if(numerator.indexOf("√")==-1)                                                            //当f1的分子不为根号时
				f1.setnumerator(String.valueOf(Double.parseDouble(numerator)*Double.parseDouble(f.getnumerator())));
			else                                                                                      //当f1的分子为根号时
			{
				double num_f=Double.parseDouble(numerator.substring(numerator.indexOf("√")+1, numerator.length()));
				num_f*=Double.parseDouble(f.getnumerator())*Double.parseDouble(f.getnumerator());
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setnumerator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setnumerator("√"+(double)num_f);
				}
			}
		}
		else                                                                                          //当f2的分子为根号时
		{
			if(numerator.indexOf("√")==-1)                                                             //当f1的分子不为根号时
			{
				double num_f=Double.parseDouble(f.getnumerator().substring(root_n+1, f.getnumerator().length()));
				num_f*=Double.parseDouble(numerator)*Double.parseDouble(numerator);
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setnumerator(String.valueOf((int)Math.sqrt(num_f)));
				}
			}
			else                                                                                            //当f1的分子为根号时
			{
				System.out.println(numerator);
				System.out.println(f.getnumerator());
				double num_f=Double.parseDouble(numerator.substring(numerator.indexOf("√")+1, numerator.length()));
				num_f*=Double.parseDouble(f.getnumerator().substring(root_n+1, f.getnumerator().length()));
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{

					f1.setnumerator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setnumerator("√"+(double)num_f);
				}
			}
		}
		if(root_d==-1)                                                                                //当f2的分母不为根号时
		{
			if(denominator.indexOf("√")==-1)                                                            //当f1的分母不为根号时
				f1.setdenominator(String.valueOf(Double.parseDouble(denominator)*Double.parseDouble(f.getdenominator())));
			else                                                                                      //当f1的分母不为根号时
			{
				double num_f=Double.parseDouble(denominator.substring(denominator.indexOf("√")+1, denominator.length()));
				num_f*=Double.parseDouble(f.getdenominator())*Double.parseDouble(f.getdenominator());
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setnumerator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setnumerator("√"+(double)num_f);
				}
			}
		}
		else                                                                                               //当f2的分母为根号时
		{
			if(denominator.indexOf("√")==-1)                                                                //当f1的分母不为根号时
			{
				double num_f=Double.parseDouble(f.getdenominator().substring(root_d+1, f.getdenominator().length()));
				num_f*=Double.parseDouble(denominator)*Double.parseDouble(denominator);
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setdenominator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setdenominator("√"+(double)num_f);
				}
			}
			else                                                                                            //当f1的分母为根号时
			{
				double num_f=Double.parseDouble(denominator.substring(denominator.indexOf("√")+1, denominator.length()));
				num_f*=Double.parseDouble(f.getdenominator().substring(root_d+1, f.getdenominator().length()));
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setdenominator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setdenominator("√"+(double)num_f);
				}
			}
		}
		return f1;
	}
	fraction multiply(String s)                                                       //重载乘，适应分数，根号，数值
	{
		fraction f;
		if(s.indexOf("/")!=-1)
		{
			fraction f2=new fraction(s);
			f=this.multiply(f2);
		}
		else if(s.indexOf("√")!=-1)
		{
			fraction f2=new fraction(Double.parseDouble(s.substring(s.indexOf("√")+1,s.length()))+"/"+s);
			f=this.multiply(f2);
		}
		else
		{
			fraction f2=new fraction(Double.parseDouble(s)*Double.parseDouble(s)+"/"+Double.parseDouble(s));
			f=this.multiply(f2);
		}
		return f;
	}
	fraction division(fraction f)                                                     //除
	{
		fraction f1=new fraction();
		int root_n=f.getnumerator().indexOf("√");
		int root_d=f.getdenominator().indexOf("√");
		if(root_d==-1)                                                                               //当f2的分母不为根号时
		{
			if(numerator.indexOf("√")==-1)                                                           //当f1的分子不为根号时
				f1.setnumerator(String.valueOf(Double.parseDouble(numerator)*Double.parseDouble(f.getdenominator())));
			else                                                                                       //当f1的分子为根号时
			{
				double num_f=Double.parseDouble(numerator.substring(numerator.indexOf("√")+1, numerator.length()));
				num_f*=Double.parseDouble(f.getdenominator())*Double.parseDouble(f.getdenominator());
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setnumerator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setnumerator("√"+(double)num_f);
				}
			}
		}
		else                                                                                               //当f2的分母为根号时
		{
			if(numerator.indexOf("√")==-1)                                                                  //当f1的分子不为根号时
			{
				double num_f=Double.parseDouble(f.getdenominator().substring(root_d+1, f.getdenominator().length()));
				num_f*=Double.parseDouble(numerator)*Double.parseDouble(numerator);
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setnumerator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setnumerator("√"+(double)num_f);
				}
			}
			else                                                                                              //当f1的分子为根号时
			{
				double num_f=Double.parseDouble(numerator.substring(numerator.indexOf("√")+1, numerator.length()));
				num_f*=Double.parseDouble(f.getdenominator().substring(root_d+1, f.getdenominator().length()));
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setnumerator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setnumerator("√"+(double)num_f);
				}
			}
		}
		if(root_n==-1)                                                                                          //当f2的分子不为根号时
		{
			if(denominator.indexOf("√")==-1)                                                                      //当f1的分母不为根号时
				f1.setdenominator(String.valueOf(Double.parseDouble(denominator)*Double.parseDouble(f.getnumerator())));
			else                                                                                                    //当f1的分母为根号时
			{
				double num_f=Double.parseDouble(denominator.substring(denominator.indexOf("√")+1, denominator.length()));
				num_f*=Double.parseDouble(f.getnumerator())*Double.parseDouble(f.getnumerator());
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setdenominator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setdenominator("√"+(double)num_f);
				}
			}
		}
		else                                                                                                  //当f2的分子为根号时
		{
			if(denominator.indexOf("√")==-1)                                                                   //当f1的分母不为根号时
			{
				double num_f=Double.parseDouble(f.getnumerator().substring(root_n+1, f.getnumerator().length()));
				num_f*=Double.parseDouble(denominator)*Double.parseDouble(denominator);
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setdenominator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setdenominator("√"+(double)num_f);
				}
			}
			else                                                                                                 //当f1的分母为根号时
			{
				double num_f=Double.parseDouble(denominator.substring(denominator.indexOf("√")+1, denominator.length()));
				num_f*=Double.parseDouble(f.getnumerator().substring(root_n+1, f.getnumerator().length()));
				if((int)Math.sqrt(num_f)==Math.sqrt(num_f))
				{
					f1.setdenominator(String.valueOf((int)Math.sqrt(num_f)));
				}
				else
				{
					f1.setdenominator("√"+(double)num_f);
				}
			}
		}
		return f1;
	}
	fraction division(String s)                                                       //重载除，适应分数，根号，数值
	{
		fraction f;
		if(s.indexOf("/")!=-1)
		{
			fraction f2=new fraction(s);
			f=this.division(f2);
		}
		else if(s.indexOf("√")!=-1)
		{
			fraction f2=new fraction(Double.parseDouble(s.substring(s.indexOf("√")+1,s.length()))+"/"+s);
			f=this.division(f2);
		}
		else
		{
			fraction f2=new fraction(Double.parseDouble(s)*Double.parseDouble(s)+"/"+Double.parseDouble(s));
			f=this.division(f2);
		}
		return f;
	}
	String tostring()
	{
		if(numerator.indexOf("√")==-1&&denominator.indexOf("√")==-1)
		{
			double num=Double.parseDouble(numerator);
			double den=Double.parseDouble(denominator);
			if(num/simplify(num,den)==den/simplify(num,den))
			{
				return "1";
			}
			else if(den/simplify(num,den)==1)
			{
				return String.valueOf(num/simplify(num,den));
			}
			else if(den/simplify(num,den)==-1)
			{
				return "-"+num/simplify(num,den);
			}
			else
			{
				return num/simplify(num,den)+"/"+den/simplify(num,den);
			}
		}
		else
			return 	numerator+"/"+denominator;
	}
	double simplify(double x, double y){                                                //化简
		if(y == 0)
			return x;
		else
			return simplify(y,x%y);
	}
	String getnumerator()
	{
		return numerator;
	}
	String getdenominator()
	{
		return denominator;
	}
	void setnumerator(String n)
	{
		numerator=n;
	}
	void setdenominator(String d)
	{
		denominator=d;
	}
}
