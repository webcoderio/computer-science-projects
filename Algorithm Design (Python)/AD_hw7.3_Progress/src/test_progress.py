#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

import unittest
import sys
import progress

class TestProgress(unittest.TestCase):

    def test1_small(self):
        """small tests"""
        self.progress_test([], 0)
        self.progress_test([1], 1)
        self.progress_test([1, 2, 3], 3)
        self.progress_test([1, 3, 2], 2)

    def test2_large(self):
        """large tests"""
        self.progress_test([32, 31, 46, 36, 32, 36, 30, 33, 22, 38, 2, 13], 4)
        long_sequence = [855, 1790, 9885, 8034, 4833, 4969, 6313, 8333, 5095, 1981, 3702, 9708, 603, 5249, 2536, 3576, 93, 1403, 9371, 4809, 2970, 8814, 4180, 8764, 7112, 5157, 736, 1268, 2234, 7720, 3136, 1228, 4592, 7136, 6586, 9753, 321, 4638, 1287, 9112, 5962, 608, 7143, 9563, 8683, 2521, 4031, 7864, 9536, 1250, 6241, 6578, 3204, 6127, 4243, 1848, 3459, 2166, 472, 3101, 1661, 2416, 4421, 6907, 3185, 410, 5346, 19, 8972, 7927, 5880, 8452, 6349, 3806, 1545, 569, 9342, 9975, 7687, 4218, 8354, 8141, 6972, 3587, 332, 3760, 7427, 1507, 6489, 7673, 7617, 9191, 6804, 8821, 3154, 3426, 4834, 9211, 367, 9384]
        self.progress_test(long_sequence, 18)
        long_sequence = [855, 1790, 9885, 8034, 4833, 4969, 6313, 8333, 5095, 1981, 3702, 9708, 603, 5249, 2536, 3576, 93, 1403, 9371, 4809, 2970, 8814, 4180, 8764, 7112, 5157, 736, 1268, 2234, 7720, 3136, 1228, 4592, 7136, 6586, 9753, 321, 4638, 1287, 9112, 5962, 608, 7143, 9563, 8683, 2521, 4031, 7864, 9536, 1250, 6241, 6578, 3204, 6127, 4243, 1848, 3459, 2166, 472, 3101, 1661, 2416, 4421, 6907, 3185, 410, 5346, 19, 8972, 7927, 5880, 8452, 6349, 3806, 1545, 569, 9342, 9975, 7687, 4218, 8354, 8141, 6972, 3587, 332, 3760, 7427, 1507, 6489, 7673, 7617, 9191, 6804, 8821, 3154, 3426, 4834, 9211, 367, 9384]
        self.progress_test(long_sequence*99, 100)
        

    def progress_test(self, scores, answer_size):
        subsequence = progress.longest_increasing_subsequence(scores)
        self.assertEqual(answer_size, len(subsequence), 'result is wrong size')
        for score in scores:
            if len(subsequence)==0: return
            if score==subsequence[0]: del subsequence[0] # Could take time.
        self.assertEqual(0, len(subsequence), 'result is not a subsequence')



if __name__ == '__main__':
    unittest.main(argv = unittest.sys.argv + ['--verbose'])
