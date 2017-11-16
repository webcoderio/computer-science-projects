#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

import unittest
import sys
import time
import cooking

class TestCooking(unittest.TestCase):

    def test1_cooking(self):
        self.assertEqual(cooking.cooking([[1],[2],[3]]), True)
        self.assertEqual(cooking.cooking([[],[],[]]), True)
        self.assertEqual(cooking.cooking([[1],[2],[1,2,3]]), False)
        self.assertEqual(cooking.cooking([[1,3],[2],[1,3]]), False)
        self.assertEqual(cooking.cooking([[1,2,3],[1,2],[1],[2,3,4]]), True)
        self.assertEqual(cooking.cooking([[1,2,3],[1,2],[1],[1]]), False)
        self.assertEqual(cooking.cooking([[2,3,4],[1],[3],[2,3,4]]), False)

if __name__ == '__main__':
    unittest.main(argv = unittest.sys.argv + ['--verbose'])
