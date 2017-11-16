#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

#import time

## write down T(n) for  n = 1000, 2000, 4000, 10000, 20000
# Running time tested on the bottom
# These are the console return
#
#===============================================================================
# ('T(1000): ', 0.031000137329101562, 'seconds')
# ('T(2000): ', 0.03099989891052246, 'seconds')
# ('T(4000): ', 0.07899999618530273, 'seconds')
# ('T(10000): ', 0.23399996757507324, 'seconds')
# ('T(20000): ', 0.46900010108947754, 'seconds')
# ('T(40000): ', 0.9839999675750732, 'seconds')
#===============================================================================
#

def merge(a, b):
    """combines two sorted lists into shorted whole"""
    i, j, out = 0, 0, []
    while i<len(a) and j<len(b):
        if a[i] < b[j]:
            out.append(a[i])
            i = i + 1
        else:
            out.append(b[j])
            j = j + 1
            
    if(len(a)>i):
        out.extend(a[i:])
    elif(len(b)>j):
        out.extend(b[j:])
    
    return out

def mergesort(a):
    if len(a) <= 1:
        return a
    else:
        mid = len(a)/2
        return merge(mergesort(a[:mid]),mergesort(a[mid:]))

def mergetest(n):
    a = range(1,n)
    a.reverse()
    return mergesort(a)


# TEST Running Time for T(n)
# ---------------------------------------------------
#===============================================================================
# start_time = time.time()
# mergetest(1000)
# end_time = time.time()
# print ('T(1000): ', end_time - start_time, 'seconds')
# 
# start_time = time.time()
# mergetest(2000)
# end_time = time.time()
# print ('T(2000): ', end_time - start_time, 'seconds')
# 
# start_time = time.time()
# mergetest(4000)
# end_time = time.time()
# print ('T(4000): ', end_time - start_time, 'seconds')
# 
# start_time = time.time()
# mergetest(10000)
# end_time = time.time()
# print ('T(10000): ', end_time - start_time, 'seconds')
# 
# start_time = time.time()
# mergetest(20000)
# end_time = time.time()
# print ('T(20000): ', end_time - start_time, 'seconds')
# 
# start_time = time.time()
# mergetest(40000)
# end_time = time.time()
# print ('T(40000): ', end_time - start_time, 'seconds')
#===============================================================================