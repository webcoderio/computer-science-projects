#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
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
        t = [[999,i] for i in range(1,1001)]
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
        x = range(2,2002,2)
        self.assertEqual(timestamp.timestamp_matching(t,x), True)
        end_time = time.time()
        print 'Time:', end_time - start_time, 'seconds'

        start_time = time.time()
        t = [[1000,i] for i in range(1,1001)]
        x = range(1,1000)+[2001]
        self.assertEqual(timestamp.timestamp_matching(t,x), False)
        end_time = time.time()
        print 'Time:', end_time - start_time, 'seconds'

    def test3(self):

        ## false because two competing for same slot
        ans = timestamp.timestamp_matching([[2,2],[3,1]],[0.5,1])
        self.assertEqual(ans, False)

        ## false because first slot is useless
        ans = timestamp.timestamp_matching([[1,1],[4,1],[5,1],[6,1]],[3,4,5,6])
        self.assertEqual(ans, False)

        ans = timestamp.timestamp_matching([[1,1],[1.5,0.5],[4,1],[5,1],[6,1]],[1,3,4,5,6])
        self.assertEqual(ans, False)


    def test4(self):
        ## smallest e_i first is bad
        ans = timestamp.timestamp_matching([[2,2],[4,1]],[3,5])
        self.assertEqual(ans, True)

        ## largest e_i first is bad
        ## smallest x_i first also bad
        ## earliest start time x_i-e_i first also bad
        ans = timestamp.timestamp_matching([[3,3],[4,1]],[3,6])
        self.assertEqual(ans, True)

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestTimestamp)
    unittest.TextTestRunner(verbosity=2).run(suite)
