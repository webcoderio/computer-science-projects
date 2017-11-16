#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

## Fill out the blanks below for parts (a) and (c):
##
## (a) length of the shortest possible list of coins that are coins of 
##     denominations d1, d2, . . . , dk needed to make change for i cents.
## (c) no. of sub-problems = i-1
##     time spent per subproblem = O(n)
##     total running time = O(nk)

def make_change(denomination, C):

    ## Here is some skeletal code to help you get started.

    ## initialization; you should replace 100 with a quantity
    ## related to the number of sub-problems
    """"
    opt = [0] * (100)     ## opt for sub-problems
    parent = [0] * (100)  ## maintain parent nodes for back-tracking

    ## solving the sub-problems
    for i in range(1,100):
        opt[i] = 1

    ## back-tracking
    ans = []
    while (i != 0):
        ans.append(0)
        i = parent[i]
     """       
    list = [None for element in range(C + 1)]
    list[0] = []
    for i in range(1, C + 1):
        for cointype in denomination:
            if cointype > i: continue
            elif not list[i] or ( len(list[i - cointype]) + 1 < len(list[i]) ):
                list[i] = list[i - cointype][:]
                list[i].append(cointype)
    #print(list[-1])
    return list[-1]
#test
#make_change([1, 5, 10, 17], 33)