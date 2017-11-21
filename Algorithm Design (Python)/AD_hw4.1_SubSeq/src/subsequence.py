#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

def subsequence(p, s):
    # p's length must less or equal to s
    if len(p) > len(s):
        return False
    
    # loop thru to remember how many found
    StrFound = 0
    for str in p:
        if str in s:
            StrFound += 1
        else:
            StrFound = -1   # doesn't find
            break
            
    # if found # match
    if StrFound == len(p):
        return True
    else:
        return False

# Test only
#p = [1,2,3]
#s = [1,2,3]
#subsequence(p,s)