#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

import unittest
import dijkstra
import nhpn
#import sys

class TestDijkstra(unittest.TestCase):

    def setUp(self):
        loader = nhpn.Loader() # Loading the data takes a few
# seconds. This statement can be moved
# to just below "import nhpn" if you
# don't modify nodes or links in your
# code.
        self.nodes = loader.nodes()
        self.links = loader.links()

    def test1(self):
        """Part (a): node_by_name"""
        ans = dijkstra.node_by_name(self.nodes, 'CAMBRIDGE', 'MA')
        self.assertEqual(ans.state, 'MA')
        self.assertEqual('NORTH CAMBRIDGE', ans.description)

    def test2(self):
        """Part (b): distance"""
        ans = dijkstra.distance(self.nodes[0], self.nodes[1])
        self.assertAlmostEqual(105747.58, ans, 2)

    def testAL(self):
        """Part (c): short path"""
        source = dijkstra.node_by_name(self.nodes, 'BRIDGEPORT', 'AL')
        destination = dijkstra.node_by_name(self.nodes, 'STEVENSON', 'AL')
        ans = dijkstra.shortest_path(self.nodes, 
                                     self.links,
                                     dijkstra.distance,
                                     source,
                                     destination)
        self.assertEqual(3, len(ans))
        self.verifyPath(ans, self.links, source, destination)

    def testLonger(self):
        """Part (c): long path"""
        source = self.nodes[0]
        destination = self.nodes[100]
        ans = dijkstra.shortest_path(self.nodes,
                                     self.links,
                                     dijkstra.distance,
                                     source,
                                     destination)
        self.verifyPath(ans, self.links, source, destination)
        self.assertAlmostEqual(2076299,
                               self.sumPath(ans, dijkstra.distance),
                               0)

    def verifyPath(self, path, edges, source, destination):
        """Verify that path is a valid path from source to destination
        (it's valid if it uses only edges in the edge set)."""
        self.assertEqual(source, path[0])
        self.assertEqual(destination, path[-1])
        # Put edges in a set for faster lookup.
        edge_set = set()
        for edge in edges:
            edge_set.add( (edge.begin, edge.end) )
        for i in range(len(path)-1):
            if (path[i], path[i+1]) not in edge_set and \
               (path[i+1], path[i]) not in edge_set:
                self.fail('Adjacent nodes in path have no edge between them')

    def sumPath(self, path, weight):
        """Compute the sum of weights along a path.  Requires path to
        be valid (see verifyPath)."""
        sum = 0
        for i in range(len(path)-1):
            sum += weight(path[i], path[i+1])
        return sum

if __name__ == '__main__':
    unittest.main(argv = unittest.sys.argv + ['--verbose'])