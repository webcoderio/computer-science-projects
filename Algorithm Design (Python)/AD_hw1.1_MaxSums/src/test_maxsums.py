#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

from maxsums1 import maxsums
from maxsums1 import maxsums_list
import unittest
import time


class TestMaxsums(unittest.TestCase):

    def test1(self):
        ans = maxsums([])
        self.assertEqual(ans, 0)

        ans = maxsums([-1,-2])
        self.assertEqual(ans, 0)

        ans = maxsums([-1,3,2,-7,4,2,-2,3,-1])
        self.assertEqual(ans, 7)

    def test2(self):
        ans = maxsums([-1,4,-1,3])
        self.assertEqual(ans, 6)

    def test3(self):

        start_time = time.time()
        self.assertEqual(maxsums_list([]),
                         [])
        self.assertEqual(maxsums_list([-1,-7]),
                         [])
        self.assertEqual(maxsums_list([-1,3,2,-7,4,2,-2,3,-1]),
                         [4,2,-2,3])
        self.assertEqual(maxsums_list([-1,4,-1,3]), [4,-1,3])
        end_time = time.time()
        print ('Time:', end_time - start_time, 'seconds')


if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestMaxsums)
    unittest.TextTestRunner(verbosity=2).run(suite)
