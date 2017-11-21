#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

import dijkstra
import nhpn
#from math import *

def flat_earth(filename = 'path_flat.kml'):
    """Test long path"""

    loader = nhpn.Loader()
    nodes = loader.nodes()
    links = loader.links()

    source = dijkstra.node_by_name(nodes, 'PASADENA', 'CA')
    destination = dijkstra.node_by_name(nodes, 'CAMBRIDGE', 'MA')
    ans = dijkstra.shortest_path(nodes,
                                 links,
                                 dijkstra.distance,
                                 source,
                                 destination)
    nhpn.Visualizer.toKML(ans, 'path_flat.kml')

def curved_earth(filename = 'path_curved.kml'):
    """Test long path"""

    loader = nhpn.Loader()
    nodes = loader.nodes()
    links = loader.links()

    def distance(node1, node2):
        """Returns the distance between node1 and node2, including the
        Earth's curvature."""
        
        """
        A = node1.latitude*pi/10**6/180
        B = node1.longitude*pi/10**6/180
        C = node2.latitude*pi/10**6/180
        D = node2.longitude*pi/10**6/180
        return acos(sin(A)*sin(C)+cos(A)*cos(C)*cos(B-D))
        """

    source = dijkstra.node_by_name(nodes, 'PASADENA', 'CA')
    destination = dijkstra.node_by_name(nodes, 'CAMBRIDGE', 'MA')
    ans = dijkstra.shortest_path(nodes,
                                 links,
                                 distance,
                                 source,
                                 destination)
    nhpn.Visualizer.toKML(ans, 'path_curved.kml')

if __name__ == '__main__':
    flat_earth()
    curved_earth()
