#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

import unittest
import sys
import time
import change

class TestChange(unittest.TestCase):

    def test1_small(self):
        """small tests"""
        self.change_test([1, 5, 10, 17], 0, 0)
        self.change_test([1, 5, 10, 17], 4, 4)
        self.change_test([1, 5, 10, 17], 5, 1)
        self.change_test([1, 5, 10, 17], 34, 2)
        self.change_test([1, 5, 10, 17], 33, 4)

    def test2_large(self):
        """large test"""
        denominations = [1, 2896, 9274, 8063, 1764, 5375, 4228, 7611, 1482, 816, 9335, 2582, 4672, 649, 1405, 5362, 1790, 4774, 9978, 3175, 6091, 1302, 9683, 728, 9819, 1934, 863, 1828, 2680, 8911, 3065, 1010, 182, 2167, 4443, 7111, 493, 262, 5091, 3337, 3956, 3646, 244, 161, 9966, 7784, 3105, 7470, 2688, 1894, 4561, 9924, 557, 8192, 7142, 4046, 8344, 6368, 7391, 7459, 395, 2680, 5844, 376, 183, 7622, 537, 5526, 371, 4769, 4498, 4452, 2727, 3929, 600, 2633, 8090, 3829, 4186, 135, 1847, 9421, 9110, 3429, 2633, 1423, 78, 4059, 9877, 2749, 7422, 5200, 9310, 49, 3456, 9770, 7729, 7724, 1175, 7743, 8492]
        C = 100000
        self.change_test(denominations, C, 11)

    def change_test(self, denominations, C, answer_size):
        coins = change.make_change(denominations, C)
        total = 0
        for coin in coins:
            self.assertTrue(coin in denominations)
            total += coin
        self.assertEqual(C, total)
        self.assertEqual(answer_size, len(coins))

if __name__ == '__main__':
    unittest.main(argv = unittest.sys.argv + ['--verbose'])
