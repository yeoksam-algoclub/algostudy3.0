import sys,pprint
sys.stdin = open("testcase.txt")
input = sys.stdin.readline

mod =987654321
n = int(input())
dp = [0]*(n+1)
dp[0] = 1
dp[2] = 1
for i in range(4,n+1,2):
    tmp = 0
    for k in range(0,n,2):
        if 1:
            tmp += (dp[k] * dp[i-k-2])
    dp[i] = tmp%mod
print(dp[-1])
