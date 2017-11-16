#!/usr/bin/python

# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!

import timestamp
import unittest
import time

class TestTimestamp(unittest.TestCase):

    def test1(self):

        ans = timestamp.timestamp_matching([[1,1],[2,1]],[2,3])
        self.assertEqual(ans, True)

        ans = timestamp.timestamp_matching([[1,1],[2,1]],[2,5])
        self.assertEqual(ans, False)

        ans = timestamp.timestamp_matching([[3,3],[3,1]],[1,3])
        self.assertEqual(ans, True)

    def test2(self):
        
        start_time = time.time()
        t = [[9999,i] for i in range(1,10000)]
        x = range(1,1001)
        self.assertEqual(timestamp.timestamp_matching(t,x), True)
        end_time = time.time()
        print 'Time:', end_time - start_time, 'seconds'

        start_time = time.time()
        t = [[1999,i] for i in range(1,2001)]
        x = range(1,2001)
        self.assertEqual(timestamp.timestamp_matching(t,x), True)
        end_time = time.time()
        print 'Time:', end_time - start_time, 'seconds'

        start_time = time.time()
        t = [[1000,i] for i in range(1,1001)]
        x = range(2,1002,2)
        self.assertEqual(timestamp.timestamp_matching(t,x), True)
        end_time = time.time()
        print 'Time:', end_time - start_time, 'seconds'

        start_time = time.time()
        t = [[1000,i] for i in range(1,1001)]
        x = range(1,1000)+[2001]
        self.assertEqual(timestamp.timestamp_matching(t,x), False)
        end_time = time.time()
        print 'Time:', end_time - start_time, 'seconds'


if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestTimestamp)
    unittest.TextTestRunner(verbosity=2).run(suite)