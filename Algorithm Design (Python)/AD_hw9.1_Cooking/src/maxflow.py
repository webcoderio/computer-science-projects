# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

## http://en.wikipedia.org/wiki/Push-relabel_maximum_flow_algorithm
def maxflow(C, source, sink):
    n = len(C) # C is the capacity matrix
    F = [[0] * n for _ in xrange(n)]
    # residual capacity from u to v is C[u][v] - F[u][v]
 
    height = [0] * n # height of node
    excess = [0] * n # flow into node minus flow from node
    seen   = [0] * n # neighbours seen since last relabel
    # node "queue"
    list   = [i for i in xrange(n) if i != source and i != sink]
 
    def push(u, v):
        send = min(excess[u], C[u][v] - F[u][v])
        F[u][v] += send
        F[v][u] -= send
        excess[u] -= send
        excess[v] += send
 
    def relabel(u):
       # find smallest new height making a push possible,
       # if such a push is possible at all
       min_height = height[u]
       for v in xrange(n):
           if C[u][v] - F[u][v] > 0:
               min_height = min(min_height, height[v])
               height[u] = min_height + 1
 
    def discharge(u):
       while excess[u] > 0:
           if seen[u] < n: # check next neighbour
               v = seen[u]
               if C[u][v] - F[u][v] > 0 and height[u] > height[v]:
                   push(u, v)
               else:
                   seen[u] += 1
           else: # we have checked all neighbours. must relabel
               relabel(u)
               seen[u] = 0
 
    height[source] = n   # longest path from source to sink is less than n long
    excess[source] = float('+infinity') # send as much flow as possible to neighbours of source
    for v in xrange(n):
        push(source, v)
 
    p = 0
    while p < len(list):
       u = list[p]
       old_height = height[u]
       discharge(u)
       if height[u] > old_height:
           list.insert(0, list.pop(p)) # move to front of list
           p = 0 # start from front of list
       p += 1
 
    return sum(F[source])

def edges2matrix(n, edgeC):
    ## n is the number of vertices; vertices are numbered 0,1,..,n-1
    ## edgesC is a list of values [start vertex, end vertex, capacity]

    C = [[0] * n for _ in xrange(n)]
    for [u,v,c] in edgeC:
        C[u][v] = c
    return C


if __name__ == '__main__':

    ## graph with two vertices 0 and 1:
    ## edge from 0 to 1 with capacity 3
    ## edge from 1 to 0 with capacity 2
    ## max flow from 0 to 1 is 3
    print maxflow(edges2matrix(2,[
            [0,1,3],
            [1,0,2]]),
              0,1)

    ## first example in lecture
    ## max flow is 30
    print maxflow(edges2matrix(4,[  ## s,u,v,t -> 0,1,2,3
            [0,1,20],   ## s->u, 20
            [0,2,10],
            [1,2,30],
            [1,3,10],
            [2,3,20]
            ]),
              0,3)

    ## second example in lecture
    ## max flow is 4
    print maxflow(edges2matrix(4,[  ## s,u,v,t -> 0,1,2,3
            [0,1,2],   ## s->u, 2
            [0,2,4],
            [1,2,6],
            [1,3,4],
            [2,3,2]
            ]),
              0,3)
