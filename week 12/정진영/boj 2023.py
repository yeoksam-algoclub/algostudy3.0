# 신기한 소수 
import sys

input = sys.stdin.readline
n = int(input())

# 소수 확인
def is_Prime(num):
    for a in range(2, int(num**0.5)+1) :
        if (num % a == 0):
            return False
    return True

# 숫자 확장하면서 소수 확인
def dfs(m) :
    # n 자리일 경우 return 
    if 10 ** (n-1) < m < 10**n:
        print(m)
        return
        
    for k in range(1,10) :
        next = m * 10 + k
        # 다음 숫자도 소수 이면 진행
        if is_Prime(next):
            dfs(next)
    return
    
dfs(2)
dfs(3)
dfs(5)
dfs(7)