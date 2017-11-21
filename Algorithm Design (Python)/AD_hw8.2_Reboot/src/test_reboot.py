#!/usr/bin/python

# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

import unittest
import sys
import time
import reboot

class TestReboot(unittest.TestCase):

    def test1_reboot(self):
        self.assertEqual(reboot.reboot([10,1,7,7],[8,4,2,1]), 19)
        self.assertEqual(reboot.reboot(
                [9,1,7,4,1,9,9,9],
                [8,4,2,1,1,1,1,1]),
                         35)

    def test2_reboot(self):
        self.assertEqual(reboot.reboot2([10,1,7,7],[8,4,2,1]), [2])
        self.assertEqual(reboot.reboot2(
                [9,1,7,4,1,9,9,9],
                [8,4,2,1,1,1,1,1]),
                         [2,5,7])

if __name__ == '__main__':
    unittest.main(argv = unittest.sys.argv + ['--verbose'])
