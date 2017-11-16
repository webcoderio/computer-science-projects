# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!
def seqalign(x,y):
    m = len(x)
    n = len(y)
    opt = [[0] * (n+1) for _ in range(m+1)] 

    x,y = " " + x, " " + y
    for i in range(0,m+1):
        opt[i][0] = i*0.5
    for j in range(0,n+1):
        opt[0][j] = j*0.5
    for i in range(1,m+1):
        for j in range(1,n+1):
            opt[i][j] = min (
                                (x[i] != y[j]) + opt[i-1][j-1],
                                0.5+opt[i-1][j],
                                0.5+opt[i][j-1])
    return opt[m][n]

print seqalign("CAT","AM")