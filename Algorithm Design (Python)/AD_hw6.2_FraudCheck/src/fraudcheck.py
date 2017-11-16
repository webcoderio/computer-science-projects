#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

def fraudcheck(L):
    """takes a list L of length n"""
    """outputs True if there's an element in L that appears > n/2 times"""
    (ans, aux) = fraudcheckhelper(L)
    return ans

def fraudcheckhelper(L):
    """takes a list L of length n"""
    """this function should return some function in addition to True/False"""

    if len(L) == 1: ## this is the base case
        return (True,L[0])
    
    mid = len(L)/2  ## this is the divide step
    List1 = L[:mid]
    List2 = L[mid:]
    (ans1, aux1) = fraudcheckhelper(List1)
    (ans2, aux2) = fraudcheckhelper(List2)
    
    if ( L.count(aux1)>mid ) or ( L.count(aux2)>mid ) or ( List1.count(aux1)>len(L)/4 and List2.count(aux2)>len(L)/4 and aux1==aux2 ):
        return (True,aux1)
    else:
        return (False,None)