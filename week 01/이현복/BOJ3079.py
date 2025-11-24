import sys
sys.stdin = open("testcase.txt")
input = sys.stdin.readline
N,M = map(int,input().split())  # 친구 M명, 심사대 N개
arr= list(int(input()) for _ in range(N))
arr.sort()

l,r = arr[0], arr[-1]*M
while l<=r:
    fin = 0
    mid = (l+r)//2
    for t in arr:
        tmp = mid // t
        fin += tmp
        if fin >=M:
            r = mid-1
            ans = mid
            break
    else:
        l = mid+1
print(ans)