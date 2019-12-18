package com.example.com.redcrad;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

public class matrix {
    int row = 1;                                                        //行数
    int col = 1;                                                        //列数
    real mat[][];                                                    //矩阵数组
    static Context context;
    static TextView text;                                                   //调试用

    static void setext(TextView tt)                                      //初始化输出界面 调试用
    {
        text = tt;
    }

    static void setcontext(Context con) {
        context = con;
    }

    matrix(String s) {
        int sLen = s.length();
        for (int i = 0; i < sLen; i++)                                        //获取行数
        {
            if (i >= 1 && i <= s.length() - 1) {
                if (s.charAt(i) == ',' && s.charAt(i - 1) == ')')
                    if (s.charAt(i + 1) != '(') {
                        Toast.makeText(context, "提示：括号错误", Toast.LENGTH_SHORT).show();
                    }
                if (s.charAt(i) == '(' && s.charAt(i - 1) == ')') {
                    Toast.makeText(context, "提示：括号错误", Toast.LENGTH_SHORT).show();
                }
            }
        }
        for (int i = 0; i < sLen; i++)                                        //获取行数
        {
            if (s.charAt(i) == ',' && s.charAt(i - 1) == ')')
                row++;
        }
        for (int i = 0; i < sLen; i++)                                        //获取列数
        {
            if (s.charAt(i) == ')')
                break;
            if (s.charAt(i) == ',' && s.charAt(i - 1) >= '0' && s.charAt(i - 1) <= '9')
                col++;
        }
        mat = new real[row][col];
        s = s.replace("(", " ");
        s = s.replace(")", " ");
        String[] str = s.split(",");
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                mat[i][j] = new real(str[i * col + j]);
            }
    }

    matrix(int r, int c) {                                                                    //指定行列数的有参构造函数
        row = r;
        col = c;
        mat = new real[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                mat[i][j] = new real("0");
    }

    matrix() {
    }                                                        //无参构造

    void transpose() {                                                                    //转置
        real temp[][] = new real[col][row];
        int temprc = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                temp[j][i] = mat[i][j];
            }
        }
        mat = temp;
        temprc = row;
        row = col;
        col = temprc;
    }

    void nummult(String num)                                               //数乘
    {
        real num_i = new real(num);
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                mat[i][j] = num_i.mul(mat[i][j]);
    }

    void neg()                                                                   //矩阵整体变为负数
    {
        real r = new real("-1");
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                mat[i][j] = r.mul(mat[i][j]);
    }

    matrix plus(matrix m2)                                                       //加法运算
    {
        if (m2.row == this.row && m2.col == this.col) {
            for (int i = 0; i < this.row; i++)
                for (int j = 0; j < this.col; j++)
                    this.mat[i][j] = this.mat[i][j].add(m2.mat[i][j]);
        } else {
            Toast.makeText(context, "两矩阵行列不相等，无法进行加法运算", Toast.LENGTH_SHORT).show();
        }
        return this;
    }

    matrix minus(matrix m2) {                                                                        //减法运算
        if (m2.row == this.row && m2.col == this.col) {
            for (int i = 0; i < this.row; i++)
                for (int j = 0; j < this.col; j++)
                    this.mat[i][j] = this.mat[i][j].sub(m2.mat[i][j]);
        } else {
            Toast.makeText(context, "两矩阵行列不相等，无法进行减法运算", Toast.LENGTH_SHORT).show();
        }
        return this;
    }

    static matrix mult(matrix m1, matrix m2) {                                                                        //积运算
        if (m1.col == m2.row) {
            matrix rev = new matrix(m1.row, m2.col);
            int m1r = 0, m1c = 0, m2r = 0, m2c = 0;
            for (int i = 0; i < rev.row; i++, m2c = 0, m1r++) {
                for (int j = 0; j < rev.col; j++, m1c = 0, m2r = 0, m2c++) {
                    for (; m1c < m1.col; m1c++, m2r++)
                        rev.mat[i][j] = rev.mat[i][j].add(m1.mat[m1r][m1c].mul(m2.mat[m2r][m2c]));
                }
            }
            return rev;
        } else {
            Toast.makeText(context, "错误:矩阵一的行数与矩阵二的列数不相等 无法进行乘法运算", Toast.LENGTH_SHORT).show();
            return m1;
        }
    }
    static matrix dot(matrix m1, matrix m2)                                                                                    //点积
    {
        matrix rev = new matrix(1, 1);
        real dot_result = new real("0");
        if (m1.col == m2.col) {
            m2.transpose();
            for (int i = 0; i < m1.col; i++) {
                dot_result = dot_result.add(m1.mat[0][i].mul(m2.mat[i][0]));
            }
            rev.mat[0][0] = dot_result;
            return rev;
        } else {
            Toast.makeText(context, "向量格式错误", Toast.LENGTH_SHORT).show();
            return rev;
        }
    }
    real norm()                                                                                      //2范数
    {
        real result = new real("0");
        if (row == 0 || col == 0)
            Toast.makeText(context, "错误:无数值", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                result = result.add(mat[i][j].mul(mat[i][j]));
            }
        return result;
    }
      matrix AC() throws Exception                                                                                      //代数余子式
      {
          matrix one = new matrix(row, col);
          matrix two = new matrix(row - 1, col - 1);
          for (int i = 0; i < row; i++)
              for (int j = 0; j < col; j++)
              {
                  int i_num = 0;
                  int j_num = 0;
                  int ii=0;
                  while(ii<row)
                  {
                      if(ii!=i)
                      {
                          int jj=0;
                          while(jj<col)
                          {
                              if(jj!=j)
                              {
                                  two.mat[i_num][j_num] = mat[ii][jj];
                                  j_num++;
                                  if (j_num == col - 1)
                                  {
                                      j_num = 0;
                                      i_num++;
                                  }
                              }
                              jj++;
                          }
                      }
                      ii++;
                  }
                  real result = new real(String.valueOf(Math.pow(-1, (i+1)+(j+1)))).mul(two.getresult());
                  one.mat[i][j] = result;
              }
          one.transpose();
          return one;
      }
      matrix inverse() throws Exception                                                                              //逆矩阵
      {
          matrix one;
          one=AC();
          one.nummult(getresult().toString());
          return one;
      }
    void show() {                                                                        //输出
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                System.out.print(mat[i][j].toString() + " ");
            System.out.println();
        }
    }

    String showstring() {
        String s = "((";
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                s += String.valueOf(mat[i][j].toString());
                if (j != col - 1)
                    s += ",";
                if (j == col - 1 && i != row - 1)
                    s += "),(";
                else if (j == col - 1)
                    s += ")";
                if (j == col - 1 && i == row - 1)
                    s += ")";
            }
        return s;
    }

    int showrow() {
        return row;
    }

    int showcol() {
        return col;
    }

    String getmat(int x, int y) {
        return String.valueOf(mat[x][y].toString());
    }

    public real[][] getMat() {
        return mat;
    }

    void setMat(real mat1[][]) {
        mat = mat1;
        row = mat1.length;
        col = mat1[0].length;
    }
    real getresult() throws Exception {                                                     //行列式|A|
        try {
            return  mathDeterminantCalculation(mat);
        } catch (Exception e) {
            Toast.makeText(context,"提示：行列式错误",Toast.LENGTH_SHORT).show();
        }
        return new real("1");
    }
    static real mathDeterminantCalculation(real[][] value) throws Exception {                   //计算结果
        if (value.length == 1) {
            return value[0][0];
        } else if (value.length == 2) {
            return value[0][0].mul(value[1][1]).sub(value[0][1].mul(value[1][0]));
        }
        real result = new real("-1");
        for (int i = 0; i < value.length; i++) {
            if (value[i][i].toString().equals("0")) {
                value = changeDeterminantNoZero(value, i, i);
                result = result.mul(new real("-1"));
            }
                for (int j = 0; j < i; j++) {
                    if (value[i][j].toString().equals("0")) {
                        continue;
                    }
                    if (value[j][j].toString().equals("0")) {
                        real[] temp = value[i];
                        value[i] = value[i - 1];
                        value[i - 1] = temp;
                        result = result.mul(new real("-1"));
                        continue;
                    }
                    real ratio = new real("-1").mul(value[i][j].div(value[j][j]));
                    value[i] = addValue(value[i], value[j], ratio);
                }
            }
            return mathValue(value, result);
    }
    static real mathValue(real[][] value,real result) throws Exception{                             //计算结果
        for (int i = 0; i < value.length; i++) {
            if (value[i][i].toString().equals("0")) {
                return new real("0");
            }
            result = result.mul(value[i][i]);
        }
        return result;
    }
    static real[] addValue(real[] currentRow,real[] frontRow, real ratio)throws Exception{
        for (int i = 0; i < currentRow.length; i++) {
            currentRow[i]=currentRow[i].add(frontRow[i].mul(ratio));
        }
        return currentRow;
    }
    static real[][] changeDeterminantNoZero(real[][] determinant,int line,int row)throws Exception{
        for (int j = line; j < determinant.length; j++) {
            if (!determinant[j][row].toString().equals("0")) {
                real[] temp = determinant[line];
                determinant[line] = determinant[j];
                determinant[j] = temp;
                return determinant;
            }
        }
        return determinant;
    }
    void Simplify() {                                                                     //行阶梯形矩阵
        for (int i = 0; i < mat.length; i++) {
            real temp = new real("1");
            int cur = 0;
            for (int a = 0; a < mat[i].length; a++) {
                if (cur == 0 && !mat[i][a].toString().equals("0")) {
                    temp = mat[i][a];
                    mat[i][a] = new real("1");
                    cur++;
                } else if (cur == 1) {
                    mat[i][a] = mat[i][a].div(temp);
                }
            }
        }
    }
    int find_one(real[] a){                                                           //行阶梯形矩阵
        for (int b=0;b<a.length;b++){
            if (a[b].toString().equals("1")){
                return b;
            }
        }
        return -1;
    }
    void Reduce(int size) {                                                            //行阶梯形矩阵
        this.Simplify();
        if (size != mat.length) {
            int pivot = find_one(mat[size]);
            for (int o = 0; o < mat.length; o++) {
                if (o != size) {
                    if(pivot==-1){
                        continue;
                    }
                    real m = mat[o][pivot];
                    this.Rows(size,o,m);
                }
            }
            Reduce(++size);
        }
    }
    void Rows(int a,int b,real ply){                                                  //行阶梯形矩阵
        int l =mat[a].length;
        for (int u=0;u<l;u++){
            mat[b][u]=mat[b][u].sub(mat[a][u].mul(ply));
        }
    }
}
