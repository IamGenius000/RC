from sympy import *


# 表达式求值一元
def exp(str1, sym, num):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    expr = sympify(str1)
    return str(expr.evalf(subs={sym: num}))


# 表达式求值二元
def exp_two(str1, sym, num, sym2, num2):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    expr = sympify(str1)
    return str(expr.evalf(subs={sym: num, sym2: num2}))


# 方程
# 一元n次方程
def equ(str1, str2, sym):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(solveset(Eq(sympify(str1), sympify(str2)), sym))


# 方程组线性
def equ_set_lin(str1, str2, sym1, sym2, sym3):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(linsolve([sympify(str1), sympify(str2)], (sympify(sym1), sympify(sym2), sympify(sym3))))


# 方程组非线性二元
def equ_set_nonlin_two(str1, str2, sym1, sym2):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z', real=True)
    return str(linsolve([sympify(str1), sympify(str2)], [sym1, sym2]))


# 方程组非线性三元
def equ_set_nonlin_three(str1, str2, str3, sym1, sym2, sym3):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z', real=True)
    return str(linsolve([sympify(str1), sympify(str2), sympify(str3)], [sym1, sym2, sym3]))


# 方程组三角函数二元
def equ_set_trig(str1, str2, sym1, sym2):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(solve([sympify(str1), sympify(str2)], [sym1, sym2]))


# 多项式化简 笼统不精确
def simp(str1):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(simplify(sympify(str1)))


# 三角恒等式化简变换
def simp_trig(str1):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(trigsimp(sympify(str1)))


# 数列求和
def summ(str1, sym, num1, num2):
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z = symbols(
        'a b c d e f g h i j k l m n o p q r s t u v w x y z')
    return str(summation(sympify(str1), (sym, num1, num2)))