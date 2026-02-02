from collections import deque
import sys
input = sys.stdin.readline

K = int(input())
W, H = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(H)]

v = [[[0]*(K+1) for _ in range(W)] for _ in range(H)]
q = deque()
q.append((0, 0, K, 0))
v[0][0][K] = 1
ans = -1
while q:
    i, j, k, cnt = q.popleft()
    if i == H-1 and j == W-1:
        ans = cnt
        break

    #원숭이 점프
    for di, dj in ((1,0),(0,1),(-1,0),(0,-1)):
        ni, nj = i+di, j+dj
        if 0 <= ni < H and 0 <= nj < W and arr[ni][nj] == 0:
            if not v[ni][nj][k]:
                v[ni][nj][k] = 1
                q.append((ni, nj, k, cnt+1))

    #말 점프
    if k > 0:
        for di, dj in ((-2,1),(-1,2),(1,2),(2,1),(-2,-1),(-1,-2),(1,-2),(2,-1)):
            ni, nj = i+di, j+dj
            if 0 <= ni < H and 0 <= nj < W and arr[ni][nj] == 0:
                if not v[ni][nj][k-1]:
                    v[ni][nj][k-1] = 1
                    q.append((ni, nj, k-1, cnt+1))

print(ans)
