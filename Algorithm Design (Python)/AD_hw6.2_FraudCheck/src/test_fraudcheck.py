#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

import unittest
import sys
import time
from fraudcheck import fraudcheck

class TestFraudcheck(unittest.TestCase):

    def test1(self):
        self.assertEqual(fraudcheck([1,2,2]),True)
        self.assertEqual(fraudcheck([2,1,2]),True)
        self.assertEqual(fraudcheck([2,2,1]),True)
        self.assertEqual(fraudcheck([1,2,3]),False)

    def test2(self):
        self.assertEqual(fraudcheck(10000*[1]),True)
        self.assertEqual(fraudcheck(2000*[1,2,3]),False)

if __name__ == '__main__':
    unittest.main(argv = unittest.sys.argv + ['--verbose'])
