#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

import substring2 as substring # modify this line to test a different file
import unittest
import time

class TestSubstring(unittest.TestCase):
    def setUp(self):
        pass

    def test1(self):
        ans = substring.longest_substring('','')
        self.assertEqual(ans, '')

    def test2(self):
        ans = substring.longest_substring('aaa','bbb')
        self.assertEqual(ans, '')

    def test3(self):
        ans = substring.longest_substring('aaa','aaa')
        self.assertEqual(ans, 'aaa')

    def test4(self):
        ans = substring.longest_substring('the quick brown fox','THE QUICK BROWN FOX')
        self.assertEqual(ans, ' ')

    def testDNA100(self):
        self.dna_test(100, 4)

    def testDNA1000(self):
        self.dna_test(1000, 66)

#     def testDNA10000(self):
#         self.dna_test(10000, 393)

#     def testDNA100000(self):
#         self.dna_test(100000, 836)

    def dna_test(self, suffix, solution_length):
        """
        assumes chr2* files are in the current directory, containing
        DNA strings. Reads the files with the given suffix, runs
        longest_substring on them, and checks the length of the
        solution.
        """
        s_file = open('data/chr2_first_' + str(suffix))
        t_file = open('data/chr2a_first_' + str(suffix))
        s = s_file.readline()
        t = t_file.readline()

        start_time = time.time()
        ans = substring.longest_substring(s, t)
        end_time = time.time()

        self.assertEqual(len(ans), solution_length)
        print 'time for test', str(suffix) + ': ', end_time - start_time, 'seconds'
    

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestSubstring)
    unittest.TextTestRunner(verbosity=2).run(suite)
