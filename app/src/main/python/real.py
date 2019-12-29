from sympy import *
def add(str1,str2):
    return str(sympify(str1)+sympify(str2))
def sub(str1,str2):
    return str(sympify(str1)-sympify(str2))
def mul(str1,str2):
    return str(sympify(str1)*sympify(str2))
def div(str1,str2):
    return str(sympify(str1)/sympify(str2))
def cal(str1):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
    'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(sympify(str1))
