#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

from maxsums3 import maxsums_dp
import unittest
import time


class TestMaxsums(unittest.TestCase):

    def test1(self):
        start_time = time.time()
        ans = maxsums_dp([])
        self.assertEqual(ans, 0)

        ans = maxsums_dp([-1,-2])
        self.assertEqual(ans, 0)

        ans = maxsums_dp([-1,3,2,-7,4,2,-2,3,-1])
        self.assertEqual(ans, 7)

        ans = maxsums_dp([-1,4,-1,3])
        self.assertEqual(ans, 6)

        end_time = time.time()
        print 'Time:', end_time - start_time, 'seconds'

    def test2(self):
        start_time = time.time()

        ans = maxsums_dp([1]*1000)
        self.assertEqual(ans, 1000)

        ans = maxsums_dp([-2,1]*1000)
        self.assertEqual(ans, 1)

        end_time = time.time()
        print 'Time:', end_time - start_time, 'seconds'

    def test3(self):
        start_time = time.time()

        ans = maxsums_dp([1]*2000)
        self.assertEqual(ans, 2000)

        ans = maxsums_dp([-2,1]*2000)
        self.assertEqual(ans, 1)

        end_time = time.time()
        print 'Time:', end_time - start_time, 'seconds'

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestMaxsums)
    unittest.TextTestRunner(verbosity=2).run(suite)
