T = int(input())

for _ in range(T):
    n = int(input())
    arr = []
    for _ in range(n):
        arr.append(input())
    arr.sort()
    ok = True
    for i in range(n - 1):
        if arr[i+1].startswith(arr[i]):
            ok = False
            break
        
    print("YES" if ok else "NO")