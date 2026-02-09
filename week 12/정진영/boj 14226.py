# 이모티콘

import sys
from collections import deque

input = sys.stdin.readline

# 만들어야 하는 이모티콘 개수 
s = int(input())
check =[ [False] * 1001 for _ in range(1001)]
q = deque()
q.append((1,0,0)) # 화면, 클립보드, 현재 시간
check[1][0] = True

# bfs 조회
while q : 
    screen, clip, time = q.popleft()
    if screen == s:
        print(time)
        break
    # 1. 클립보드 복사
    if not check[screen][screen]:
        check[screen][screen] = True
        q.append((screen, screen, time+1))
    
    # 2. 붙여넣기
    if clip > 0 and screen+clip < 1001 and not check[screen+clip][clip] :
        check[screen+clip][clip] = True
        q.append((screen+clip, clip, time + 1))
    
    
    # 3. 화면에서 이모지 삭제
    if screen > 1 and not check[screen-1][clip] :
        check[screen-1][clip] = True
        q.append((screen-1,clip,time+1))
    
        
