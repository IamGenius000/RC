from sympy import *
import numpy as np

# 求解行列式
def mat_det(str1):
    return str(Matrix(sympify(str1)).det())


#迹
def mat_trace(str1):
    return str(np.trace(np.array(sympify(str1))))

#维数
def mat_dim(str1):
    return str(np.array(sympify(str1)).ndim)

# 秩
def mat_rank(str1):
    return str(Matrix(sympify(str1)).rank())


# 逆矩阵
def mat_inv(str1):
    return str(Matrix(sympify(str1)).inv())


# 点积
def mat_dot(str1, str2):
    return str(Matrix(sympify(str1)).dot(Matrix(sympify(str2))))


# 叉乘
def mat_cross(str1, str2):
    return str(Matrix(sympify(str1)).cross(Matrix(sympify(str2))))


# LU分解
def mat_lu(str1):
    return str(Matrix(sympify(str1)).inv(method="LU"))


# LU分解
def mat_lu_l(str1):
    L,U,P = Matrix(sympify(str1)).LUdecomposition()
    return str(L)


# LU分解
def mat_lu_u(str1):
    L, U,P = Matrix(sympify(str1)).LUdecomposition()
    return str(U)


# QR分解 Q正交
def mat_QR_Q(str1):
    Q, R = Matrix(sympify(str1)).QRdecomposition()
    return str(Q)


# QR分解 R上三角
def mat_QR_R(str1):
    Q, R = Matrix(sympify(str1)).QRdecomposition()
    return str(R)


# LDL分解
def mat_LDL_L(str1):
    L, D = Matrix(sympify(str1)).LDLdecomposition()
    return str(L)


# LDL分解
def mat_LDL_D(str1):
    L, D = Matrix(sympify(str1)).LDLdecomposition()
    return str(D)


# 齐次线性方程组 ax=b a,b
def mat_gs(str1, str2):
    return str(Matrix(sympify(str1)).LUsolve(Matrix(sympify(str2))))


# 化简行阶梯型矩阵   (需删除)
def mat_rref(str1):
    return str(Matrix(sympify(str1)).rref())


# 特征值
def mat_eig(str1):
    return str(Matrix(sympify(str1)).eigenvals())


# 特征向量
def mat_eig_ver(str1):
    return str(Matrix(sympify(str1)).left_eigenvects())


# 对角化
def mat_PD_D(str1):
    P, D = Matrix(sympify(str1)).diagonalize()
    return str(D)


# 对角化
def mat_PD_P(str1):
    P, D = Matrix(sympify(str1)).diagonalize()
    return str(P)


# 共扼
def mat_D(str1):
    return str(Matrix(sympify(str1)).D)

#范数
def mat_norm(str1):
    return str(Matrix(sympify(str1)).norm())

#共扼复数
def mat_adj(str1):
    return str(np.conj(np.array(sympify(str1))))

#混合积
def mat_mix(str1,str2,str3):
    return str(Matrix(sympify(mat_cross(str1,str2))).dot(Matrix(sympify(str3))))

