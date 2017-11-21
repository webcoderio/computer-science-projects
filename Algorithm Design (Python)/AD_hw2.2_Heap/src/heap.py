# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!
def parent(i):
    return i/2

def left(i):
    return 2*i

def right(i):
    return 2*i+1

class heap:
    """
    Python implementation of minheap, using 1-based indexing.
    """

    def __init__(self):
        self.A = [None] # To make the heap 1-based, None is stuck in slot 0.
        self.heapsize = 0
        
    def __getitem__(self, i):
        return self.A[i]

    def heapify_up(self, i):
        if i >= 1:
            j = parent(i)
            if self.A[i] < self.A[j]:
                self._swap(i, j)
                self.heapify_up(j)

    def heapify_down(self, i):
        ## fill in the implementation of heapify_down
        if left(i) > self.heapsize:
            return None
        elif left(i) < self.heapsize:
            if self.A[left(i)] < self.A[right(i)]:
                j = left(i)
            else:
                j = right(i)
        elif left(i) == self.heapsize:
            j = left(i)
        if self.A[j] < self.A[i]:
            self._swap(j, i)
            self.heapify_down(j)
            

    def insert(self, v):
        """inserts the item v into the heap"""
        ### fix the following buggy implementation of insert
        self.heapsize += 1
        self.A.append(v)
        self.heapify_up(self.heapsize)

    def delete(self, i):
        """deletes the element in heap position i"""
        ### fix the following buggy implementation of delete
        if i == self.heapsize:
            self.A.pop()
        else:
            self.A[i] = self.A.pop()
        self.heapsize -= 1    
        self.heapify_down(i)
        
    def extract_min(self):
        if self.heapsize < 1:
            print "error: empty heap"
            return
        min = self.A[1]
        self.delete(1)
        return min
    
    def _swap(self, index1, index2):
        self.A[index1], self.A[index2] = self.A[index2], self.A[index1]


class positive_infinity:
    "bigger than any object other than itself"
    def __cmp__(self, other):
        if isinstance(other, self.__class__):
            return 0
        return 1

class negative_infinity:
    "smaller than any object other than itself"
    def __cmp__(self, other):
        if isinstance(other, self.__class__):
            return 0
        return - 1
