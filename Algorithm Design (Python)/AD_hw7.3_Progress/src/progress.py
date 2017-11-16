#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

## Fill out the blanks below for questions 2(a) and (c):
##
## (a) first or second opt?
##    first
## (c) no. of sub-problems = n
##     time spent per subproblem = O(1) 
##     total running time = O(n)
def longest_increasing_subsequence(scores):
    scores_length = len(scores)
    if scores_length == 0:  # empty input return it
        return []
    # storages
    parentdict = {} # dictionary of parent
    maxscoredict = {}   # dictionary of LIS ends at index io
    #######################################################
    # iterate score in scores list
    maxscoredict[0] = 0 # fist index i initialize to 0
    for i in range(scores_length):
        maxjscore = 0 # max score of for all j < i
        maxjparent = -1 # parent of the max score for all j < i
        for j in range(i):
            # if score[i] and maxscoredict[j] is the new large value
            if ( maxscoredict[j] > maxjscore and scores[j] < scores[i] ):
                maxjparent = j
                maxjscore = maxscoredict[j]
        parentdict[i] = maxjparent
        maxjscore += 1 # add 1 to subproblem at i below
        maxscoredict[i] = maxjscore
        
    #######################################################
    # iterate score and do max compare to dictionary
    maxival = 0
    maxiindex = 0
    for k in range(scores_length):
        if maxscoredict[k] > maxival:
            maxiindex = k
            maxival = maxscoredict[k]
    lislist = [scores[maxiindex]] # build score list with last index on scores
    #######################################################
    # iterate when it has parent
    while parentdict.has_key(parentdict[maxiindex]):
        maxiindex = parentdict[maxiindex]
        lislist.append(scores[maxiindex])
    #######################################################
    # result
    #print(lislist)
    lislist.reverse() # reverse list element order
    return lislist