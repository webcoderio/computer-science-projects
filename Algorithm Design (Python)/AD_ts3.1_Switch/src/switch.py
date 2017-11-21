# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!
def whichcities(M,Q, B):
   ## assume the lists Q and B are 1-indexed
   opt[0,"Queens"] = 0
   opt[0, "Brooklyn"] = 0

   for i in range(1, n+1):
		opt[i, "Queens"] = min( (opt[i-1,"Brooklyn"]+M), (opt[i-1,"Queens"]) ) + Qi
		opt[i, "Brooklyn"] =  min( (opt[i-1,"Brooklyn"]), (opt[i-1,"Queens"]+M) ) + Bi
   return min(opt[n,"Queens"],opt[n,"Brooklyn"])