import sys
from collections import deque

input = sys.stdin.readline
# n 개의 동영상과 q개의 질문
n, q = map(int, input().split())

# i 번째와 연결된 노드 리스트 (번호, 가중치)
graph = [[] for _ in range(n+1)]

for _ in range(n-1):
    pi, qi, ri = map(int, input().split())
    graph[pi].append((qi, ri))
    graph[qi].append((pi, ri))

for i in range(1, n + 1):
    graph[i].sort(key=lambda x: (x[1], x[0]))

    
for _ in range(q):
    k, v = map(int, input().split())
    answer = 0
    
    arr = deque()
    # 순회할 비디오 번호랑 현재 최소 usado
    arr.append((v, float("inf")))
    
    visited = [False for _ in range(n+1)]
    while arr:
        v, u = arr.popleft()
        visited[v] = True
        
        for (nx, r) in graph[v]:
            if visited[nx]:
                continue
            now = min(u, r)
            if now >= k :
                answer +=1
                arr.append((nx, now))
                visited[nx] = True
    print(answer)
            

