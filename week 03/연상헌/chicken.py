from itertools import combinations

n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]

home = []
chicken = []

for i in range(n):
    for j in range(n):
        if arr[i][j] == 1:
            home.append((i, j))
        elif arr[i][j] == 2:
            chicken.append((i, j))

answer = int(1e9)

if len(chicken) <= m:
    total = 0
    for hx, hy in home:
        dist = int(1e9)
        for cx, cy in chicken:
            dist = min(dist, abs(hx - cx) + abs(hy - cy))
        total += dist
    answer = total
else:
    for comb in combinations(chicken, m):
        total = 0
        for hx, hy in home:
            dist = int(1e9)
            for cx, cy in comb:
                dist = min(dist, abs(hx - cx) + abs(hy - cy))
            total += dist
        answer = min(answer, total)

print(answer)
