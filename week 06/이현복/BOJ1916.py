import sys,heapq
input = sys.stdin.readline

N = int(input())    #도시
M = int(input())    #버스

arr = [[] for _ in range(N)]
for _ in range(M):
    a,b,c = map(int,input().split())
    arr[a-1].append((b,c))
S,E = map(int,input().split())

length = [-1] * N
length[S-1] = 0
pq = [(0,S)]

while pq:
    cost, node = heapq.heappop(pq)
    if node == E:
        break
    else:
        for city,money in arr[node-1]:
            temp = cost + money
            if length[city-1] == -1 or length[city-1] > temp:
                length[city - 1] = temp
                heapq.heappush(pq,(temp,city))
print(length[E-1])
