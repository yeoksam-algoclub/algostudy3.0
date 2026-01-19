import sys

input = sys.stdin.readline

str = input().strip()
stack = [] 

for s in str:
    stack.append(s)
    if len(stack) < 4 :
        continue
    if "".join(stack[-4:]) == "PPAP":
        for _ in range(4):
            stack.pop()
        stack.append("P")
    
    
# 스택에 P 만 남아있어야 "PPAP"
if "".join(stack) == "P" :
    print("PPAP")
else : 
    print("NP")