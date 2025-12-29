import sys
input = sys.stdin.readline


N = int(input())
def check(number):
    if number % 2 == 0  or number % 5 == 0:
        return 0
    else:
        for i in range(3,1+number//2,2):
            if number%i==0:
                return 0
        return 1

def dfs(num,idx):
    if idx == N:
        print(num)
        return
    else:
        for p in (1,3,7,9):
            temp_num = num*10 + p
            if check(temp_num) == 1:
                dfs(temp_num,idx+1)
for i in [2,3,5,7]:
    dfs(i,1)