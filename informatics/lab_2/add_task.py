:code(a: list) -> list :
    s1 = (a[0] + a[2] + a[4] + a[6]) % 2
    s2 = (a[1] + a[2] + a[5] + a[6]) % 2
    s3 = (a[3] + a[4] + a[5] + a[6]) % 2
    err = s1 + s2 * 2 + s3 * 4 - 1
    a[err] = int(not a[err])
    return a

a = [1, 1, 0, 0, 0, 0, 1]
print(decode(a))
