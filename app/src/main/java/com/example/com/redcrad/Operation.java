package com.example.com.redcrad;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.math.BigInteger;
import java.util.ArrayList;

public class Operation {
    final static double PI = 3.141592653589793;
    static int num = 0;
    static TextView tt;
    static Context context;
    static real result;                                                    //最后的值
    static LinkQueue<matrix> link = new LinkQueue<matrix>();
    static void setcontext(Context con)                                     //初始化输出活动界面
    {
        context = con;
    }
    static void init()
    {
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
    }
    static void setext(TextView text)                                      //初始化输出界面
    {
        tt = text;
    }
    static matrix transFrom(String t)                                       //格式转化
    {
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '(')
                num1++;
            if (t.charAt(i) == ')')
                num2++;
            if (t.charAt(i) == ',' && t.charAt(i - 1) == ')')
                num3++;
            if (((t.charAt(i) >= 0 && t.charAt(i) <= 9) && t.charAt(i - 1) == '(') || ((t.charAt(i) >= 0 && t.charAt(i) <= 9) && t.charAt(i - 1) == ','))
                num4++;
        }
        if (num1 != num2)
            Toast.makeText(context, "提示:括号错误", Toast.LENGTH_SHORT).show();
        if (num4 % (num3 + 1) != 0)
            Toast.makeText(context, "提示:矩阵内数量错误", Toast.LENGTH_SHORT).show();
        t.trim();
        matrix m = new matrix(t.substring(t.indexOf("("),t.length()));
        if (t.charAt(0) == 't')
            m.transpose();
        if (t.charAt(0) >= '0' && t.charAt(0) <= '9'||(t.charAt(0)=='-'&&t.charAt(1) >= '0' && t.charAt(1) <= '9')) {
            int l = t.indexOf("(");
            String num = t.substring(0, l);
            m.nummult(num);
        }
        if (t.charAt(0) == '-') {
            m.neg();
        }
        return m;
    }
    static String transFrom_form(String s)                                     //格式转换公式
    {
        String str=s;
        if(str.contains("^"))
        str=str.replace("^","**");
        if(str.contains("∏"))
            str=str.replace("∏","pi");
        if(str.contains("√"))
            str=str.replace("√","sqrt");
        return str;
    }
    static String transFrom_form_out(String s)                                     //格式转换公式输出
    {
        String str=s;
        if(str.contains("**"))
            str=str.replace("**","^");
        if(str.contains("pi"))
            str=str.replace("pi","∏");
        if(str.contains("sqrrt"))
            str=str.replace("sqrt","√");
        return str;
    }
    static void check(String s) throws Exception {
        s.trim();
        int front = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                front = i;
                break;
            }
        }
        String prefix = s.substring(0, front);
        if (prefix.equals("norm"))                                                        //范数
        {
            matrix one = transFrom(s.substring(front, s.length()));                      //调用Jama
            real result = one.norm();
            tt.append("范数：  √" + result);
        } else if (prefix.equals("dim"))                                             //维数
        {
        } else if (prefix.equals("det"))                                                //行列式
        {
            matrix one = transFrom(s.substring(front, s.length()));
            real result = one.getresult();
            tt.append("值为：  " + result.toString());
        } else if (prefix.equals("inverse"))                                            //逆矩阵
        {
            matrix one = transFrom(s.substring(front,s.length()));
            matrix two=one.inverse();
            String s1 = two.showstring();
            tt.append("逆矩阵为："+"\n");
            format(s1);
        } else if (prefix.equals("ref"))                                                 //行阶梯形矩阵
        {
            matrix one = transFrom(s.substring(front, s.length()));
            one.Reduce(0);
            tt.append("行阶梯形矩阵为：  "+"\n" );
            format(one.showstring());
        } else if (prefix.equals("rank"))                                                 //调用Jama 求秩
        {
        } else if (prefix.equals("trace"))                                               //调用Jama求迹
        {
        } else if (prefix.equals("alg"))                                               //代数余子式
        {
            matrix one = transFrom(s.substring(front, s.length()));
            matrix two=one.AC();
            tt.append("代数余子式：\n");
            format(two.showstring());
        } else if (prefix.equals("dim"))                                                  //调用Jama求维数
        {
        } else if (prefix.equals("adj"))                                                  //调用Jama求共轭(实数共轭)
        {
            matrix one = transFrom(s.substring(front, s.length()));
            one.transpose();
            format(one.showstring());
        } else if (prefix.equals("qr"))                                                  //调用Jama求QR
        {
        } else if (prefix.equals("lu"))                                                  //调用Jama求LU
        {
        } else if (prefix.equals("svd"))                                                  //调用Jama求SVD
        {

        } else if (prefix.equals("chol"))                                                  //调用Jama求乔利斯基分解
        {
        } else if (prefix.equals("eig"))                                               //调用Jama求特征值
        {
        } else if (prefix.equals("com"))                                               //组合
        {
            tt.append(com(s));
        } else if (prefix.equals("nop"))                                               //排列
        {
            tt.append(nop(s));
        } else if (prefix.equals("model"))                                            //模
        {
            tt.append("模:" + model(s.substring(front, s.length())));
        } else if (prefix.equals("cos")) {
            tt.append("余弦值为:" + cos(s.substring(front, s.length())));
        } else if (prefix.equals("dc")) {
           String res[]=dc(s.substring(front, s.length()).trim());
            tt.append("cosα:" + res[0] + "\n");                   //输出
            tt.append("cosβ:" + res[1] + "\n");
            tt.append("cosγ:" + res[2] + "\n");
        } else if (prefix.equals("projection")) {
            tt.append("向量1在向量2上的投影为:" + projection(s.substring(front, s.length())));
        } else if (prefix.equals("product")) {
            tt.append("向量积：");
           String str=product(s.substring(front, s.length()));
            str = str.replace("(", " ");
            str = str.replace(")", " ");
            String[] st = str.split(",");
            tt.append(st[0]+"i"+st[1]+"j"+st[2]+"k");
        }else if (prefix.equals("da"))
        {
           int angle[]=da(s.substring(front, s.length()));
            tt.append("α:" + angle[0] + "\n");                   //输出
            tt.append("β:" + angle[1] + "\n");
            tt.append("γ:" + angle[2] + "\n");
        }
        else if(prefix.equals("sd"))
        {
            tt.append(sd("同向单位向量："+sd(s.substring(front, s.length()))));
        }
        else if(prefix.equals("od"))
        {
            tt.append("反向单位向量："+od(s.substring(front, s.length())));
        }
        else if(prefix.equals("mixed"))
        {
            tt.append("混合积："+mixed(s.substring(front, s.length())));
        }
        else if(prefix.equals("dot"))
        {
            tt.append("数量积："+dot(s.substring(front, s.length())));
        }
        else if(prefix.equals("diff"))                                                                    //求导
        {
            String str=s.substring(front,s.length());
            int sum=0;
            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)==',')
                    sum++;
            }
            int frist=str.indexOf("(");
            str=str.substring(frist+1,str.length());
            int second=str.lastIndexOf(")");
            str=str.substring(0,second);
            String str2[] = str.split(",");
            for(int i=0;i<str2.length;i++)
            {
                str2[i]=str2[i].trim();
            }
            if(sum==2) {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("deri", str2[0], str2[1], str2[2]);
                String diff = obj1.toJava(String.class);
                tt.append(transFrom_form_out(diff) + "\n");
            }else if(sum==4)
            {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("deri_two", str2[0],str2[1] ,str2[2],str2[3],str2[4]);
                String diff = obj1.toJava(String.class);
                tt.append(transFrom_form_out(diff) + "\n");
            }
            else
            {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("deri_three", str2[0],str2[1],str2[2],str2[3],str2[4],str2[5],str2[6]);
                String diff = obj1.toJava(String.class);
                tt.append(transFrom_form_out(diff) + "\n");

            }
        }
        else if(prefix.equals("inte"))                                                 //定积分
        {
            String str=s.substring(front,s.length());
            int sum=0;
            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)==',')
                    sum++;
            }
            int frist=str.indexOf("(");
            str=str.substring(frist+1,str.length());
            int second=str.lastIndexOf(")");
            str=str.substring(0,second);
            String str2[] = str.split(",");
            for(int i=0;i<str2.length;i++)
            {
                str2[i]=str2[i].trim();
            }
            if(sum==3) {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("inte", str2[0],str2[1],str2[2],str2[3]);
                String expr = obj1.toJava(String.class);
                tt.append(transFrom_form_out(expr) + "\n");
            }else if(sum==6)
            {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("inte_two", str2[0],str2[1],str2[2],str2[3],str2[4],str2[5],str2[6]);
                String expr = obj1.toJava(String.class);
                tt.append(transFrom_form_out(expr) + "\n");
            }
            else
            {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("inte_three", str2[0],str2[1],str2[2],str2[3],str2[4],str2[5],str2[6],str2[7],str2[8],str2[9]);
                String expr = obj1.toJava(String.class);
                tt.append(transFrom_form_out(expr) + "\n");

            }
        }
        else if(prefix.equals("nointe"))                                                       //不定积分
        {
            String str=s.substring(front,s.length());
            int sum=0;
            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)==',')
                    sum++;
            }
            int frist=str.indexOf("(");
            str=str.substring(frist+1,str.length());
            int second=str.lastIndexOf(")");
            str=str.substring(0,second);
            String str2[] = str.split(",");
            for(int i=0;i<str2.length;i++)
            {
                str2[i]=str2[i].trim();
            }

            if(sum==1) {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("inte_no", str2[0],str2[1]);
                String expr = obj1.toJava(String.class);
                tt.append(transFrom_form_out(expr) + "\n");
            }else if(sum==2)
            {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("inte_no_two", str2[0],str2[1],str2[2]);
                String expr = obj1.toJava(String.class);
                tt.append(transFrom_form_out(expr) + "\n");
            }
            else
            {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("inte_no_three", str2[0],str2[1],str2[2],str2[3],str2[4]);
                String expr = obj1.toJava(String.class);
                tt.append(transFrom_form_out(expr) + "\n");

            }
        }
        else if(prefix.equals("limit"))                                                               //极限
        {
            String str=s.substring(front,s.length());
            int frist=str.indexOf("(");
            str=str.substring(frist+1,str.length());
            int second=str.lastIndexOf(")");
            str=str.substring(0,second);
            String str2[] = str.split(",");
            for(int i=0;i<str2.length;i++)
            {
                str2[i]=str2[i].trim();
            }
            str2[0] = transFrom_form(str2[0]);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("calculus").callAttr("lim", str2[0],str2[1],str2[2]);
            String expr = obj1.toJava(String.class);
            tt.append(transFrom_form_out(expr) + "\n");
        }
        else if(prefix.equals("eva"))                                            //表达式求值
        {
            String str=s.substring(front,s.length());
            int sum=0;
            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)==',')
                    sum++;
            }
            int frist=str.indexOf("(");
            str=str.substring(frist+1,str.length());
            int second=str.lastIndexOf(")");
            str=str.substring(0,second);
            String str2[] = str.split(",");
            for(int i=0;i<str2.length;i++)
            {
                str2[i]=str2[i].trim();
            }
            if(sum==2)
            {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("algebra").callAttr("exp", str2[0],str2[1],str2[2]);
                String expr = obj1.toJava(String.class);
                tt.append(transFrom_form_out(expr) + "\n");
            }
            if(sum==4)
            {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("algebra").callAttr("exp_two", str2[0],str2[1],str2[2],str2[3],str2[4]);
                String expr = obj1.toJava(String.class);
                tt.append(transFrom_form_out(expr) + "\n");
            }
        }
        else if(prefix.equals("equ"))                                                                 //解方程
        {
            String str=s.substring(front,s.length());
            int sum=0;
            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)==',')
                    sum++;
            }
            int frist=str.indexOf("(");
            str=str.substring(frist+1,str.length());
            int second=str.lastIndexOf(")");
            str=str.substring(0,second);
            String str2[] = str.split(",");
            for(int i=0;i<str2.length;i++)
            {
                str2[i]=str2[i].trim();
            }
            if(sum==2)
            {
                str2[0] = transFrom_form(str2[0]);
                str2[1] = transFrom_form(str2[1]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("algebra").callAttr("equ", str2[0],str2[1],str2[2]);
                String expr = obj1.toJava(String.class);
                tt.append(transFrom_form_out(expr) + "\n");
            }
            if(sum==4)
            {
                str2[0] = transFrom_form(str2[0]);
                str2[1] = transFrom_form(str2[1]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("algebra").callAttr("equ_set_lin", str2[0],str2[1],str2[2],str2[3],str2[4]);
                String expr = obj1.toJava(String.class);
                tt.append(transFrom_form_out(expr) + "\n");
            }
            if(sum==3)
            {
                str2[0] = transFrom_form(str2[0]);
                str2[1] = transFrom_form(str2[1]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("algebra").callAttr("equ_set_nonlin_two", str2[0],str2[1],str2[2],str2[3]);
                String expr = obj1.toJava(String.class);
                tt.append(transFrom_form_out(expr) + "\n");
            }
            if(sum==5)
            {
                str2[0] = transFrom_form(str2[0]);
                str2[1] = transFrom_form(str2[1]);
                str2[2] = transFrom_form(str2[2]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("algebra").callAttr("equ_set_nonlin_three", str2[0],str2[1],str2[2],str2[3],str2[4],str2[5]);
                String expr = obj1.toJava(String.class);
                tt.append(transFrom_form_out(expr) + "\n");
            }
        }
        else if(prefix.equals("tequ"))                                                                 //解三角函数方程
        {
            String str=s.substring(front,s.length());
            int frist=str.indexOf("(");
            str=str.substring(frist+1,str.length());
            int second=str.lastIndexOf(")");
            str=str.substring(0,second);
            String str2[] = str.split(",");
            for(int i=0;i<str2.length;i++)
            {
                str2[i]=str2[i].trim();
            }
            str2[0] = transFrom_form(str2[0]);
            str2[1] = transFrom_form(str2[1]);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("algebra").callAttr("equ_set_trig", str2[0],str2[1],str2[2],str2[3]);
            String expr = obj1.toJava(String.class);
            tt.append(transFrom_form_out(expr) + "\n");
        }
        else if(prefix.equals("simp"))                                                                 //化简多项式
        {
            String str=s.substring(front,s.length());
            int frist=str.indexOf("(");
            str=str.substring(frist+1,str.length());
            int second=str.lastIndexOf(")");
            str=str.substring(0,second);
            String str2=str.trim();
            str2=transFrom_form(str2);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("algebra").callAttr("simp", str2);
            String expr = obj1.toJava(String.class);
            tt.append(transFrom_form_out(expr) + "\n");
        }
        else if(prefix.equals("tsimp"))                                                                 //化解三角函数多项式
        {
            String str=s.substring(front,s.length());
            int frist=str.indexOf("(");
            str=str.substring(frist+1,str.length());
            int second=str.lastIndexOf(")");
            str=str.substring(0,second);
            String str2=str.trim();
            str2=transFrom_form(str2);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("algebra").callAttr("simp_trig", str2);
            String expr = obj1.toJava(String.class);
            tt.append(transFrom_form_out(expr) + "\n");
        }
        else if(prefix.equals("summ"))                                                                 //数列累加
        {
            String str=s.substring(front,s.length());
            int frist=str.indexOf("(");
            str=str.substring(frist+1,str.length());
            int second=str.lastIndexOf(")");
            str=str.substring(0,second);
            String str2[] = str.split(",");
            for(int i=0;i<str2.length;i++)
            {
                str2[i]=str2[i].trim();
            }
            str2[0] = transFrom_form(str2[0]);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("algebra").callAttr("summ", str2[0],str2[1],str2[2],str2[3]);
            String expr = obj1.toJava(String.class);
            tt.append(transFrom_form_out(expr) + "\n");
        }
        else {
            calculation(s);
            tt.append("\n");                                                       //最后的输出值
            tt.append("值为:" + result.toString());
        }

    }

    static String cal(String s) throws Exception                               //计算不输出结果（括号内使用）(此处更新括号里的括号)
    {
        try {
            char symbol = '0';
            int symbolnum = 0;
            boolean ji_frist = false;                           //*与。的先后顺序
            int front = 100000;
            int last = 100000;
            boolean daL = false;
            boolean daR = false;
            boolean flagd = true;
            for (int i = 0; i < s.length(); i++)                          //判断大括号
            {
                if (s.charAt(i) == '{')
                    daL = true;
                if (s.charAt(i) == '}')
                    daR = true;
                if (s.charAt(i) == '[' || s.charAt(i) == ']')
                    flagd = false;
            }
            if (!daL && daR || daL && !daR) {
                Toast.makeText(context, "提示：大括号错误", Toast.LENGTH_SHORT).show();
            }
      /*  for (int i = 0; i < s.length(); i++)                        //判断括号
        {
            if (s.charAt(i) == '[')
                front = i;
            if (last == 100000) {
                if (s.charAt(i) == ']')
                    last = i;
            }
        }*/
            if (daL && daR && flagd) {
                for (int i = 0; i < s.length(); i++)                        //判断括号
                {
                    if (s.charAt(i) == '{')
                        front = i;
                    if (last == 100000) {
                        if (s.charAt(i) == '}')
                            last = i;
                    }
                }
            }
            if (front != 100000 && last != 100000)                                   //有括号
            {
                for (int i = 0; i < s.length(); i++)                                 //判断括号及算数优先级
                {
                    if (s.charAt(i) == '+' || (s.charAt(i) == '-' && (s.charAt(i - 1) == ')' || s.charAt(i - 1) == ']') || s.charAt(i - 1) == '}') || s.charAt(i) == '*' || s.charAt(i) == '·')
                        num++;
                    if (flagd == false) {
                        if (s.charAt(i) == '[')
                            front = i;
                        if (last == 100000) {
                            if (s.charAt(i) == ']')
                                last = i;
                        }
                    }
                    if (daL && daR && flagd) {
                        if (s.charAt(i) == '{')
                            front = i;
                        if (last == 100000) {
                            if (s.charAt(i) == '}')
                                last = i;
                        }
                    }
                    if (s.charAt(i) == '*' && ji_frist == false) {
                        symbol = '*';
                        symbolnum = i;
                        ji_frist = true;
                    } else if (s.charAt(i) == '·' && ji_frist == false) {
                        symbol = '·';
                        symbolnum = i;
                        ji_frist = true;
                    } else if (symbol == '0' && s.charAt(i) == '+') {
                        symbol = '+';
                        symbolnum = i;
                    } else if (symbol == '0' &&(s.charAt(i) == '-' && (s.charAt(i - 1) == ')' || s.charAt(i - 1) == ']' || s.charAt(i - 1) == '}'))) {
                        symbol = '-';
                        symbolnum = i;
                    }
                }
                if (num == 0)                                                  //一个式子时
                {
                    matrix one = transFrom(s);
                    String t = one.showstring();
                    System.out.println(t);
                    link.clear();
                    format(t);
                    tt.append("\n");
                    result = one.getresult();                                                             //获得此时的矩阵值
                    return s;
                }
                if (front != 100000 && last != 100000)                               //有括号时
                {
                    int flagnum = 0;
                    int number = 0;
                    char flag = '0';
                    for (int i = front; i < last + 1; i++) {
                        if ((s.charAt(i) == '+' || (s.charAt(i) == '-' && (s.charAt(i - 1) == ')' || s.charAt(i - 1) == ']' || s.charAt(i - 1) == '}'))|| s.charAt(i) == '*')) {
                            number++;
                            flagnum = i;
                            flag = s.charAt(i);
                        }
                    }
                    if (number == 0) {
                        String s1 = s.substring(0, front);
                        String s2 = s.substring(front + 1, last);
                        String s3 = s.substring(last + 1, s.length());
                        String all = s1 + s2 + s3;
                        System.out.println(all);
                        link.clear();
                        format(all);
                        tt.append("\n");
                        calculation(all);
                        return all;
                    }
                    if (number == 1)                                                    //二个
                    {
                        matrix one = transFrom(s.substring(front + 1, flagnum));
                        matrix two = transFrom(s.substring(flagnum + 1, last));
                        switch (flag) {
                            case '+':
                                one.plus(two);
                                break;
                            case '-':
                                one.minus(two);
                                break;
                            case '*':
                                one = matrix.mult(one, two);
                                break;
                            case '·': {
                                if (one.showrow() != 1 && two.showrow() != 1)
                                    one = matrix.mult(one, two);
                                else
                                    one = matrix.dot(one, two);
                                break;
                            }
                            default:
                                Toast.makeText(context, "错误:程序错误", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        String s1 = s.substring(0, front);
                        String s2 = one.showstring();
                        String s3 = s.substring(last + 1, s.length());
                        String all = s1 + s2 + s3;
                        System.out.println(all);
                        link.clear();
                        format(all);
                        tt.append("\n");
                        calculation(all);
                        return all;
                    }
                    if (number > 1)                                                 //多个
                    {
                        String s1 = s.substring(0, front + 1);
                        String s2 = s.substring(front + 1, last);
                        String s3 = s.substring(last, s.length());
                        String center = cal(s2);
                        System.out.println(s1 + center + s3);
                        link.clear();
                        format(s1 + center + s3);
                        tt.append("\n");
                        calculation(s1 + center + s3);
                        return s1 + center + s3;
                    }

                }
            } else if ((front != 100000 && last == 100000) || (front == 100000 && last != 100000))    //错误
            {
                Toast.makeText(context, "错误:中括号不对称错误", Toast.LENGTH_SHORT).show();
                return "";
            } else                                                                      //无括号
            {
                symbol = '0';
                symbolnum = 0;
                for (int i = 0; i < s.length(); i++)                          //判断算数优先级
                {
                    if (s.charAt(i) == '*' && ji_frist == false) {
                        symbol = '*';
                        symbolnum = i;
                        ji_frist = true;
                    } else if (s.charAt(i) == '·' && ji_frist == false) {
                        symbol = '·';
                        symbolnum = i;
                        ji_frist = true;
                    } else if (symbol == '0' && s.charAt(i) == '+') {
                        symbol = '+';
                        symbolnum = i;
                    } else if (symbol == '0' &&(s.charAt(i) == '-' && (s.charAt(i - 1) == ')' || s.charAt(i - 1) == ']' || s.charAt(i - 1) == '}'))) {
                        symbol = '-';
                        symbolnum = i;
                    }
                }
                if (symbol == '*') {
                    int f = 0;
                    int l = 0;
                    for (int i = symbolnum; i > 0; i--) {
                        if (s.charAt(i) == '(' && s.charAt(i - 1) == '(') {
                            f = i - 1;
                            if (i - 1 != 0) {
                                if (s.charAt(i - 2) == 't')
                                    f--;
                                if (s.charAt(i - 2) >= '0' && s.charAt(i - 2) <= '9')
                                    for (int n = f - 1; n >= 0; n--) {
                                        if (s.charAt(n) >= '0' && s.charAt(n) <= '9' || s.charAt(n) == '.' || s.charAt(n) == '/')
                                            f--;
                                        else
                                            break;
                                    }
                            }
                            break;
                        }
                    }
                    for (int i = symbolnum; i < s.length(); i++) {
                        if (s.charAt(i) == ')' && s.charAt(i + 1) == ')') {
                            l = i + 1;
                            break;
                        }
                    }
                    matrix one = transFrom(s.substring(f, symbolnum));
                    matrix two = transFrom(s.substring(symbolnum + 1, l + 1));
                    one = matrix.mult(one, two);
                    String s1 = s.substring(0, f);
                    String s2 = one.showstring();
                    String s3 = s.substring(l + 1, s.length());
                    cal(s1 + s2 + s3);
                    //   result = one.getresult();                                                             //获得此时的矩阵值
                    return s1 + s2 + s3;
                }
                if (symbol == '·') {
                    int f = 0;
                    int l = 0;
                    for (int i = symbolnum; i > 0; i--) {                                                 //一直向前找矩阵的前置符号
                        if (s.charAt(i) == '(' && s.charAt(i - 1) == '(') {
                            f = i - 1;
                            if (i - 1 != 0)                                                                //前置还有可能有别的数
                            {                                                                              //拓展前置在此加入
                                if (s.charAt(i - 2) == 't')
                                    f--;
                                if (s.charAt(i - 2) >= '0' && s.charAt(i - 2) <= '9')
                                    for (int n = f - 1; n >= 0; n--) {
                                        if (s.charAt(n) >= '0' && s.charAt(n) <= '9' || s.charAt(n) == '.' || s.charAt(n) == '/')
                                            f--;
                                        else
                                            break;
                                    }
                            }
                            break;
                        }
                    }
                    for (int i = symbolnum; i < s.length(); i++) {
                        if (s.charAt(i) == ')' && s.charAt(i + 1) == ')') {
                            l = i + 1;
                            break;
                        }
                    }
                    matrix one = transFrom(s.substring(f, symbolnum));
                    matrix two = transFrom(s.substring(symbolnum + 1, l + 1));
                    if (one.showrow() != 1 && two.showrow() != 1)
                        one = matrix.mult(one, two);
                    else
                        one = matrix.dot(one, two);
                    String s1 = s.substring(0, f);
                    String s2 = one.showstring();
                    String s3 = s.substring(l + 1, s.length());
                    System.out.println(s1 + s2 + s3);
                    link.clear();
                    format(s1 + s2 + s3);
                    tt.append("\n");
                    //        result=one.getresult();                                                             //获得此时的矩阵值
                    calculation(s1 + s2 + s3);
                    return s1 + s2 + s3;
                }
                if (symbol == '-') {
                    int f = 0;
                    int l = 0;
                    for (int i = symbolnum; i > 0; i--) {
                        if (s.charAt(i) == '(' && s.charAt(i - 1) == '(') {
                            f = i - 1;
                            if (i - 1 != 0) {
                                if (s.charAt(i - 2) == 't')
                                    f--;
                                if (s.charAt(i - 2) >= '0' && s.charAt(i - 2) <= '9')
                                    for (int n = f - 1; n >= 0; n--) {
                                        if (s.charAt(n) >= '0' && s.charAt(n) <= '9' || s.charAt(n) == '.' || s.charAt(n) == '/')
                                            f--;
                                        else
                                            break;
                                    }
                            }
                            break;
                        }
                    }
                    for (int i = symbolnum; i < s.length(); i++) {
                        if (s.charAt(i) == ')' && s.charAt(i + 1) == ')') {
                            l = i + 1;
                            break;
                        }
                    }
                    matrix one = transFrom(s.substring(f, symbolnum));
                    matrix two = transFrom(s.substring(symbolnum + 1, l + 1));
                    one.minus(two);
                    String s1 = s.substring(0, f);
                    String s2 = one.showstring();
                    String s3 = s.substring(l + 1, s.length());
                    cal(s1 + s2 + s3);
                    //    result = one.getresult();                                                             //获得此时的矩阵值
                    return s1 + s2 + s3;
                }
                if (symbol == '+') {
                    int f = 0;
                    int l = 0;
                    for (int i = symbolnum; i > 0; i--) {
                        if (s.charAt(i) == '(' && s.charAt(i - 1) == '(') {
                            f = i - 1;
                            if (i - 1 != 0) {
                                if (s.charAt(i - 2) == 't')
                                    f--;
                                if (s.charAt(i - 2) >= '0' && s.charAt(i - 2) <= '9')
                                    for (int n = f - 1; n >= 0; n--) {
                                        if (s.charAt(n) >= '0' && s.charAt(n) <= '9' || s.charAt(n) == '.' || s.charAt(n) == '/')
                                            f--;
                                        else
                                            break;
                                    }
                            }
                            break;
                        }
                    }
                    for (int i = symbolnum; i < s.length(); i++) {
                        if (s.charAt(i) == ')' && s.charAt(i + 1) == ')') {
                            l = i + 1;
                            break;
                        }
                    }
                    matrix one = transFrom(s.substring(f, symbolnum));
                    matrix two = transFrom(s.substring(symbolnum + 1, l) + ")");
                    one.plus(two);
                    String s1 = s.substring(0, f);
                    String s2 = one.showstring();
                    String s3 = s.substring(l + 1, s.length());
                    cal(s1 + s2 + s3);
                    //           result = one.getresult();                                                             //获得此时的矩阵值
                    return s1 + s2 + s3;
                }
            }
        } catch (Exception e) {
            tt.setText("");
            tt.append("请输入正确的式子");
        }
        return "";
    }
    static String calculation(String s)                       //计算且输出
    {
        try {
            int front = 100000;
            int last = 100000;
            char symbol = '0';
            boolean ji_frist = false;                           //*与。的先后顺序
            int symbolnum = 0;
            boolean daL = false;
            boolean daR = false;
            boolean flagd = true;
            for (int i = 0; i < s.length(); i++)                          //判断大括号
            {
                if (s.charAt(i) == '{')
                    daL = true;
                if (s.charAt(i) == '}')
                    daR = true;
                if (s.charAt(i) == '[' || s.charAt(i) == ']')
                    flagd = false;
            }
            for (int i = 0; i < s.length(); i++)                                 //判断括号及算数优先级
            {
                if (s.charAt(i) == '+' || (s.charAt(i) == '-' && (s.charAt(i - 1) == ')' || s.charAt(i - 1) == ']' || s.charAt(i - 1) == '}')) || s.charAt(i) == '*' || s.charAt(i) == '·')      //此处添加 运算符
                    num++;
                if (flagd == false) {
                    if (s.charAt(i) == '[')
                        front = i;
                    if (last == 100000) {
                        if (s.charAt(i) == ']')
                            last = i;
                    }
                }
                if (daL && daR && flagd) {
                    if (s.charAt(i) == '{')
                        front = i;
                    if (last == 100000) {
                        if (s.charAt(i) == '}')
                            last = i;
                    }
                }
                if (s.charAt(i) == '*' && ji_frist == false) {
                    symbol = '*';
                    symbolnum = i;
                    ji_frist = true;
                } else if (s.charAt(i) == '·' && ji_frist == false) {
                    symbol = '·';
                    symbolnum = i;
                    ji_frist = true;
                } else if (symbol == '0' && s.charAt(i) == '+') {
                    symbol = '+';
                    symbolnum = i;
                } else if (symbol == '0' && (s.charAt(i) == '-' && (s.charAt(i - 1) == ')' || s.charAt(i - 1) == ']' || s.charAt(i - 1) == '}'))) {
                    symbol = '-';
                    symbolnum = i;
                }
            }
            if (num == 0)                                                  //一个式子时
            {
                matrix one = transFrom(s);
                String t = one.showstring();
                System.out.println(t);
                link.clear();
                format(t);
                tt.append("\n");
                result=one.getresult();
                return s;
            }
            if (front != 100000 && last != 100000)                               //有括号时
            {
                int flagnum = 0;
                int number = 0;
                char flag = '0';
                for (int i = front; i < last + 1; i++) {
                    if ((s.charAt(i) == '+' ||(s.charAt(i) == '-' && (s.charAt(i - 1) == ')' || s.charAt(i - 1) == ']' || s.charAt(i - 1) == '}')) || s.charAt(i) == '*')) {
                        number++;
                        flagnum = i;
                        flag = s.charAt(i);
                    }
                }
                if (number == 0) {
                    String s1 = s.substring(0, front);
                    String s2 = s.substring(front + 1, last);
                    String s3 = s.substring(last + 1, s.length());
                    String all = s1 + s2 + s3;
                    System.out.println(all);
                    link.clear();
                    format(all);
                    tt.append("\n");
                    calculation(all);
                    return all;
                }
                if (number == 1)                                                    //二个
                {
                    matrix one = transFrom(s.substring(front + 1, flagnum));
                    matrix two = transFrom(s.substring(flagnum + 1, last));
                    switch (flag) {
                        case '+':
                            one.plus(two);
                            break;
                        case '-':
                            one.minus(two);
                            break;
                        case '*':
                            one = matrix.mult(one, two);
                            break;
                        case '·': {
                            if (one.showrow() != 1 && two.showrow() != 1)
                                one = matrix.mult(one, two);
                            else
                                one = matrix.dot(one, two);
                            break;
                        }
                        default:
                            Toast.makeText(context, "错误:程序错误", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    String s1 = s.substring(0, front);
                    String s2 = one.showstring();
                    String s3 = s.substring(last + 1, s.length());
                    String all = s1 + s2 + s3;
                    System.out.println(all);
                    link.clear();
                    format(all);
                    tt.append("\n");
                    calculation(all);
                    return all;
                }
                if (number > 1)                                                 //多个
                {
                    String s1 = s.substring(0, front + 1);
                    String s2 = s.substring(front + 1, last);
                    String s3 = s.substring(last, s.length());
                    String center = cal(s2);
                    System.out.println(s1 + center + s3);
                    link.clear();
                    format(s1 + center + s3);
                    tt.append("\n");
                    calculation(s1 + center + s3);
                    return s1 + center + s3;
                }

            }
            if ((front != 100000 && last == 100000) || (front == 100000 && last != 100000)) {
                Toast.makeText(context, "错误:中括号不对称错误", Toast.LENGTH_SHORT).show();
                return "";
            }
            if (front == 100000 && last == 100000 && symbol != '0')                         //无括号时
            {

                if (symbol == '*') {
                    int f = 0;
                    int l = 0;
                    for (int i = symbolnum; i > 0; i--) {                                                 //一直向前找矩阵的前置符号
                        if (s.charAt(i) == '(' && s.charAt(i - 1) == '(') {
                            f = i - 1;
                            if (i - 1 != 0)                                                                //前置还有可能有别的数
                            {                                                                              //拓展前置在此加入
                                if (s.charAt(i - 2) == 't')
                                    f--;
                                if (s.charAt(i - 2) >= '0' && s.charAt(i - 2) <= '9')
                                    for (int n = f - 1; n >= 0; n--) {
                                        if (s.charAt(n) >= '0' && s.charAt(n) <= '9' || s.charAt(n) == '.' || s.charAt(n) == '/')
                                            f--;
                                        else
                                            break;
                                    }
                            }
                            break;
                        }
                    }
                    for (int i = symbolnum; i < s.length(); i++) {
                        if (s.charAt(i) == ')' && s.charAt(i + 1) == ')') {
                            l = i + 1;
                            break;
                        }
                    }
                    matrix one = transFrom(s.substring(f, symbolnum));
                    matrix two = transFrom(s.substring(symbolnum + 1, l + 1));
                    one = matrix.mult(one, two);
                    String s1 = s.substring(0, f);
                    String s2 = one.showstring();
                    String s3 = s.substring(l + 1, s.length());
                    System.out.println(s1 + s2 + s3);
                    link.clear();
                    format(s1 + s2 + s3);
                    tt.append("\n");
                    result=one.getresult();                                                             //获得此时的矩阵值
                    calculation(s1 + s2 + s3);
                    return s1 + s2 + s3;
                }
                if (symbol == '·') {
                    int f = 0;
                    int l = 0;
                    for (int i = symbolnum; i > 0; i--) {                                                 //一直向前找矩阵的前置符号
                        if (s.charAt(i) == '(' && s.charAt(i - 1) == '(') {
                            f = i - 1;
                            if (i - 1 != 0)                                                                //前置还有可能有别的数
                            {                                                                              //拓展前置在此加入
                                if (s.charAt(i - 2) == 't')
                                    f--;
                                if (s.charAt(i - 2) >= '0' && s.charAt(i - 2) <= '9')
                                    for (int n = f - 1; n >= 0; n--) {
                                        if (s.charAt(n) >= '0' && s.charAt(n) <= '9' || s.charAt(n) == '.' || s.charAt(n) == '/')
                                            f--;
                                        else
                                            break;
                                    }
                            }
                            break;
                        }
                    }
                    for (int i = symbolnum; i < s.length(); i++) {
                        if (s.charAt(i) == ')' && s.charAt(i + 1) == ')') {
                            l = i + 1;
                            break;
                        }
                    }
                    matrix one = transFrom(s.substring(f, symbolnum));
                    matrix two = transFrom(s.substring(symbolnum + 1, l + 1));
                    if (one.showrow() != 1 && two.showrow() != 1)
                        one = matrix.mult(one, two);
                    else
                        one = matrix.dot(one, two);
                    String s1 = s.substring(0, f);
                    String s2 = one.showstring();
                    String s3 = s.substring(l + 1, s.length());
                    System.out.println(s1 + s2 + s3);
                    link.clear();
                    format(s1 + s2 + s3);
                    tt.append("\n");
                    result=one.getresult();                                                             //获得此时的矩阵值
                    calculation(s1 + s2 + s3);
                    return s1 + s2 + s3;
                }
                if (symbol == '-') {
                    int f = 0;
                    int l = 0;
                    for (int i = symbolnum; i > 0; i--) {
                        if (s.charAt(i) == '(' && s.charAt(i - 1) == '(') {
                            f = i - 1;
                            if (i - 1 != 0) {
                                if (s.charAt(i - 2) == 't')
                                    f--;
                                if (s.charAt(i - 2) >= '0' && s.charAt(i - 2) <= '9')
                                    for (int n = f - 1; n >= 0; n--) {
                                        if (s.charAt(n) >= '0' && s.charAt(n) <= '9' || s.charAt(n) == '.' || s.charAt(n) == '/')
                                            f--;
                                        else
                                            break;
                                    }
                            }
                            break;
                        }
                    }
                    for (int i = symbolnum; i < s.length(); i++) {
                        if (s.charAt(i) == ')' && s.charAt(i + 1) == ')') {
                            l = i + 1;
                            break;
                        }
                    }
                    matrix one = transFrom(s.substring(f, symbolnum));
                    matrix two = transFrom(s.substring(symbolnum + 1, l + 1));
                    one.minus(two);
                    String s1 = s.substring(0, f);
                    String s2 = one.showstring();
                    String s3 = s.substring(l + 1, s.length());
                    System.out.println(s1 + s2 + s3);
                    link.clear();
                    format(s1 + s2 + s3);
                    tt.append("\n");
                    result=one.getresult();                                                             //获得此时的矩阵值
                    calculation(s1 + s2 + s3);
                    return s1 + s2 + s3;
                }
                if (symbol == '+') {
                    int f = 0;
                    int l = 0;
                    for (int i = symbolnum; i > 0; i--) {
                        if (s.charAt(i) == '(' && s.charAt(i - 1) == '(') {
                            f = i - 1;
                            if (i - 1 != 0) {
                                if (s.charAt(i - 2) == 't')
                                    f--;
                                if (s.charAt(i - 2) >= '0' && s.charAt(i - 2) <= '9')
                                    for (int n = f - 1; n >= 0; n--) {
                                        if (s.charAt(n) >= '0' && s.charAt(n) <= '9' || s.charAt(n) == '.' || s.charAt(n) == '/')
                                            f--;
                                        else
                                            break;
                                    }
                            }
                            break;
                        }
                    }
                    for (int i = symbolnum; i < s.length(); i++) {
                        if (s.charAt(i) == ')' && s.charAt(i + 1) == ')') {
                            l = i + 1;
                            break;
                        }
                    }
                    matrix one = transFrom(s.substring(f, symbolnum));
                    matrix two = transFrom(s.substring(symbolnum + 1, l) + ")");
                    one.plus(two);
                    String s1 = s.substring(0, f);
                    String s2 = one.showstring();
                    String s3 = s.substring(l + 1, s.length());
                    System.out.println(s1 + s2 + s3);
                    link.clear();
                    format(s1 + s2 + s3);
                    tt.append("\n");
                    result=one.getresult();                                                             //获得此时的矩阵值
                    calculation(s1 + s2 + s3);
                    return s1 + s2 + s3;
                }
            }
        } catch (Exception e) {
            tt.setText("");
            tt.append("请输入正确的式子");
        }
        return "";
    }
    static void format(String s)                                            //格式化输出
    {
        int row = 0;
        int col = 0;
        int focusr = 0;
        int focusc = 0;
        int fuhao[];
        int num = 0;
        String show[][];
        String tran_s = transstring(s);
        String tran_r = "";
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) == '+' || (s.charAt(i) == '-' && (s.charAt(i - 1) == ')' || s.charAt(i - 1) == ']')) || s.charAt(i) == '*'))
                num++;
        }
        fuhao = new int[num];
        num = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) == '+' || (s.charAt(i) == '-' && (s.charAt(i - 1) == ')' || s.charAt(i - 1) == ']')) || s.charAt(i) == '*')) {
                fuhao[num] = i;
                num++;
                col++;
            }
            if (s.charAt(i) == '[' || s.charAt(i) == ']')
                col++;
        }
        num--;
        if (num == -1) {
            tran_r = " ";
            matrix one = link.deQueue();
            show = new String[one.row][one.col + 1];
            for (int i = 0; i < one.row; i++)
                for (int j = 0; j < one.col; j++) {
                    show[i][j] = "   ";
                }
            for (int j = 0; j < one.row; j++)
                for (int k = 0; k < one.col; k++)
                    show[j][k] = String.valueOf(one.mat[j][k]);
            for (int i = 0; i < one.row; i++) {
                for (int j = 0; j < one.col; j++) {

                    tran_r += show[i][j] + " ";
                }
                tt.append(tran_r + "\n");
                tran_r = " ";
            }
        } else {
            while (num >= 0) {
                int maxr = showmaxrow(s, fuhao[num]);
                if (maxr > row)
                    row = maxr;
                int maxc = showmaxcol(s, fuhao[num]);
                col += maxc;
                if (num == 0)
                    col += showmaxcol(s, fuhao[0]);
                num--;
            }
            show = new String[row][col + 1];
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col + 1; j++) {
                    if (j == col)
                        show[i][j] = "\n";
                }
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++) {
                    show[i][j] = "  ";
                }
            for (int i = 0; i < tran_s.length(); i++) {

                if (tran_s.charAt(i) == 'm') {
                    if (focusr == 0 && focusc == 0) {
                        matrix one = link.deQueue();
                        for (int j = 0; j < one.row; j++)
                            for (int k = 0; k < one.col; k++)
                                show[j][k + focusc] = String.valueOf(one.mat[j][k]);
                        focusc += one.col - 1;
                    } else {
                        focusr = 0;
                        focusc++;
                        matrix one = link.deQueue();
                        for (int j = 0; j < one.row; j++)
                            for (int k = 0; k < one.col; k++)
                                show[j][k + focusc] = String.valueOf(one.mat[j][k]);
                        focusc += one.col - 1;
                    }
                } else {
                    if (focusr == 0 && focusc == 0) {
                        focusr = row / 2;
                        show[focusr][focusc] = String.valueOf(tran_s.charAt(i));
                    } else {
                        focusr = row / 2;
                        focusc++;
                        show[focusr][focusc] = String.valueOf(tran_s.charAt(i));
                    }
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col + 1; j++) {

                    tran_r += show[i][j] + " ";
                }
                tt.append(tran_r);
                tran_r = "";
            }
        }
    }
    static int showmaxrow(String s, int symbolnum)                          //格式化输出式子最大行
    {
        int f = 0;
        int l = 0;
        for (int i = symbolnum; i > 0; i--) {
            if (s.charAt(i) == '(' && s.charAt(i - 1) == '(') {
                f = i - 1;
                break;
            }
        }
        for (int i = symbolnum; i < s.length(); i++) {
            if (s.charAt(i) == ')' && s.charAt(i + 1) == ')') {
                l = i + 1;
                break;
            }
        }
        matrix one = transFrom(s.substring(f, symbolnum));
        matrix two = transFrom(s.substring(symbolnum + 1, l + 1));
        return (one.showrow() > two.showrow()) ? one.showrow() : two.showrow();
    }
    static int showmaxcol(String s, int symbolnum)                          //格式化输出式子最大列
    {
        int f = 0;
        int l = 0;
        for (int i = symbolnum; i > 0; i--) {
            if (s.charAt(i) == '(' && s.charAt(i - 1) == '(') {
                f = i - 1;
                break;
            }
        }
        for (int i = symbolnum; i < s.length(); i++) {
            if (s.charAt(i) == ')' && s.charAt(i + 1) == ')') {
                l = i + 1;
                break;
            }
        }
        matrix one = transFrom(s.substring(f, symbolnum));
        matrix two = transFrom(s.substring(symbolnum + 1, l + 1));
        return (one.showcol() > two.showcol()) ? one.showcol() : two.showcol();
    }
    static String transstring(String s)                                    //式子转化
    {
        s=s.trim();
        int f = 1000;
        int l = 1000;
        String out = null;
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' && s.charAt(i + 1) == '(' && f == 1000)
                f = i;
            if (s.charAt(i) == ')' && s.charAt(i - 1) == ')' && l == 1000)
                l = i;
        }
        if (f == 0 && l == s.length() - 1) {

            matrix one = transFrom(s);
            link.enQueue(one);
            out = "m";

        } else if (f != 0 && l == s.length() - 1) {

            if (s.charAt(f - 1) == 't')
                f--;
            else if (s.charAt(f - 1) >= '0' && s.charAt(f - 1) <= '9')
                for (int n = f - 1; n >= 0; n--) {
                    if (s.charAt(n) >= '0' && s.charAt(n) <= '9' || s.charAt(n) == '.' || s.charAt(n) == '/')
                        f--;
                    else
                        break;
                }
            if (f == 0) {
                matrix one = transFrom(s.substring(f, s.length()));
                link.enQueue(one);
                out = "m";
            } else {
                matrix one = transFrom(s.substring(f, s.length()));
                link.enQueue(one);
                String s1 = s.substring(0, f);
                out = s1 + "m";
            }
        } else if (f != 0 && l != s.length() - 1) {
            if (s.charAt(f - 1) == 't')
                f--;
            else if (s.charAt(f - 1) >= '0' && s.charAt(f - 1) <= '9')
                for (int n = f - 1; n >= 0; n--) {
                    if (s.charAt(n) >= '0' && s.charAt(n) <= '9' || s.charAt(n) == '.' || s.charAt(n) == '/')
                        f--;
                    else
                        break;
                }
            if (f == 0) {
                matrix one = transFrom(s.substring(0, l + 1));
                link.enQueue(one);
                String s1 = s.substring(l + 1, s.length());
                out = "m" + s1;
            } else {
                matrix one = transFrom(s.substring(f, l + 1));
                link.enQueue(one);
                String s1 = s.substring(0, f);
                String s2 = s.substring(l + 1, s.length());
                out = s1 + "m" + s2;

            }
        } else if (f == 0 && l != s.length() - 1) {
            matrix one = transFrom(s.substring(0, l + 1));
            link.enQueue(one);
            String s1 = s.substring(l + 1, s.length());
            out = "m" + s1;

        }
        for (int i = 0; i < out.length(); i++) {
            if (out.charAt(i) == '(' || out.charAt(i) == ')') {
                flag = true;
                break;
            }
        }
        if (flag == false)
            return out;
        else
            return transstring(out);


    }
    static String com(String s)                                            //组合
    {
        BigInteger n;
        BigInteger m;
        String mis = "输入有误";
        if (s == null)
            return mis;
        int l = s.indexOf("(");
        int mid = s.indexOf(",");
        int r = s.indexOf(")");
        n = new BigInteger(s.substring(l + 1, mid).trim());
        m = new BigInteger(s.substring(mid + 1, r).trim());
        if (m.multiply(new BigInteger("2")).compareTo(n) > 0)
            m = n.subtract(m);
        BigInteger i = BigInteger.ZERO;
        BigInteger ans = BigInteger.ONE;
        while (i.compareTo(m) < 0) {
            ans = ans.multiply(n.subtract(i)).divide(i = i.add(BigInteger.ONE));
        }
        return ans.toString();
    }
    public static String nop(String s)                                     //排列
    {
        BigInteger n;
        BigInteger m;

        String mis = "输入有误";
        if (s == null)
            return mis;
        int l = s.indexOf("(");
        int mid = s.indexOf(",");
        int r = s.indexOf(")");
        n = new BigInteger(s.substring(l + 1, mid).trim());
        m = new BigInteger(s.substring(mid + 1, r).trim());
        BigInteger i = BigInteger.ZERO;
        BigInteger ans = BigInteger.ONE;
        while (i.compareTo(m) < 0) {
            ans = ans.multiply(n.subtract(i));
            i = i.add(BigInteger.ONE);
        }
        return ans.toString();
    }
    public static String model(String s)                                    //模
    {
        String result;
        String num[];
        real r[];
        real r1;
        s = s.replace("(", " ");
        s = s.replace(")", " ");
        s = s.trim();
        num = s.split(",");
        r = new real[num.length];
        for (int i = 0; i < num.length; i++) {
            r[i] = new real(num[i]);
        }
        for (int i = 0; i < r.length; i++) {
            if (i != 0)
                r[0] = r[0].add(r[i].mul(r[i]));
            else
                r[0] = r[0].mul(r[0]);
        }
        String ss = r[0].toString();
        if (ss.indexOf("/") == -1) {
            real r2 = new real("√" + Double.parseDouble(ss));
            result = r2.toString();
        } else {
            String s1 = "√" + Double.parseDouble(ss.substring(0, ss.indexOf("/"))) * Double.parseDouble(ss.substring(ss.indexOf("/") + 1, ss.length()));
            String s2 = String.valueOf(Double.parseDouble(ss.substring(ss.indexOf("/") + 1, ss.length())));
            System.out.println(s1);
            System.out.println(s2);
            real r3 = new real(s1);
            real r4 = new real(s2);
            r3 = r3.div(r4);
            result = r3.toString();
        }
        return result;
    }
    public static String cos(String string)                                   //coss
    {
        try {
            StringBuilder sb = new StringBuilder();
            ArrayList<realbase> lis = new ArrayList<realbase>();
            string = string.replace("(", " ");
            string = string.replace(")", " ");
            String[] str = string.split(",");
            realbase[] nums = new realbase[6];
            if (str.length == 6) {
                for (int i = 0; i < 6; i++) {
                    nums[i] = new realbase(str[i]);
                    if (nums[i] == null) {
                        return "Error";
                    }
                }
            } else if (str.length == 4) {
                nums[0] = new realbase(str[0]);
                nums[1] = new realbase(str[1]);
                nums[2] = new realbase("0");
                nums[3] = new realbase(str[2]);
                nums[4] = new realbase(str[3]);
                nums[5] = new realbase("0");

            } else
                return "Error";
            lis = Innerproduct(nums);
            realbase tmp = molds(nums);
            if (tmp.toString().equals("+0")) {
                return "Error";
            }
            ArrayList<realbase> list = new ArrayList<realbase>();
            for (realbase t : lis) {
                realbase n = t.div(tmp);
                boolean flag = true;
                for (int i = 0; i < list.size(); i++) {
                    if (n.equals(list.get(i))) {
                        realbase tmpn = list.remove(i).add(n);
                        list.add(tmpn);
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    list.add(n);
            }
            for (realbase n : list)
                sb.append(n.toString());
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
        } catch (Exception e) {
            return "Error";
        }
    }
    static realbase molds(realbase[] number)                                                  //cos model
    {
        realbase tmp1 = Molds(number[0], number[1], number[2]);
        realbase tmp2 = Molds(number[3], number[4], number[5]);
        realbase tmp = new realbase();
        tmp1 = tmp1.mul(tmp2);
        tmp.Denominator = tmp1.Denominator;
        tmp.rootMol = tmp1.molecule * tmp1.Denominator;
        tmp.molecule = tmp.rootDen = 1;
        tmp.optimization();
        return tmp;
    }
    static realbase Molds(realbase a, realbase b, realbase c)                                 //cos model
    {
        return a.mul(a).add(b.mul(b)).add(c.mul(c));
    }
    static ArrayList<realbase> Innerproduct(realbase[] number)                                  //cos list
    {
        ArrayList<realbase> lis = new ArrayList<realbase>();
        realbase tmp = number[0].mul(number[3]);
        lis.add(tmp);
        tmp = number[1].mul(number[4]);
        if (lis.get(0).equals(tmp)) {
            realbase t = lis.get(0);
            t = t.add(tmp);
            lis.remove(0);
            lis.add(t);
        } else {
            lis.add(tmp);
        }
        tmp = number[2].mul(number[5]);
        if (lis.get(0).equals(tmp)) {
            realbase t = lis.get(0);
            t = t.add(tmp);
            lis.remove(0);
            lis.add(t);
        } else if (lis.size() > 1 && lis.get(1).equals(tmp)) {
            realbase t = lis.get(1);
            t = t.add(tmp);
            lis.remove(1);
            lis.add(t);
        } else
            lis.add(tmp);
        return lis;
    }
    public static int angle(String s)
    {                                                         //余弦值求角
        double result;
        if(s.indexOf("/")!=-1)
        {
            String f_s=s.substring(0,s.indexOf("/"));
            String l_s=s.substring(s.indexOf("/")+1,s.length());
            double r_f;
            double r_l;
            if(f_s.indexOf("√")!=-1)
            {
                if(f_s.indexOf("-")==-1)
                {
                    if(f_s.indexOf("√")-1>=0)
                    {
                        double front=Double.parseDouble(f_s.substring(0,f_s.indexOf("√")));
                        double last=Double.parseDouble(f_s.substring(f_s.indexOf("√")+1,f_s.length()));
                        r_f=front*front*Math.sqrt(last);
                    }
                    else
                    {
                        f_s=f_s.replace("√", "");
                        r_f=Math.sqrt(Double.parseDouble(f_s.trim()));
                    }
                }
                else
                {
                    if(f_s.indexOf("√")-1>=0&&f_s.charAt(f_s.indexOf("√")-1)!='-')
                    {
                        double front=Double.parseDouble(f_s.substring(1,f_s.indexOf("√")));
                        double last=Double.parseDouble(f_s.substring(f_s.indexOf("√")+1,f_s.length()));
                        r_f=front*front*Math.sqrt(last)*-1;
                    }
                    else
                    {
                        f_s=f_s.replace("√", "");
                        f_s=f_s.replace("-", "");
                        r_f=Math.sqrt(Double.parseDouble(f_s.trim()))*-1;
                    }
                }
            }
            else
            {
                r_f=Double.parseDouble(f_s);
            }
            if(l_s.indexOf("√")!=-1)
            {
                if(l_s.indexOf("-")==-1)
                {
                    if(l_s.indexOf("√")-1>=0)
                    {
                        double front=Double.parseDouble(l_s.substring(0,l_s.indexOf("√")));
                        double last=Double.parseDouble(l_s.substring(l_s.indexOf("√")+1,l_s.length()));
                        r_l=front*front*Math.sqrt(last);
                    }
                    else
                    {
                        l_s=l_s.replace("√", "");
                        r_l=Math.sqrt(Double.parseDouble(l_s.trim()));
                    }
                }
                else
                {
                    if(l_s.indexOf("√")-1>=0&&l_s.charAt(l_s.indexOf("√")-1)!='-')
                    {
                        double front=Double.parseDouble(l_s.substring(1,l_s.indexOf("√")));
                        double last=Double.parseDouble(l_s.substring(l_s.indexOf("√")+1,l_s.length()));
                        r_l=front*front*Math.sqrt(last)*-1;
                    }
                    else
                    {
                        l_s=l_s.replace("√", "");
                        l_s=l_s.replace("-", "");
                        r_l=Math.sqrt(Double.parseDouble(l_s.trim()))*-1;
                    }
                }
            }
            else
            {
                r_l=Double.parseDouble(l_s);
            }
            result=r_f/r_l;
        }
        else if(s.indexOf("√")!=-1)
        {
            if(s.indexOf("-")==-1)
            {
                if(s.indexOf("√")-1>=0)
                {
                    double front=Double.parseDouble(s.substring(0,s.indexOf("√")));
                    double last=Double.parseDouble(s.substring(s.indexOf("√")+1,s.length()));
                    result=front*front*Math.sqrt(last);
                }
                else
                {
                    s=s.replace("√", "");
                    result=Math.sqrt(Double.parseDouble(s.trim()));
                }
            }
            else
            {
                if(s.indexOf("√")-1>=0&&s.charAt(s.indexOf("√")-1)!='-')
                {
                    double front=Double.parseDouble(s.substring(1,s.indexOf("√")));
                    double last=Double.parseDouble(s.substring(s.indexOf("√")+1,s.length()));
                    result=front*front*Math.sqrt(last)*-1;
                }
                else
                {
                    s=s.replace("√", "");
                    s=s.replace("-", "");
                    result=Math.sqrt(Double.parseDouble(s.trim()))*-1;
                }
            }
        }
        else
        {
            result=Double.parseDouble(s);
        }
        return  (int) Math.floor(Math.acos(result)/2/PI*360);
    }
    static String[] dc(String s)                                                            //求方向余弦
    {
        s = s.trim();
        int num = 0;
        String res[] = new String[3];
        for (int i = 0; i < s.length(); i++)       //检测
        {
            num++;
        }
        if (num < 2) {
            tt.append("向量格式出错");
        }
        int front = s.indexOf(",");                      //分解向量
        int last = s.lastIndexOf(",");
        String a = s.substring(s.indexOf("(") + 1, front);
        String b = s.substring(front + 1, last);
        String c = s.substring(last + 1, s.length() - 1);
        real f1 = new real(a.trim());                //赋值
        real f2 = new real(b.trim());
        real f3 = new real(c.trim());
        real model = new real(model(s));             //模
        f1 = f1.div(model);                            //方向余弦
        f2 = f2.div(model);
        f3 = f3.div(model);
        res[0] = f1.toString();
        res[1] = f2.toString();
        res[2] = f3.toString();
        return res;
    }
    static String projection(String s)                                                    //投影
    {
        String a = s.substring(1, s.indexOf(")") + 1);
        String b = s.substring(s.indexOf(")") + 2, s.length() - 1);
        real m = new real(model(a));
        real c = new real(cos(s));
        m = m.mul(c);
        return m.toString();
    }
    static String product(String string)                                                 //向量积
    {
        try {
            StringBuilder sb = new StringBuilder();
            ArrayList<real> lis = new ArrayList<real>();
            string = string.replace("(", " ");
            string = string.replace(")", " ");
            String[] str = string.split(",");
            real[] nums = new real[6];
            if (str.length == 6) {
                for (int i = 0; i < 6; i++) {
                    nums[i] = new real(str[i]);
                    if (nums[i] == null) {
                        return "Error";
                    }
                }
            } else if (str.length == 4) {
                nums[0] = new real(str[0]);
                nums[1] = new real(str[1]);
                nums[2] = new real("0");
                nums[3] = new real(str[2]);
                nums[4] = new real(str[3]);
                nums[5] = new real("0");

            } else
                return "Error";
            ArrayList<real> list = new ArrayList<real>();
            list.add(nums[1].mul(nums[5]).sub(nums[2].mul(nums[4])));
            list.add(nums[2].mul(nums[3]).sub(nums[0].mul(nums[5])));
            list.add(nums[0].mul(nums[4]).sub(nums[2].mul(nums[3])));
            sb.append("(");
            sb.append(list.get(0)).append(",");
            if (!list.get(1).toString().startsWith("-"))
            sb.append(list.get(1)).append(",");
            if (!list.get(2).toString().startsWith("-"))
            sb.append(list.get(2)).append(")");
            string = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return string;
    }
    static  int[] da(String s)                                                           //方向角
    {
        String dc[]=dc(s);
        int angle[]=new int[dc.length];
        for(int i=0;i<dc.length;i++)
        {
            angle[i]=(int)angle(dc[i]);
        }
        return angle;
    }
    static String sd(String s)                                                            //同向单位向量
    {
        String dc[] = dc(s);
        String result = "(";
        for (int i = 0; i < dc.length; i++)
        {
            if(i!=dc.length-1)
                result+=dc[i]+",";
            else
                result+=")";
        }
        return result;
    }
    static String od(String s)                                                            //反向单位向量
    {
        String dc[] = dc(s);
        String result = "(";
        for (int i = 0; i < dc.length; i++)
        {
            real t=new real(dc[i]);
            t=t.mul(new real("-1"));
            dc[i]=t.toString();
            if(i!=dc.length-1)
                result+=dc[i]+",";
            else
                result+=")";
        }
        return result;
    }
    static String mixed(String s)                                                      //混合积
    {
        int flag=0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)==','&&s.charAt(i-1)==')')
                flag=i;
        }
        String s1=s.substring(1, flag);
        String s2=s.substring(flag + 1, s.length());
        String s3=product(s1);
        matrix one=new matrix(s3);
        matrix two=new matrix(s2);
        one=matrix.dot(one,two);
        return one.showstring();
    }
    static String dot(String s)                                                       //数量积
    {
        int flag=0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)==','&&s.charAt(i-1)==')')
                flag=i;
        }
        String s1=s.substring(1,flag);
        String s2=s.substring(flag+1,s.length());
        matrix one=new matrix(s1);
        matrix two=new matrix(s2);
        one=matrix.dot(one,two);
        return one.showstring();
    }
}
