#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

def timestamp_matching(t, x):
    """checks whether the events x match the approximate time-stamps in t"""
    """t is a 0-indexed list where t[i] is a list of 2 entries [t_i,e_i]
       corresponding to the time interval t_i +/- e_i"""
    """x is a 0-indexed list where x[i] is the time x_i"""
    
    x.sort()
    ## sort the list x
    t.sort(key=lambda y: y[0]) 
    ## sort the list t according to the values t_i
    
    # my algorithm start here
    # t, x
    XLength=len(x)
    TLength=len(t)

    i_count, j_count = 0,0
    while i_count < XLength:
        while j_count < TLength :
            if abs( (t[j_count])[0] - x[i_count] ) <= (t[j_count])[1]:
                i_count+= 1
                break
            else:
                j_count += 1
        if j_count == TLength:
            return False
    return True

# Test only
# t, x
#timestamp_matching([[1,1],[2,1]],[2,3]) # True
#timestamp_matching([[1,1],[2,1]],[5,5]) # False
#timestamp_matching([[3,3],[3,1]],[1,3]) # True
#print 2 in range(1,3)