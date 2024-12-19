n = int(input())
t =[]
p =[]
total =[]

for i in range(n):
    a, b=map(int, input().split())
    t.append(a)
    p.append(b)
    total.append(b)
total.append(0)
for i in range(n-1, -1, -1):
    if t[i]+i>n:
        total[i]=total[i+1]
    else:
        total[i]=max(total[i+1], p[i]+total[i+t[i]])
print(total[0])