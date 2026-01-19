import heapq

INF = float('inf')
n = int(input())    # 도시개수
m = int(input())    # 버스개수
arr = list([] for _ in range(n + 1))

for _ in range(m):
    a,b,c = map(int, input().split())
    arr[a].append((b, c))
s, e = map(int, input().split())
dist = [INF] * (n + 1)
dist[s] = 0

pq = []
heapq.heappush(pq, (0, s))

while pq:
    now, u = heapq.heappop(pq)

    if now > dist[u]:
        continue

    for v, weight in arr[u]:
        new = now + weight
        if new < dist[v]:
            dist[v] = new
            heapq.heappush(pq, (new, v))
print(dist[e])