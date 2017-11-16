#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

import unittest
from switch import switch
import sys
import time

class TestSwitch(unittest.TestCase):

    def test1(self):
        self.assertEqual(
            switch([10,1,1,10], [5,1,20,20]), 50)

    def test2(self):
        self.assertEqual(
            switch([10,1]*10000, [1,10]*10000), 11*10000)

if __name__ == '__main__':
    unittest.main(argv = unittest.sys.argv + ['--verbose'])
