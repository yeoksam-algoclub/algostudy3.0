MOD = 987654321
n = int(input())
dp = [0] * (10001)
dp[0] = 1
dp[2] = 1
dp[4] = 2
for i in range(6, n + 1, 2):    # 6, 8, 10, 12, ...
    res = 0
    # j = 0
    # while j <= i-2-j:
    #     if j < i-2-j:
    #         res += (dp[j]*dp[i-2-j]) * 2
    #     elif j == i-2-j:
    #         res += dp[j] * dp[j]
    #     j += 2
    for j in range(0, i+1, 2): # 0, 2, 4, 6,...
        if j < i-2-j:
            res += (dp[j]*dp[i-2-j]) * 2
        elif j == i-2-j:
            res += dp[j] * dp[j]
    dp[i] = res % MOD
print(dp[n])