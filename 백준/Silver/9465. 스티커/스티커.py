#테스트 케이스 개수 T
T = int(input())

#테스트 케이스 첫째줄 n
for _ in range(T):
    #배열
    sticker = []
    n = int(input())
    #n개의 정수 -> 해당 위치의 스티커 점수 
    #연속하는 두 정수 사이에는 빈칸이 하나 있다....?
    for i in range(2):
        sticker.append(list(map(int, input().split())))
    for j in range(1, n):
        if j==1:
            sticker[0][j]+=sticker[1][j-1]
            sticker[1][j]+=sticker[0][j-1]
        else:
            sticker[0][j]+=max(sticker[1][j-1], sticker[1][j-2])
            sticker[1][j]+=max(sticker[0][j-1], sticker[0][j-2])
    print(max(sticker[0][n-1], sticker[1][n-1]))