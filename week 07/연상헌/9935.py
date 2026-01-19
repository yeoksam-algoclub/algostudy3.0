word = input()
boom = input()
q = []
n = len(boom)

for i in word:
    q.append(i)
    while q and "".join(q[-n:]) == boom:
        for _ in range(n):
            q.pop()
if q:
    print("".join(q))
else:
    print("FRULA")