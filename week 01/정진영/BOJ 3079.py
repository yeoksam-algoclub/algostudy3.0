import sys

input = sys.stdin.readline

N, M = map(int, input().split())
times = [int(input()) for _ in range(N)]
# 오름차순 정렬
times.sort()

# 총 심사 시간 기준으로 이분탐색 

left = 0
# 가장 많이 걸리는 경우 : 최소 시간 * M 
right = min(times) * M

while left < right :
    mid = (left + right) // 2
    # 각 심사대 별로 수용 가능한 인원 확인 
    total = sum([mid//time for time in times])
    
    # 인원을 수용하지 못하면
    if total < M :
        left = mid +1
    else : 
        right = mid

print(right)