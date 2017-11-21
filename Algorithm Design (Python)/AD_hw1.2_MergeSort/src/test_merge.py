#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

import unittest
import mergesort
#import sys
import time
#import timeit
#from math import log

class TestFib(unittest.TestCase):

    def test1_merge(self):
        """test merge"""
        self.assertEqual(mergesort.merge([2], [1]), [1,2]);
        self.assertEqual(mergesort.merge([2,4], [1,2]), [1,2,2,4]);
        self.assertEqual(mergesort.merge([3], [1,2,3,4,5]), [1,2,3,3,4,5]);
        self.assertEqual(mergesort.merge([1,2,3,4,5], [3]), [1,2,3,3,4,5]);

    def test2_sort(self):
        """test mergesort"""
        start_time = time.time()
        self.assertEqual(mergesort.mergesort([2,1]), [1,2]);
        list99 = range(1,100);
        list99.reverse();
        self.assertEqual(mergesort.mergetest(100), range(1,100));
        self.assertEqual(mergesort.mergetest(1000), range(1,1000));
        end_time = time.time()
        print('')
        print('time for small tests', end_time - start_time, 'seconds')

    def test3(self):
        print ('')
        for n in [1000, 2000, 4000]:
            start_time = time.time()
            mergesort.mergetest(n)
            end_time = time.time()
            print ('T(', n, '): ', end_time - start_time, 'seconds')

if __name__ == '__main__':
    unittest.main(argv = unittest.sys.argv + ['--verbose'])
