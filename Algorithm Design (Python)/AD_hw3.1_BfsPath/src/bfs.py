# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!
verbose = False

def edges2nb(n, edges):
    # pre-processing to convert a list of edges into adj. list representation
    # n is the no. of vertices, and we assume vertices are numbered 1,2,..,n
    # edges is a list of edges
    # e.g. n = 3, edges = [(1,2),(1,3)]; then nb[1] = [2,3], nb[2] = [1], nb[3] = [1]

    nb = [[] for n in range(n+1)]   ## the error lies in this line  
    
    for edge in edges:
        nb[edge[0]].append(edge[1])
        nb[edge[1]].append(edge[0])
    return nb

def bfs(n, edges, s):
    # runs BFS on the graph ([n], edges) starting from node s
    # n and edges as before, s is the start node for BFS
    # e.g. n = 3, edges = [(1,2),(1,3),(2,3)], s =1: output will be [(1,2),(1,3)]

    nb = edges2nb(n, edges)
    discovered = [False] * (n+1)
    discovered[s] = True
    i, L, T = 0, [[s]], []  ## the error lies in this line

    while (L[i]):   
        #if verbose: print "level ", i, " : ", L[i]
        L.append([])
        for u in L[i]:   
            # add all undiscovered neighbors of u
            for v in nb[u]:
                if not discovered[v]:
                    discovered[v] = True
                    T.append((u,v))
                    L[i+1].append(v)
                    
        i+=1              ## the error lies in this line
    return T

def bfs_path(n, edges, s, t):
    # on the graph ([n], edges), return
    # the shortest path from s to t
    if s==t:
        return [t]     
    else:
        bfs_tree=bfs(n,edges,s)
        for edge in bfs_tree:
            if edge[1] == t: 
                break
    return  bfs_path(n, edges, s, edge[0])+[t]