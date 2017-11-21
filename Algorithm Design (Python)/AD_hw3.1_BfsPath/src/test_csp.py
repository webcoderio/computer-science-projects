#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

import unittest
#import bfs
import count_shortest_paths
#import sys
#import time

class TestBFS(unittest.TestCase):

    def edges_cc(self,t):
        # this is a graph with 2t vertices and 4(t-1) edges
        # edges are (1,3),(1,4),(2,3),(2,4) and so on
        edges = [ (2*i-1,2*i+1) for i in range(1,t) ]
        edges.extend( [ (2*i-1,2*i+2) for i in range(1,t) ] )
        edges.extend( [ (2*i,2*i+1) for i in range(1,t) ] )
        edges.extend( [ (2*i,2*i+2) for i in range(1,t) ] )
        return edges

    def edges_d(self,t):
        edges = [ (1,i) for i in range(2,t) ]
        edges.extend ( [ (i,t) for i in range(2,t) ] )
        return edges

    def test2(self):
        """test count_shortest_paths"""

        linegraph = [ (i,i+1) for i in range(1,10) ]
        linegraph.extend( [ (i,i+10) for i in range(1,10) ])
        self.assertEqual(count_shortest_paths.count_shortest_paths(20,linegraph,1,19),1)

        self.assertEqual(count_shortest_paths.count_shortest_paths(20, self.edges_cc(10), 1, 19), 256)
        self.assertEqual(count_shortest_paths.count_shortest_paths(20, self.edges_cc(10), 1, 20), 256)

        self.assertEqual(count_shortest_paths.count_shortest_paths(40, self.edges_cc(20), 1, 40), 2**18)
        self.assertEqual(count_shortest_paths.count_shortest_paths(1000, self.edges_cc(500), 1, 1000), 2**498)


        self.assertEqual(count_shortest_paths.count_shortest_paths(10, self.edges_d(10), 1, 10), 8)
        self.assertEqual(count_shortest_paths.count_shortest_paths(20000, self.edges_d(20000), 1, 20000), 19998)

if __name__ == '__main__':
    unittest.main(argv = unittest.sys.argv + ['--verbose'])
