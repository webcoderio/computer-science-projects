#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

import array

def reboot(x, s):
    """takes a vector x of available data and
    and a vector s of capabilities and returns
    the total amount of data processsed in
    optimal solution"""

    """A[i][j] is optimal total data processed
    starting from day i and the processor in a
    state determined by s_j"""

    """B[i][j] is state on day i+1 for optimum solution 
    starting from day i and the processor in a
    state determined by s_j"""

    n = len(x)

    A = [array.array('I', (0 for j in range(n)))
                     for i in range(n)]

    B = [array.array('I', (0 for j in range(n)))
                     for i in range(n)]
    
    for j in range(0,n-1):
        A[n-1][j] = min(x[n-1], s[j])

    for i in range(n-2,-1,-1):
        for j in range(0,n-1):
            loadY = 0 + A[i+1][0]
            ## if we reboot, then day i load is 0, and
            ## we're off to day i+1 with the processor in state s_0

            loadN = min(x[i], s[j]) + A[i+1][j+1]
            ## if we don't boot, then day i load is min(x[i], s[j])
            ## and we're off to day i+1 with the processor in state s_{j+1}

            if loadY > loadN:
                A[i][j] = loadY
                B[i][j] = 0
            else:
                A[i][j] = loadN
                B[i][j] = j+1

    next = 0
    for i in range(0,n-1):
        next = B[i][next]
        if next == 0:
            print "reboot on day", i+1

    return A[0][0]

if __name__ == '__main__':
    print reboot([10,1,7,7],[8,4,2,1])

def reboot2(x, s):
    """takes a vector x of available data and
    and a vector s of capabilities and returns
    the total amount of data processsed in
    optimal solution"""

    """A[i][j] is optimal total data processed
    starting from day i and the processor in a
    state determined by s_j"""

    n = len(x)

    A = [array.array('I', (0 for j in range(n)))
                     for i in range(n+1)]
    
    for j in range(0,n-1):
        A[n][j] = 0

    for i in range(n-1,-1,-1):
        for j in range(0,n-1):
            A[i][j] = max(0 + A[i+1][0],
                          ## if we reboot, then day i load is 0, and
                          ## we're off to day i+1 with the processor in state s_0
                         min(x[i], s[j]) + A[i+1][j+1])
                          ## if we don't boot, then day i load is min(x[i], s[j])
            ## and we're off to day i+1 with the processor in state s_{j+1}

    return A[0][0]

