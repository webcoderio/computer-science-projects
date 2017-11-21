#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

#  Given an array of n integers a_0,a_1,...,a_{n-1} positive
#  and negative, such as [-1, 3, 2, -7, 4, 2, -3, 3, -1], you
#  want to find the largest sum of contiguous integers; in
#  this case it would be 4 + 2 - 2 + 3 = 7.

#  Note: the empty set has sum 0.

def partialsums(a):
    ## computes all of the partial sums in linear time
    ## ans[0] = a[0], ans[1] = a[0]+a[1], ans[2] = a[0]+a[1]+a[2], etc
    partial = 0
    ans = []
    for elt in a:
        partial += elt
        ans.append(partial)
    return ans
        
def maxsums(a):
    ## "brute force" algorithm to return
    ## the largest sum in time O(n^2)

    ans = 0
    li = partialsums(a)
    for i in range(0,len(a)):
        li.extend(partialsums(a[i:]))
        for j in range(i,len(li)):
            if li[j] > ans:
                ans = li[j]
    return ans

def maxsums_list(a):
    ## "brute force" algorithm to return
    ## the list of values corresponding to
    ## the largest sum in time O(n^2)

    ans = []
    li =[]
    for i in range(0,len(a)):
        maxsumsval = 0
        li = a[i:]
        for j in range(len(li)):
            maxsumsval += li[j]
            if(maxsumsval == maxsums(a)):
                ans = li[0:j+1]
    
    return ans

#li = [-1,9,2,3,-9,7]
#maxsums_list(li)