#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

import subsequence
import unittest
import time

class TestSubsequence(unittest.TestCase):

    def test1(self):
        ans = subsequence.subsequence(['a'],['aa'])
        self.assertEqual(ans, False)

#        ans = subsequence.subsequence([],['aa'])
#        self.assertEqual(ans, True)

        ans = subsequence.subsequence(['a','b'],['a','a','b','a','b'])
        self.assertEqual(ans, True)

        ans = subsequence.subsequence(['a'],['A'])
        self.assertEqual(ans, False)

        ans = subsequence.subsequence(['a','b'],['a'])
        self.assertEqual(ans, False)

        ans = subsequence.subsequence(['buy Yahoo','buy eBay','buy Yahoo','buy Oracle'],
                                      ['buy Amazon','buy Yahoo','buy eBay','buy Yahoo','buy Yahoo','buy Oracle'])
        self.assertEqual(ans, True)

        ans = subsequence.subsequence(['buy Yahoo','buy eBay','buy Yahoo','buy Oracle'],
                                      ['buy Amazon','buy Yahoo','buy eBay','buy Yahoo','buy Yahoo'])
        self.assertEqual(ans, False)
    

    def test2(self):
        print "test"
        start_time = time.time()
        self.assertEqual(subsequence.subsequence(['a'] * 1000000,
                                                 ['b','a'] * 1000000), True)
        end_time = time.time()
        print 'Time1:', end_time - start_time, 'seconds'

        start_time = time.time()
        self.assertEqual(subsequence.subsequence(['a'] * 2000000,
                                                 ['b','a'] * 2000000), True)
        end_time = time.time()
        print 'Time2:', end_time - start_time, 'seconds'

        start_time = time.time()
        self.assertEqual(subsequence.subsequence(['a'] * 1000000,
                                                 ['b','c'] * 1000000), False)
        end_time = time.time()
        print 'Time3:', end_time - start_time, 'seconds'

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestSubsequence)
    unittest.TextTestRunner(verbosity=2).run(suite)
