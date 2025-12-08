n = int(input())
arr = list(map(int, input().split()))
a = 0
b = n - 1
val = abs(arr[0] + arr[-1])
aa = 0
bb = n - 1
while a < b:
    if abs(arr[a] + arr[b]) < val:
        aa = a
        bb = b
        val = abs(arr[a] + arr[b])
    if arr[a] + arr[b] > 0:
        b -= 1
    elif arr[a] + arr[b] < 0:
        a += 1
    elif arr[a] + arr[b] == 0:
        aa = a
        bb = b
        break
print(arr[aa], arr[bb])