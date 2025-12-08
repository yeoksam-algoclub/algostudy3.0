n, m = map(int, input().split())
arr = [int(input()) for _ in range(n)]
arr.sort()
a = 0
b = arr[-1] * m

ans = b

while a <= b:
    mid = (a + b) // 2
    
    people = 0
    for i in arr:
        people += mid // i
        if people >= m:
            break

    if people >= m:
        ans = mid
        b = mid - 1
    else:
        a = mid + 1
print(ans)