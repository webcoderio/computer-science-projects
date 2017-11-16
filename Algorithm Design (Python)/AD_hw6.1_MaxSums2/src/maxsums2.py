#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

#  Given an array of n integers a_0,a_1,...,a_{n-1} positive
#  and negative, such as [-1, 3, 2, -7, 4, 2, -3, 3, -1], you
#  want to find the largest sum of contiguous integers; in
#  this case it would be 4 + 2 - 2 + 3 = 7.

#  Note: the empty set has sum 0.
###########################################################

def partialsums(a):
    ## computes all of the partial sums in linear time
    ## ans[0] = a[0], ans[1] = a[0]+a[1], ans[2] = a[0]+a[1]+a[2], etc
    partial = 0
    ans = []
    for elt in a:
        partial += elt
        ans.append(partial)
    return ans

def maxsums_dc(a):
    ## divide and conquer algorithm to return
    ## the largest sum in time O(n log n)

    # Base case step
    if len(a) == 0:
        return 0
    elif len(a) == 1:
        return max(a[0], 0)

    # Recursive case step
    # '//' is a floor division
    # http://docs.python.org/release/2.2.3/whatsnew/node7.html
    middle = len(a)//2

    lsum = 0
    lmax = 0
    # xrange([start], stop[, step])
    # This function is very similar to range()
    # but returns an “xrange object”
    for left in xrange(1, middle + 1):
        lsum += a[middle - left]
        lmax = max(lmax, lsum)

    rsum = 0
    rmax = 0
    for right in xrange(middle, len(a)):
        rsum += a[right]
        rmax = max(rmax, rsum)

    return max(lmax + rmax, maxsums_dc(a[:middle]), maxsums_dc(a[middle:]))
    #O(n log n)