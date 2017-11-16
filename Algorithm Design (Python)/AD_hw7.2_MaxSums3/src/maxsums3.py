#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
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

def maxsums_dp(a):
    ## using dynamic programming to find
    ## the largest sum in time O(n)

    ## you will need to figure out what your sub-problem is
    maxsum = 0
    maxindex = 0
    for i in xrange(0, len(a)):
        maxindex = max(maxindex + a[i], 0)
        maxsum = max(maxsum, maxindex)
    return maxsum
    #O(n)