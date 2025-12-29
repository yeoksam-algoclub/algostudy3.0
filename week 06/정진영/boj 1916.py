import sys
from heapq import heappush, heappop

sys.stdin = open("input.txt","r")
input = sys.stdin.readline

# n 개의 도시, m 개의 버스
n = int(input())
m = int(input())

# 버스 정보 초기화
bus_info = [[] for _ in range(n+1)]

for _ in range(m) :
    s, e, c = map(int, input().split())
    bus_info[s].append((e,c))

# 출발점 ~ 도착점
s,e = map(int, input().split())


# 우선순위큐 + 다익스트라

hq = []
arr = [float("inf")] * (n+1) # 도시 노드별 최소 비용 저장

heappush(hq, (s, 0)) # 현재 노드, 현재 코스트 저장

while hq : 
    now_node, now_cost = heappop(hq)
    # 현재 비용이 더 크다면 continue
    if arr[now_node] < now_cost : 
        continue
    for next_node, next_cost in bus_info[now_node]:
        check = now_cost + next_cost
        # 다음 노드까지 가는 비용 비교
        # 더 작다면 이동 
        if arr[next_node] > check : 
            arr[next_node] = check 
            heappush(hq,(next_node, check))

print(arr[e])
