#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

import unittest
import bfs
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
        edges = [ (1,i) for i in range(1,t) ]
        edges.extend ( [ (1,t) for i in range(1,t) ] )
        return edges

    def test1(self):
        """test bfs"""
        self.assertEqual((1,2) in bfs.bfs(3,[(1,2),(1,3),(1,2)],1), True)
        self.assertEqual((1,3) in bfs.bfs(3,[(1,2),(1,3),(1,2)],1), True)
        self.assertEqual((2,3) in bfs.bfs(3,[(1,2),(1,3),(1,2)],1), False)
        self.assertEqual((1,4) in bfs.bfs(4,[(1,2),(1,3),(1,2),(3,4)],1), False)
        self.assertEqual((3,4) in bfs.bfs(4,[(1,2),(1,3),(1,2),(3,4)],1), True)
        self.assertEqual((1,6) in bfs.bfs(8,self.edges_cc(4),1), False)
        self.assertEqual((5,6) in bfs.bfs(6,[(1,2),(1,3),(1,2),(3,4),(5,6)],1), False)

        linegraph = [ (i,i+1) for i in range(1,10) ]
        self.assertEqual(linegraph, bfs.bfs(20,linegraph,1))

        linegraph.extend( [ (i,i+10) for i in range(1,10) ])
        self.assertEqual(set(linegraph), set(bfs.bfs(20,linegraph,1)))

    def test2(self):
        edges_tb_ex = [(1,2),(1,3),(2,3),(2,4),(2,5),(3,5),(3,7),(3,8),(5,6),(9,10),(11,12),(12,13)]
        self.assertEqual(bfs.bfs_path(13, edges_tb_ex, 1,7),[1,3,7])

if __name__ == '__main__':
    unittest.main(argv = unittest.sys.argv + ['--verbose'])
