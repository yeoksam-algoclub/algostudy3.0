import sys

sys.stdin = open("input.txt","r")
input = sys.stdin.readline

n, m = map(int, input().split())
matrix = [list(map(int,input().split())) for _ in range(n)]
# 행 누적합
sum = [[0] * (m+1) for _ in range(n)]

answer = -float("inf")
for i in range(n):
    for j in range(m):
        # 누적합 점화식
        sum[i][j+1] = sum[i][j] + matrix[i][j]

# 부분 행렬 조회
for left in range(m):
    for right in range(left+1, m+1):
        temp = 0
        for row in range(n):
            temp += sum[row][right] - sum[row][left]
            answer = max(answer, temp)
            # 음수면 끊어버리기
            if temp < 0:
                temp = 0
print(answer)


