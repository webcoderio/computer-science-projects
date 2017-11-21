# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!
import heap_id

import math
def node_by_name(nodes, city, state):
    for node in nodes:
        if node.description.find(city) != -1 and node.state == state:
            return node
    return None
   

def distance(node1, node2):
    return math.hypot((node2.longitude - node1.longitude), (node2.latitude - node1.latitude))
     

def shortest_path(nodes, edges, weight, s, t):
 

    infinity = 1.7976931348623157e+308
    dist = [infinity] * len(nodes)

    previous = [None] * len(nodes)
    heap = heap_id.heap_id()
    # print nodes.index(s)
    dist[nodes.index(s)] = 0
    
        
    for i in range(len(dist)):
        heap.insert(dist[i])
    u = heap.extract_min_with_id()
    #print u[1]-1
    while heap.heapsize > 0:
        index_ID = u[1]-1
        # print index_ID
        if dist[index_ID] == infinity:
            return []
        for edge in edges:
            if edge.begin ==nodes[index_ID] or edge.end == nodes[index_ID]:
                if edge.begin == nodes[index_ID]:
                    begin=edge.begin
                    end=edge.end
                if edge.end == nodes[index_ID]:
                    begin = edge.end
                    end = edge.begin
                alt = u[0] + weight(begin, end)
                index_end = nodes.index(end)
                if alt < dist[index_end]:
                    dist[index_end] = alt
                    #print index_end
                    
                    heap.decrease_key_using_id(index_end+1, alt)
                    previous[index_end] = nodes[index_ID]
                    #print nodes[index_ID]
            if nodes[index_ID] == t:
                path = [t]
                p = previous[nodes.index(t)]
                while p != s:
                    path.append(p)
                    p = previous[nodes.index(p)]       
                path.append(s)
                path.reverse()
                return path
        u = heap.extract_min_with_id()
        
    

