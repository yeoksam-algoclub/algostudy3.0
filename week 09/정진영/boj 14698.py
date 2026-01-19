import sys
from heapq import heapify, heappush, heappop

input = sys.stdin.readline

t = int(input())
d = 1000000007

for _ in range(t):
    n = int(input())
    
    hq = list(map(int, input().split()))
    heapify(hq)
    
    if n == 1 :
        print(1)
        continue
        
    total = 1
    while len(hq) > 1:
        a = heappop(hq)
        b = heappop(hq)
        r = a * b 
        total *= r
        total %= d
        heappush(hq, r)
    
    print(total)