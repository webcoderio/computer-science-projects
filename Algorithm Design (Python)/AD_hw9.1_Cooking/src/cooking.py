#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

from maxflow import maxflow
from maxflow import edges2matrix

def cooking(notavail):
    n = len(notavail)
    edgesC = []
    
    for i in range(1,n+1):
    	edgesC.append([0,i,1])
    	for j in range(1,n+1):
    		if j in notavail[i-1]:
    			continue
		edgesC.append([i,j+n,1])

    for k in range(n+1,n*2+1):
        #print("k")
    	edgesC.append([k,n*2+1,1])
    
    # construct adj list
    adjlist = edges2matrix(n*2+2, edgesC)
    
    if (maxflow(adjlist,0,n*2+1)!=n):
        #print("F")
    	return False
    else:
        #print("T")
        return True