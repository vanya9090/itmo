# Нега-двоичная
res = ""
value = 885

while True:
    rem = value % -10
    value = value // -10
    print(rem, value)
    if rem < 0:
        rem = rem + 10
        value = value + 1
    print(rem, value)
    res = str(rem) + res
    if value == 0:
        break

print(res)
