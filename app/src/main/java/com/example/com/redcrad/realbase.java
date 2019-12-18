package com.example.com.redcrad;

import java.util.ArrayList;

public class realbase
{
	public realbase(double molecule, double denominator, double rootMol, double rootDen) {
		this.molecule = molecule;
		this.Denominator = denominator;
		this.rootMol = rootMol;
		this.rootDen = rootDen;
	}

	public realbase() {
		this.molecule = 0;
		this.Denominator = this.rootMol = this.rootDen = 1;
	}

	public realbase(String string) {
		{
			string = string.trim();
			if (string.matches("√\\d*+(\\.\\d*)?"))
			{
				this.rootMol = Double.parseDouble(string.substring(1));
				this.molecule = this.Denominator = this.rootDen = 1;
			}
			else if (string.matches("-√\\d*+(\\.\\d*)?"))
			{
				this.rootMol = Double.parseDouble(string.substring(2));
				this.molecule = -1;
				this.Denominator = this.rootDen = 1;
			}
			else if (string.matches("√\\d*+(\\.\\d*)?/\\d*+(\\.\\d*)?"))
			{
				String[] str = string.substring(1).split("/");
				this.rootMol = Double.parseDouble(str[0]);
				this.Denominator = Double.parseDouble(str[1]);
				this.rootDen = this.molecule = 1;
			}
			else if (string.matches("-√\\d*+(\\.\\d*)?/\\d*+(\\.\\d*)?"))
			{
				String[] str = string.substring(2).split("/");
				this.rootMol = Double.parseDouble(str[0]);
				this.Denominator = Double.parseDouble(str[1]);
				this.rootDen = 1;
				this.molecule = -1;
			}
			else if (string.matches("\\d*+(\\.\\d*)?√\\d*+(\\.\\d*)?"))
			{
				String[] s = string.split("√");
				this.molecule = Double.parseDouble(s[0]);
				this.rootMol = Double.parseDouble(s[1]);
				this.rootDen = this.Denominator = 1;
			}
			else if (string.matches("-\\d*+(\\.\\d*)?√\\d*+(\\.\\d*)?"))
			{
				String[] s = string.split("√");
				this.molecule = Double.parseDouble(s[0]);
				this.rootMol = Double.parseDouble(s[1]);
				this.rootDen = this.Denominator = 1;
			}
			else if (string.matches("\\d*+(\\.\\d*)?√\\d*+(\\.\\d*)?/\\d*+(\\.\\d*)?"))
			{
				String[] s = string.split("[√/]");
				this.molecule = Double.parseDouble(s[0]);
				this.rootMol = Double.parseDouble(s[1]);
				this.rootDen = 1;
				this.Denominator = Double.parseDouble(s[2]);
			}
			else if (string.matches("-\\d*+(\\.\\d*)?√\\d*+(\\.\\d*)?/\\d*+(\\.\\d*)?"))
			{
				String[] s = string.split("[√/]");
				this.molecule = Double.parseDouble(s[0]);
				this.rootMol = Double.parseDouble(s[1]);
				this.rootDen = 1;
				this.Denominator = Double.parseDouble(s[2]);
			}
			else if (string.matches("-\\d*+(\\.\\d*)?/\\d*+(\\.\\d*)?"))
			{
				String[] s = string.split("/");
				this.molecule = Double.parseDouble(s[0]);
				this.Denominator = Double.parseDouble(s[1]);
				this.rootMol = this.rootDen = 1;
			}
			else if (string.matches("\\d*+(\\.\\d*)?/\\d*+(\\.\\d*)?"))
			{
				String[] s = string.split("/");
				this.molecule = Double.parseDouble(s[0]);
				this.Denominator = Double.parseDouble(s[1]);
				this.rootMol = this.rootDen = 1;
			}
			else if (string.matches("-\\d*+(\\.\\d*)?\\d*+(\\.\\d*)?"))
			{
//				String[] s = string.split("/");
				this.molecule = Double.parseDouble(string);
				this.Denominator = Double.parseDouble("1");
				this.rootMol = this.rootDen = 1;
			}
			else
			{
				try
				{
					this.molecule = Double.parseDouble(string);
					this.Denominator = this.rootDen = this.rootMol = 1;
				}
				catch (Exception e)
				{
					System.out.println("Error");

				}
			}
		}
	}

	double molecule, Denominator, rootMol, rootDen;

	String getmolecule()
	{
		String result;
		String ss = toString();
		if (ss.indexOf("/") == -1)
		{
			realbase r2 = new realbase(toString());
			r2 = r2.mul(r2);
			result = r2.toString();
		}
		else
		{
			String s1 = "√" + Integer.parseInt(ss.substring(0, ss.indexOf("/")))
					* Integer.parseInt(ss.substring(ss.indexOf("/") + 1, ss.length()));
			result = s1;
		}
		return result;

	}

	String getdenominator()
	{
		String result;
		String ss = toString();
		if (ss.indexOf("/") == -1)
		{
			result = toString();
		}
		else
		{
			String s1 = String.valueOf(Integer.parseInt(ss.substring(ss.indexOf("/") + 1, ss.length())));
			result = s1;
		}
		return result;
	}

	public double getMolecule()
	{
		return molecule;
	}

	public void setMolecule(double molecule)
	{
		this.molecule = molecule;
	}

	public double getDenominator()
	{
		return Denominator;
	}

	public void setDenominator(double denominator)
	{
		Denominator = denominator;
	}

	public double getRootMol()
	{
		return rootMol;
	}

	public void setRootMol(double rootMol)
	{
		this.rootMol = rootMol;
	}

	public double getRootDen()
	{
		return rootDen;
	}

	public void setRootDen(double rootDen)
	{
		this.rootDen = rootDen;
	}

	public boolean equals(realbase number)
	{
		return this.rootDen == number.rootDen && this.rootMol == number.rootMol;
	}

	public realbase add(realbase number)
	{
		if (!this.equals(number))
		{
			System.out.println("Error");
			return null;
		}
		realbase num = new realbase();
		num.molecule = this.molecule * number.Denominator + this.Denominator * number.molecule;
		num.Denominator = this.Denominator * number.Denominator;
		num.rootMol = this.rootMol;
		num.rootDen = this.rootDen;
		num.optimization();
		return num;
	}

	public realbase sub(realbase number)
	{
		if (!this.equals(number))
		{
			System.out.println("Error");
			return null;
		}
		realbase num = new realbase();
		num.molecule = this.molecule * number.Denominator - this.Denominator * number.molecule;
		num.Denominator = this.Denominator * number.Denominator;
		num.rootMol = this.rootMol;
		num.rootDen = this.rootDen;
		num.optimization();
		return num;
	}

	public realbase mul(realbase number)
	{
		realbase num = new realbase();
		num.molecule = this.molecule * number.molecule;
		num.Denominator = this.Denominator * number.Denominator;
		num.rootMol = this.rootMol * number.rootMol;
		num.rootDen = this.rootDen * this.rootDen;
		num.optimization();
		return num;
	}

	public realbase div(realbase number)
	{
		realbase num = new realbase(1, 1, 1, 1);
		num.molecule *= this.molecule * number.Denominator;
		num.Denominator *= this.Denominator * number.molecule;
		num.rootMol *= this.rootMol * number.rootDen;
		num.rootDen *= this.rootDen * number.rootMol;
		num.optimization();
		return num;
	}

	public double gcd(double d, double e)
	{
		double c;
		while (e > 0)
		{
			c = d;
			d = e;
			e = c % e;
		}
		return d;
	}

	public void optimization()
	{
		if (this.rootDen > 1)
		{
			this.rootMol *= this.rootDen;
			this.Denominator *= this.rootDen;
			this.rootDen = 1;
		}
		for (double i = 2; i <= this.rootMol / i; i++)
		{
			if (this.rootMol % ( i * i) == 0)
			{
				this.rootMol /= ( i * i);
				this.molecule *= i;
			}
		}
		double k = gcd(Math.abs(this.molecule), Math.abs(this.Denominator));
		if (k > 1)
		{
			this.molecule /= k;
			this.Denominator /= k;
		}
		if (this.Denominator < 0)
		{
			this.Denominator = -this.Denominator;
			this.molecule = -this.molecule;
		}
		if (this.molecule == 0 || this.rootMol == 0)
		{
			this.Denominator = 1;
			this.rootDen = 1;
			this.rootMol = 1;
			this.molecule = 0;
		}
	}

	public String toString()
	{
		this.optimization();
		StringBuilder sb = new StringBuilder();
		if (this.molecule == 0 || this.rootMol == 0)
			return "0";
		if (this.molecule == 1 && this.Denominator == 1 && this.rootMol == 1 && this.rootDen == 1)
			return "1";
		if (this.molecule > 0)
			sb.append("+");
		else if (this.molecule < 0)
		{
			sb.append("-");
		}
		if (Math.abs(this.molecule) > 0 &&this.molecule!=1)
			sb.append(Math.abs(this.molecule));
		else if (this.Denominator == 1 && this.rootDen == 1 && this.rootMol == 1)
		{
			sb.append(Math.abs(this.molecule));
		}
		if (this.rootMol > 0 &&this.rootMol!=1)
		{
			sb.append("√").append(this.rootMol);
		}
		if (this.Denominator > 0 &&this.Denominator!=1)
		{
			if (this.molecule == 1 || this.molecule == -1)
				if (this.rootMol == 1)
					sb.append(1);
			sb.append("/").append(this.Denominator);
		}

		String ans = sb.toString();
		if (ans.equals("+0"))
			return "0";
		ans = ans.replace("+0", "+");
		ans = ans.replace("+-", "-");
		ans = ans.replace("++", "+");
		if (ans.endsWith("+"))
			ans.subSequence(0, ans.length() - 1);
		if (ans.startsWith("+"))
			return ans.substring(1);
		return ans;
	}
}
