# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!

def parent(i):
    return i/2

def left(i):
    return 2*i

def right(i):
    return 2*i+1

class heap_id:
    """
    Python implementation of minheap, using 1-based indexing.
    Adapted from CLRS.
    
    Augmented to include a layer of indirection useful for applictions
    like dijkstra's algorithm. insert(key) returns a unique ID. The
    same ID is also returned when extract_min_id() is called. ID can
    be used (instead of an index) to decrease_key, by calling
    decrease_key_id(ID, key)
    """
    
    def __init__(self):
        self.A = [None] # To make the heap 1-based, None is stuck in slot 0.
                        # Other elements of A will be of the form [key, ID].
        self.heapsize = 0
        self.ID_to_index = {}
        self._ID = 0
        
    def __getitem__(self, i):
        return self.A[i][0]

    def __setitem__(self, i, key):
        self.A[i][0] = key

    def min_heapify(self, i):
        l = left(i)
        r = right(i)
        smallest = i
        if l <= self.heapsize and self[l] < self[i]:
            smallest = l
        if r <= self.heapsize and self[r] < self[smallest]:
            smallest = r
        if smallest != i:
            self._swap(i, smallest)
            self.min_heapify(smallest)

    def decrease_key(self, i, key):
        """Decrease key given index."""
        if key >= self[i]:
            return
        self[i] = key
        while i > 1 and self[parent(i)] > self[i]:
            self._swap(i, parent(i))
            i = parent(i)

    def decrease_key_using_id(self, ID, key):
        """Decrease key given ID."""
        self.decrease_key(self.ID_to_index[ID], key)

    
    def insert(self, key):
        """Returns an ID that is associated with the item."""
        self.heapsize += 1
        ID = self._next_id()
        self.ID_to_index[ID] = self.heapsize
        self.A.append( [positive_infinity(), ID] )
        self.decrease_key(self.heapsize, key)
        return ID

    def extract_min(self):
        """Extracts min and returns key."""
        return self.extract_min_with_id()[0]

    def extract_min_with_id(self):
        """Extracts min and returns (key,ID) pair."""
        if self.heapsize < 1:
            print "error: empty heap"
            return
        self._swap(1, self.heapsize)
        self.heapsize -= 1
        min_pair = self.A.pop()
        del self.ID_to_index[min_pair[1]]
        self.min_heapify(1)
        return tuple(min_pair)
    
    def _swap(self, index1, index2):
        self.A[index1],self.A[index2] = self.A[index2],self.A[index1]
        ID1 = self.A[index1][1]
        ID2 = self.A[index2][1]
        self.ID_to_index[ID1],self.ID_to_index[ID2] = \
            self.ID_to_index[ID2],self.ID_to_index[ID1]
        
    def _next_id(self):
        self._ID += 1
        return self._ID

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
        return -1
