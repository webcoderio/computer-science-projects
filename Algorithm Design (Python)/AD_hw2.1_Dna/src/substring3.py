# Author: Andy Ng <admin@webcoder.io>
# Webcoder.io All Rights Reserved
# Please do not delete the credit above!
""" (a) O(n^4)
    (b) O(n)
"""

def longest_substring(s, t):
    """Finds the longest substring that occurs in both s and t"""
    result = ''
    minLength = 0
    maxLength = min(len(s), len(t))
    while minLength <= maxLength: 
        mid = (minLength + maxLength)/2
        ans = k_substring(s, t, mid)
        if ans is None:
            maxLength = mid-1
        else:
            minLength = mid+1
            result = ans
    return result


def k_substring(s, t, k):
    """Finds a substring of length k in both s and t if there is one,
    and returns it. Otherwise, returns None."""
    substr_set = set() # set for storage
    
    for s_start in range(len(s)-k+1):
        current = s[s_start : s_start+k]
        substr_set.add(current)
    
    # find substring between s and k
    for t_start in range(len(t)-k+1):
        current = t[t_start : t_start+k]
        if current in substr_set:
            return current
    return None
