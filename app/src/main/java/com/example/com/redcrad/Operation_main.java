package com.example.com.redcrad;

import android.content.Context;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import java.math.BigInteger;
import java.util.ArrayList;

public class Operation_main {
    final static double PI = 3.141592653589793;
    static TextView out;
    static Context context;
    static void setcontext(Context con)                                     //初始化输出活动界面
    {
        context = con;
    }
    static void setText(TextView view)
    {
        out=view;
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
         if (prefix.equals("diff"))                                                                    //求导
        {
            String str = s.substring(front, s.length());
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ',')
                    sum++;
            }
            int frist = str.indexOf("(");
            str = str.substring(frist + 1, str.length());
            int second = str.lastIndexOf(")");
            str = str.substring(0, second);
            String str2[] = str.split(",");
            for (int i = 0; i < str2.length; i++) {
                str2[i] = str2[i].trim();
            }
            if (sum == 2) {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("deri", str2[0], str2[1], str2[2]);
                String diff = obj1.toJava(String.class);
                out.append(transFrom_form_out(diff) + "\n");
            } else if (sum == 4) {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("deri_two", str2[0], str2[1], str2[2], str2[3], str2[4]);
                String diff = obj1.toJava(String.class);
                out.append(transFrom_form_out(diff) + "\n");
            } else {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("deri_three", str2[0], str2[1], str2[2], str2[3], str2[4], str2[5], str2[6]);
                String diff = obj1.toJava(String.class);
                out.append(transFrom_form_out(diff) + "\n");

            }
        } else if (prefix.equals("inte"))                                                 //定积分
        {
            String str = s.substring(front, s.length());
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ',')
                    sum++;
            }
            int frist = str.indexOf("(");
            str = str.substring(frist + 1, str.length());
            int second = str.lastIndexOf(")");
            str = str.substring(0, second);
            String str2[] = str.split(",");
            for (int i = 0; i < str2.length; i++) {
                str2[i] = str2[i].trim();
            }
            if (sum == 3) {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("inte", str2[0], str2[1], str2[2], str2[3]);
                String expr = obj1.toJava(String.class);
                out.append(transFrom_form_out(expr) + "\n");
            } else if (sum == 6) {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("inte_two", str2[0], str2[1], str2[2], str2[3], str2[4], str2[5], str2[6]);
                String expr = obj1.toJava(String.class);
                out.append(transFrom_form_out(expr) + "\n");
            } else {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("inte_three", str2[0], str2[1], str2[2], str2[3], str2[4], str2[5], str2[6], str2[7], str2[8], str2[9]);
                String expr = obj1.toJava(String.class);
                out.append(transFrom_form_out(expr) + "\n");

            }
        } else if (prefix.equals("nointe"))                                                       //不定积分
        {
            String str = s.substring(front, s.length());
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ',')
                    sum++;
            }
            int frist = str.indexOf("(");
            str = str.substring(frist + 1, str.length());
            int second = str.lastIndexOf(")");
            str = str.substring(0, second);
            str=str.trim();
            String str2[] = str.split(",");
            for (int i = 0; i < str2.length; i++) {
                str2[i] = str2[i].trim();
            }
            if (sum == 1) {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("inte_no", str2[0], str2[1]);
                String expr = obj1.toJava(String.class);
                out.append(transFrom_form_out(expr) + "\n");
            } else if (sum == 2) {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("inte_no_two", str2[0], str2[1], str2[2]);
                String expr = obj1.toJava(String.class);
                out.append(transFrom_form_out(expr) + "\n");
            } else {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("calculus").callAttr("inte_no_three", str2[0], str2[1], str2[2], str2[3], str2[4]);
                String expr = obj1.toJava(String.class);
                out.append(transFrom_form_out(expr) + "\n");
            }
        } else if (prefix.equals("limit"))                                                               //极限
        {
            String str = s.substring(front, s.length());
            int frist = str.indexOf("(");
            str = str.substring(frist + 1, str.length());
            int second = str.lastIndexOf(")");
            str = str.substring(0, second);
            String str2[] = str.split(",");
            for (int i = 0; i < str2.length; i++) {
                str2[i] = str2[i].trim();
            }
            str2[0] = transFrom_form(str2[0]);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("calculus").callAttr("lim", str2[0], str2[1], str2[2]);
            String expr = obj1.toJava(String.class);
            out.append(transFrom_form_out(expr) + "\n");
        } else if (prefix.equals("eva"))                                            //表达式求值
        {
            String str = s.substring(front, s.length());
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ',')
                    sum++;
            }
            int frist = str.indexOf("(");
            str = str.substring(frist + 1, str.length());
            int second = str.lastIndexOf(")");
            str = str.substring(0, second);
            String str2[] = str.split(",");
            for (int i = 0; i < str2.length; i++) {
                str2[i] = str2[i].trim();
            }
            if (sum == 2) {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("algebra").callAttr("exp", str2[0], str2[1], str2[2]);
                String expr = obj1.toJava(String.class);
                out.append(transFrom_form_out(expr) + "\n");
            }
            if (sum == 4) {
                str2[0] = transFrom_form(str2[0]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("algebra").callAttr("exp_two", str2[0], str2[1], str2[2], str2[3], str2[4]);
                String expr = obj1.toJava(String.class);
                out.append(transFrom_form_out(expr) + "\n");
            }
        } else if (prefix.equals("equ"))                                                                 //解方程
        {
            String str = s.substring(front, s.length());
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ',')
                    sum++;
            }
            int frist = str.indexOf("(");
            str = str.substring(frist + 1, str.length());
            int second = str.lastIndexOf(")");
            str = str.substring(0, second);
            String str2[] = str.split(",");
            for (int i = 0; i < str2.length; i++) {
                str2[i] = str2[i].trim();
            }
            if (sum == 2) {
                str2[0] = transFrom_form(str2[0]);
                str2[1] = transFrom_form(str2[1]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("algebra").callAttr("equ", str2[0], str2[1], str2[2]);
                String expr = obj1.toJava(String.class);
                out.append(transFrom_form_out(expr) + "\n");
            }
            if (sum == 4) {
                str2[0] = transFrom_form(str2[0]);
                str2[1] = transFrom_form(str2[1]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("algebra").callAttr("equ_set_lin", str2[0], str2[1], str2[2], str2[3], str2[4]);
                String expr = obj1.toJava(String.class);
                expr=transFrom_form_out(expr);
                expr=expr.replace("{","");
                expr=expr.replace("}","");
                int fr = expr.indexOf("(");
                expr = expr.substring(fr + 1, expr.length());
                int se = expr.lastIndexOf(")");
                expr = expr.substring(0, se);
                expr=expr.trim();
                String st[] = expr.split(",");
                out.append(str2[2]+"="+st[0]+" "+str2[3]+"="+st[1] + " "+str2[4]+"="+st[2] +"\n");
            }
            if (sum == 3) {
                str2[0] = transFrom_form(str2[0]);
                str2[1] = transFrom_form(str2[1]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("algebra").callAttr("equ_set_nonlin_two", str2[0], str2[1], str2[2], str2[3]);
                String expr = obj1.toJava(String.class);
                expr=transFrom_form_out(expr);
                expr=expr.replace("{","");
                expr=expr.replace("}","");
                int fr = expr.indexOf("(");
                expr = expr.substring(fr + 1, expr.length());
                int se = expr.lastIndexOf(")");
                expr = expr.substring(0, se);
                expr=expr.trim();
                String st[] = expr.split(",");
                out.append(str2[2]+"="+st[0]+" "+str2[3]+"="+st[1] + "\n");
            }
            if (sum == 5) {
                str2[0] = transFrom_form(str2[0]);
                str2[1] = transFrom_form(str2[1]);
                str2[2] = transFrom_form(str2[2]);
                Python py = Python.getInstance();
                PyObject obj1 = py.getModule("algebra").callAttr("equ_set_nonlin_three", str2[0], str2[1], str2[2], str2[3], str2[4], str2[5]);
                String expr = obj1.toJava(String.class);
                expr=transFrom_form_out(expr);
                expr=expr.replace("{","");
                expr=expr.replace("}","");
                int fr = expr.indexOf("(");
                expr = expr.substring(fr + 1, expr.length());
                int se = expr.lastIndexOf(")");
                expr = expr.substring(0, se);
                expr=expr.trim();
                String st[] = expr.split(",");
                out.append(str2[3]+"="+st[0]+" "+str2[4]+"="+st[1]+" "+str2[5]+"="+st[2] + "\n");
            }
        } else if (prefix.equals("tequ"))                                                                 //解三角函数方程
        {
            String str = s.substring(front, s.length());
            int frist = str.indexOf("(");
            str = str.substring(frist + 1, str.length());
            int second = str.lastIndexOf(")");
            str = str.substring(0, second);
            String str2[] = str.split(",");
            for (int i = 0; i < str2.length; i++) {
                str2[i] = str2[i].trim();
            }
            str2[0] = transFrom_form(str2[0]);
            str2[1] = transFrom_form(str2[1]);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("algebra").callAttr("equ_set_trig", str2[0], str2[1], str2[2], str2[3]);
            String expr = obj1.toJava(String.class);
            expr=transFrom_form_out(expr);
            expr=expr.replace("{","");
            expr=expr.replace("}","");
            int fr = expr.indexOf("(");
            expr = expr.substring(fr + 1, expr.length());
            int se = expr.lastIndexOf(")");
            expr = expr.substring(0, se);
            expr=expr.trim();
            String st[] = expr.split(",");
            out.append(str2[2]+"="+st[0]+" "+str2[3]+"="+st[1]  + "\n");
        } else if (prefix.equals("simp"))                                                                 //化简多项式
        {
            String str = s.substring(front, s.length());
            int frist = str.indexOf("(");
            str = str.substring(frist + 1, str.length());
            int second = str.lastIndexOf(")");
            str = str.substring(0, second);
            String str2 = str.trim();
            str2 = transFrom_form(str2);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("algebra").callAttr("simp", str2);
            String expr = obj1.toJava(String.class);
            out.append(transFrom_form_out(expr) + "\n");
        } else if (prefix.equals("tsimp"))                                                                 //化解三角函数多项式
        {
            String str = s.substring(front, s.length());
            int frist = str.indexOf("(");
            str = str.substring(frist + 1, str.length());
            int second = str.lastIndexOf(")");
            str = str.substring(0, second);
            String str2 = str.trim();
            str2 = transFrom_form(str2);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("algebra").callAttr("simp_trig", str2);
            String expr = obj1.toJava(String.class);
            out.append(transFrom_form_out(expr) + "\n");
        } else if (prefix.equals("summ"))                                                                 //数列累加
        {
            String str = s.substring(front, s.length());
            int frist = str.indexOf("(");
            str = str.substring(frist + 1, str.length());
            int second = str.lastIndexOf(")");
            str = str.substring(0, second);
            String str2[] = str.split(",");
            for (int i = 0; i < str2.length; i++) {
                str2[i] = str2[i].trim();
            }
            str2[0] = transFrom_form(str2[0]);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("algebra").callAttr("summ", str2[0], str2[1], str2[2], str2[3]);
            String expr = obj1.toJava(String.class);
            out.append(transFrom_form_out(expr) + "\n");
        } else if (prefix.equals("com"))                                               //组合
         {
             out.append(com(s));
         }
         else if (prefix.equals("nop"))                                               //排列
         {
             out.append(nop(s));
         } else if (prefix.equals("model"))                                            //模
         {
             out.append("模:" + model(s.substring(front, s.length())));
         } else if (prefix.equals("cos")) {
             out.append("余弦值为:" + cos(s.substring(front, s.length())));
         }
         else if (prefix.equals("dc")) {
             String[] res = dc(s.substring(front, s.length()).trim());
             out.append("cosα:" + res[0] + " ");                   //输出
             out.append("cosβ:" + res[1] + " ");
             out.append("cosγ:" + res[2] + "\n");
         }
         else if (prefix.equals("projection"))
         {
             out.append("向量1在向量2上的投影为:" + projection(s.substring(front, s.length())));
         }
         else if (prefix.equals("product")) {
             out.append("向量积：");
             String str=product(s.substring(front, s.length()));
             out.append(str);
             int frist=str.indexOf("(");
             str=str.substring(frist+1,str.length());
             int second=str.lastIndexOf(")");
             str=str.substring(0,second);
             String[] st = str.split(",");
             out.append(st[0]+"i"+st[1]+"j"+st[2]+"k");
         }
         else if (prefix.equals("da"))
         {
             int angle[]=da(s.substring(front, s.length()));
             out.append("α:" + angle[0] + "\n");                   //输出
             out.append("β:" + angle[1] + "\n");
             out.append("γ:" + angle[2] + "\n");
         }
         else if(prefix.equals("sd"))
         {
             out.append(sd("同向单位向量："+sd(s.substring(front, s.length()))));
         }
         else if(prefix.equals("od"))
         {
             out.append("反向单位向量："+od(s.substring(front, s.length())));
         }
         else if(prefix.equals("mixed"))
         {
             out.append("混合积："+mixed(s.substring(front, s.length())));
         }
         else if(prefix.equals("dot"))
         {
             out.append("数量积："+dot(s.substring(front, s.length())));
         }
         else if (prefix.equals("det"))                                                //行列式
         {
             matrix one = Operation.transFrom(s.substring(front, s.length()));
             real result = one.getresult();
             out.append("值为：  " + result.toString());
         }
         else if(prefix.equals("norm"))                                                        //范数
         {
             matrix one = Operation.transFrom(s.substring(front, s.length()));
             real result = one.norm();
             out.append("范数：  √" + result);
         }
         else
         {
             out.append("请检查输入是否正确");
         }
    }
    //向量处理部分
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
     static String nop(String s)                                     //排列
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
    static String model(String s)                                    //模
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
        return ss;
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
            if(f_s.contains("√"))
            {
                if(!f_s.contains("-"))
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
            if(l_s.contains("√"))
            {
                if(!l_s.contains("-"))
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
        else if(s.contains("√"))
        {
            if(!s.contains("-"))
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
        s=s.trim();
        int num = 0;
        String[] res = new String[3];
        for (int i = 0; i < s.length(); i++)       //检测
        {
            num++;
        }
        if (num < 2) {
            out.append("向量格式出错");
        }
        int front = s.indexOf(",");                      //分解向量
        int last = s.lastIndexOf(",");
        String a = s.substring(s.indexOf("(") + 1, front);
        String b = s.substring(front + 1, last);
        String c = s.substring(last + 1, s.length() - 1);
        real f1 = new real(a.trim());                //赋值
        real f2 = new real(b.trim());
        real f3 = new real(c.trim());;
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
            string=string.replace("(","");
            string=string.replace(")","");
            String[] str = string.split(",");
            real[] nums = new real[6];
            if (str.length == 6) {
                for (int i = 0; i < 6; i++) {
                    nums[i] = new real(str[i].trim());
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
            out.append(string);
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
