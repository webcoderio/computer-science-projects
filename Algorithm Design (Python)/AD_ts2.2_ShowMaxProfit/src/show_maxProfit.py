# Author: Andy Ng <andy@pcinvent.com>
# PCinvent.com All Rights Reserved
# Please do not delete the credit above!
def show_maxProfit():
    p = [9,1,5]
    n=len(p)
    if len(p) <=1:
        print 0
        return 0
    
    m_income = 0
    buy = 1
    sell = 1
    for i in range(1,n+1):
        m_min = p[i]
        m_max = p[i]
        for j in range(i,n+1):
            if m_max < p[j] :
                m_max = p[j]
                sell = j

        if m_max > m_min:
            m_income_tmp = m_max - m_min

        else:
            m_income_tmp = 0
        if m_income_tmp > m_income:
            m_income = m_income_tmp
            buy = i

        if (buy!=sell):
            print "buy in " + buy
            print "sell in " + sell
            print "total earn " + m_income
            return m_income
        else:
            print 0
            
        return 0

show_maxProfit()