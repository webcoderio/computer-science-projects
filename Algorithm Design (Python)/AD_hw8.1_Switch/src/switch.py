#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

def switch(a, b):
## assume a and b are 1-indexed and n = no. of minutes
    n = len(b)
    opt = [0] * (2 * n)
    opt[0] = a[0]
    opt[1] = b[0]
    for i in range(1, n):
        opt[2 * i + 0] = max(a[i] + opt[2 * (i-1) + 0], opt[2 * (i-2) + 1])
        opt[2 * i + 1] = max(b[i] + opt[2 * (i-1) + 1], opt[2 * (i-1) + 0])
    return max(opt[2 * n - 2], opt[2 * n -1])