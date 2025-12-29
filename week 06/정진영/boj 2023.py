import sys

sys.stdin = open("input.txt","r")
input = sys.stdin.readline

# n 자리 수 
n = int(input())

# 시작 수 2, 3, 5, 7
def is_Prime(num) : 
    for a in range(2, int(num**0.5) + 1):
        if (int(num) % a == 0 ):
             return False
    return True

arr = [1, 3, 5, 7]
def dfs(num) :
    if (len(str(num)) == n ):
        print(num)
        return
    for i in range(1,10) :
        temp = 10*num + i 
        if (is_Prime(temp) == True ):
            dfs(temp)
    
    
dfs(2)
dfs(3)
dfs(5)
dfs(7)