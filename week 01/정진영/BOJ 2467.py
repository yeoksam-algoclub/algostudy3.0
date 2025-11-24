import sys

input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().split())) # 오름차순 정렬되어 있음

check = float('inf') # 절댓값 비교 
# 정답 초기화
ans1 = 0 
ans2 = 0 

# 이분 탐색
left = 0
right = N-1

while left < right : 
    total = arr[left] + arr[right]
    
    # 0보다 가까우면 
    if abs(total) < check : 
         check = abs(total)
         ans1 = arr[left]
         ans2 = arr[right]
    
    # 0 이면 반복문 중지
    if total == 0 :
        break
    # 인덱스 이동
    if total > 0 :
        right -=1
    else : 
        left +=1 

print(ans1,ans2, sep=" ")