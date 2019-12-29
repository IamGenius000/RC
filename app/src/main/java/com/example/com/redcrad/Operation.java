package com.example.com.redcrad;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

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
        if(str.contains("sqrt"))
            str=str.replace("sqrt","√");
        return str;
    }
    static String transFrom_mat(String s)
    {
        String str=s;
        str=transFrom_form_out(str);
        int frist=str.indexOf("[");
        int last=str.lastIndexOf("]");
        str=str.substring(frist+1,last);
        return str;
    }
    static void check(String s) throws Exception {
        s=s.trim();
        int front = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                front = i;
                break;
            }
        }
        String prefix = s.substring(0, front);
         if (prefix.equals("inv"))                                            //逆矩阵
        {
            String str = s.substring(front, s.length());
            str=transFrom_form(str);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("linear").callAttr("mat_inv", str);
            String expr = obj1.toJava(String.class);
            tt.append("逆矩阵为："+"\n");
            expr=transFrom_mat(expr);
            tt.append(expr+ "\n");
        } else if (prefix.equals("ref"))                                                 //行阶梯形矩阵
        {
            String str = s.substring(front, s.length());
            str=transFrom_form(str);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("linear").callAttr("mat_rref", str);
            String expr = obj1.toJava(String.class);
            tt.append("行阶梯形矩阵为：  "+"\n" );
            tt.append(transFrom_mat(expr) + "\n");
        } else if (prefix.equals("alg"))                                               //代数余子式
        {
            matrix one = transFrom(s.substring(front, s.length()));
            matrix two=one.AC();
            tt.append("代数余子式：\n");
            format(two.showstring());
        } else if (prefix.equals("dim"))                                                  //维数
        {
        } else if (prefix.equals("adj"))                                                  //共轭复数
        {
            String str = s.substring(front, s.length());
            str=transFrom_form(str);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("linear").callAttr("mat_adj", str);
            String expr = obj1.toJava(String.class);
            tt.append(transFrom_mat(expr)+ "\n");
        } else if (prefix.equals("qr"))                                                  //QR
        {
            String str = s.substring(front, s.length());
            str=transFrom_form(str);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("linear").callAttr("mat_QR_Q", str);
            String expr = obj1.toJava(String.class);
            tt.append("Q为："+"\n");
            tt.append(transFrom_mat(expr)+ "\n");
            PyObject obj2 = py.getModule("linear").callAttr("mat_QR_R", str);
            String expr2 = obj2.toJava(String.class);
            tt.append("R为："+"\n");
            tt.append(transFrom_mat(expr2)+ "\n");
        } else if (prefix.equals("lu"))                                                  //LU
        {
            String str = s.substring(front, s.length());
            str=transFrom_form(str);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("linear").callAttr("mat_lu_l", str);
            String expr = obj1.toJava(String.class);
            tt.append("L为："+"\n");
            tt.append(transFrom_mat(expr)+ "\n");
            PyObject obj2 = py.getModule("linear").callAttr("mat_lu_u", str);
            String expr2 = obj2.toJava(String.class);
            tt.append("U为："+"\n");
            tt.append(transFrom_mat(expr2)+ "\n");
        }
         else if (prefix.equals("ldl"))                                                  //LDL
         {
             String str = s.substring(front, s.length());
             str=transFrom_form(str);
             Python py = Python.getInstance();
             PyObject obj1 = py.getModule("linear").callAttr("mat_LDL_L", str);
             String expr = obj1.toJava(String.class);
             tt.append("L为："+"\n");
             tt.append(transFrom_mat(expr)+ "\n");
             PyObject obj2 = py.getModule("linear").callAttr("mat_LDL_D", str);
             String expr2 = obj2.toJava(String.class);
             tt.append("D为："+"\n");
             tt.append(transFrom_mat(expr2)+ "\n");
         }else if (prefix.equals("svd"))                                                  //SVD
        {

        } else if(prefix.equals("pd"))                                                 //对角化
        {
            String str = s.substring(front, s.length());
            str=transFrom_form(str);
            Python py = Python.getInstance();
            PyObject obj1 = py.getModule("linear").callAttr("mat_PD_P", str);
            String expr = obj1.toJava(String.class);
            tt.append("P为："+"\n");
            tt.append(transFrom_mat(expr)+ "\n");
            PyObject obj2 = py.getModule("linear").callAttr("mat_PD_D", str);
            String expr2 = obj2.toJava(String.class);
            tt.append("D为："+"\n");
            tt.append(transFrom_mat(expr2)+ "\n");
         }else if (prefix.equals("chol"))                                                  //乔利斯基分解
         {
         }
        else if(prefix.equals("mixed"))
        {
            tt.append("混合积："+mixed(s.substring(front, s.length())));
        }
        else {
                calculation(s);
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

}
