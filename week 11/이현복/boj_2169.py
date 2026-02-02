import sys
sys.stdin = open("testcase.txt")
input = sys.stdin.readline

N,M = map(int,input().split())
arr = [list(map(int,input().split())) for _ in range(N)]
ans = [[0]*M for _ in range(N)]
ans[0][0] = arr[0][0]
for i in range(1,M):
    ans[0][i] = arr[0][i] + ans[0][i-1]

for i in range(1,N):
    # 우측으로 이동은 좌측 끝에서 부터 체크
    # 좌측으로 이동은 우측 끝에서 부터 체크
    l,r = [0]*M,[0]*M                       # 좌, 우 DP 배열
    l[0] = ans[i-1][0] + arr[i][0]
    r[M-1] = ans[i - 1][M-1] + arr[i][M-1]
    for j in range(1,M):
        l[j] = max(ans[i-1][j],l[j-1])
        l[j]+=arr[i][j]
    for j in range(M-2,-1,-1):
        r[j] = max(ans[i-1][j],r[j+1])
        r[j]+=arr[i][j]
    for j in range(M):
        ans[i][j] = max(l[j],r[j])

print(ans[-1][-1])
