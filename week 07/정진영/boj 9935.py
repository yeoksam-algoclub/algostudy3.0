import sys

sys.stdin = open("input.txt","r")
input = sys.stdin.readline

s = input().rstrip()
boom = input()
stack = []
for i in range(len(s)):
    stack.append(s[i])
    if (len(stack) < len(boom)) : continue 
    while (len(stack) >= len(boom) and "".join(stack[-len(boom):] )== boom) : 
        cnt = 0
        while cnt < len(boom) : 
            stack.pop()
            cnt +=1 
if (len(stack)>0) :
    print("".join(stack))
else: 
    print("FRULA")