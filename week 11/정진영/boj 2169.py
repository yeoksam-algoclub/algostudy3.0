import sys
input = sys.stdin.readline

n, m = map(int, input().split())
dp = [list(map(int, input().split())) for _ in range(n)]


# 첫번째 행 : 오른쪽만 이동 가능 
for j in range(1, m) :
    dp[0][j] += dp[0][j-1]

# 나머지 행 업데이트
for i in range(1, n):
    # 두 방향 임시 배열
    left_to_right = dp[i][:]
    right_to_left = dp[i][:]
    
    # 왼쪽에서 오른쪽으로 가는 경우 dp 업데이트
    left_to_right[0] += dp[i-1][0]
    for j in range(1, m):
        # 위에서 아래로 내려오는 경우 또는 왼쪽에서 오른쪽 
        left_to_right[j] += max(dp[i-1][j], left_to_right[j-1])
    
    # 오른쪽에서 왼쪽으로 가는 경우 dp 업데이트
    right_to_left[m-1] += dp[i-1][m-1]
    for j in range(m-2,-1,-1):
        right_to_left[j] += max(dp[i-1][j], right_to_left[j+1])
        
    # 두 방향 비교, 각 좌표값 최대값으로 업데이트
    for j in range(m):
        dp[i][j] = max(left_to_right[j], right_to_left[j])
    
print(dp[n-1][m-1])
