# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!
import heap_id
import math

def node_by_name(nodes, city, state):
    nodes_length = len(nodes)
    i_count = 0
    #print(city)
    while i_count < nodes_length:
        if nodes[i_count].state == state:
            if (nodes[i_count].description).count(city) > 0:
                #print nodes[i_count].description
                return nodes[i_count]
        i_count += 1
    return False # in case couldn't find

def distance(node1, node2):
    # distance = SquareRoot( (x2 - x1)^2 + (y2 - y1)^2 )
    x1 = node1.longitude
    y1 = node1.latitude
    x2 = node2.longitude
    y2 = node2.latitude
    #print(x2,x1,y2,y1)
    #distance = math.sqrt( (x2-x1)^2 + (y2-y1)^2 )
    # In python ^ 2 is express as ** 2
    distance = math.sqrt( (x2-x1)**2 + (y2-y1)**2 )
    return distance

def shortest_path(nodes, edges, weight, s, t):
    # http://docs.python.org/tutorial/datastructures.html
    """>>> tel = {'jack': 4098, 'sape': 4139}
    >>> tel['guido'] = 4127
    >>> tel
    {'sape': 4139, 'guido': 4127, 'jack': 4098}
    """
    # Create empty data storages of dictionaries
    adj_list = {}   # nodes and edges 
    node_to_id = {}  # node to id reference
    id_to_node = {} # id to node reference
    node_to_parent = {} # node's parent reference
    node_to_key = {} # node to heap key reference
    
    # heap_id object instance
    id_heap = heap_id.heap_id()
    
    for edge in edges:
        # beginning node to ending node
        if edge.begin in adj_list:
            adj_list[edge.begin].append(edge.end)
        else:                                
            adj_list[edge.begin] = [edge.end]
        # ending node to beginning node
        if edge.end in adj_list:
            adj_list[edge.end].append(edge.begin)
        else:
            adj_list[edge.end] = [edge.begin]
    
    # node to key reference dictionary
    for node in nodes:
        # parent node
        if node == s:
            id = id_heap.insert(0)
            node_to_key[node] = 0   # node_to key 0
            node_to_parent[node] = None # no parent
        # child node
        else:
            id = id_heap.insert(heap_id.positive_infinity) # hep_id positive_infinity()
            node_to_key[node] = heap_id.positive_infinity # node_to key to positive_infinity
        # new references
        node_to_id[node] = id            #update node_to_id dictionary
        id_to_node[id] = node            #update id_to_node dictionary
    
    # check what nodes still left
    while node_to_id:
        ext_min_node = id_heap.extract_min_with_id()    # extract min node use heap_id obj id_heap
        ext_min_node_node = id_to_node[ext_min_node[1]] # node
        ext_min_node_key = ext_min_node[0]  # key
        # delete id for the ext min node
        del node_to_id[ext_min_node_node]
        # has outgoing edges ?
        if ext_min_node_node in adj_list:
            for idx in adj_list[ext_min_node_node]: # loop thru the dictionary
                if ext_min_node_key != heap_id.positive_infinity: # has a connected path to
                    # weight priority queue
                    if node_to_key[idx] > ext_min_node_key + weight( ext_min_node_node, idx ):
                        node_to_key[idx] = ext_min_node_key + weight( ext_min_node_node, idx )
                        id_heap.decrease_key_using_id(node_to_id[idx], node_to_key[idx]) # decrease key
                        node_to_parent[idx] = ext_min_node_node # parent node

    # prepare for shortest path result
    result_list = []
    result_list.append(t) # put destination into result
    node = t # where t is destination
    
    # parent insertion
    while node_to_parent[node] is not None: # 'is not None' is not equal to '!='
        result_list.insert(0, node_to_parent[node]) # node
        node = node_to_parent[node] # parent
 
    return result_list