n, m = map(int, input().split())
arr = []
for _ in range(n):
    inp = list(map(int, input().split()))
    arr.append(inp)

prefix = [[0] * (m+1) for _ in range(n+1)]
for i in range(1, n+1):
    for j in range(1, m+1):
        prefix[i][j] = arr[i-1][j-1] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1]

res = -10000
for i in range(n):# 작은쪽
    for j in range(m):
        for k in range(i+1, n+1):# 큰쪽
            for l in range(j+1, m+1):
                res = max(res, prefix[k][l] - prefix[k][j] - prefix[i][l] + prefix[i][j])
print(res)