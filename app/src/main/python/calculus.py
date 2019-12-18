from sympy import *


# 导数部分
# 求导(可求高阶，默认1阶)一元

def deri(str1, sym, num=1):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(diff(sympify(str1), sym, num))


# 求导二元
def deri_two(str1, sym, num=1, sym2='', num2=1):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(diff(sympify(str1), sym, num, sym2, num2))


# 求导三元
def deri_three(str1, sym, num=1, sym2='', num2=1, sym3='', num3=1):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(diff(sympify(str1), sym, num, sym2, num2, sym3, num3))


# 偏导数
def deri_part(str1, sym, sym2):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(diff(sympify(str1), sym) + diff(sympify(str1), sym2))


# 隐函数求导

# 方向导数

# 抽象导数

# 积分部分
# 不定积分
def inte_no(str1, sym):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(integrate(sympify(str1), sympify(sym)))


# 二层
def inte_no_two(str1, sym, sym2):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(integrate(sympify(str1), sympify(sym), sympify(sym2)))


# 三层
def inte_no_three(str1, sym, sym2, sym3):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(integrate(sympify(str1), sympify(sym), sympify(sym2), sympify(sym3)))


# 定积分
# 一层
def inte(str1, sym, front, last):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(integrate(sympify(str1), (sym, front, last)))


# 二层
def inte_two(str1, sym, front, last, sym2, front2, last2):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(integrate(sympify(str1), (sym, front, last), (sym2, front2, last2)))


# 三层
def inte_three(str1, sym, front, last, sym2, front2, last2, sym3, front3, last3):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(integrate(sympify(str1), (sym, front, last), (sym2, front2, last2), (sym3, front3, last3)))


# 微分方程

# 极限部分一元
def lim(str1, sym, last):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(limit(sympify(str1), sym, last))


# 评估极限
def lim_est(str1, sym, last, sym2):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(limit(sympify(str1), sym, last, sym2))