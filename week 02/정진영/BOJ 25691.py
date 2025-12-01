import sys
input = sys.stdin.readline

#노드 개수 n 최대 이동 횟수  k
n, k = map(int, input().split())

nodes = [[] for _ in range (n)]
for _ in range(n-1):
    p, c = map(int, input().split())
    nodes[p].append(c)
    nodes[c].append(p)

apples = list(map(int,input().split()))

# 0에서 시작 최대 개수 방문 
# DFS + DP 

# [i]번 노드에서 자신 포함 [j]개의 노드를 진행할 때
dp = [[-float('inf')] * (k+1) for _ in range(n)]

# 현재 i번째 노드에서 확인한 개수
check = [0] * (n)    

# 역으로 DP 병합하기
# c -> p
def dfs(c, p) :
    dp[c][1] = apples[c] # 자기자신만 포함했을 때 
    check[c] = 1    
    for v in nodes[c]: 
        if v == p :
            continue
        dfs(v, c)
        
        # dp[c] dp[v] 비교 병합
        for x in range(check[c], 0, -1):
            if dp[c][x] < 0 :
                continue
            # 자식 노드에서 연결할 수 있는 경우 확인
            for y in range(1, check[v]+1) :
                # 더 연결 할 수 없다면 (이미 최대 방문 횟수 초과)
                if x + y > k:
                    break
                dp[c][x+y] = max(dp[c][x+y] , dp[c][x]+ dp[v][y])
        
        # 최대 방문 횟수 갱신
        check[c] += check[v]
        if check[c] > k :
            check[c] = k

dfs(0, -1)
print(max(dp[0]))