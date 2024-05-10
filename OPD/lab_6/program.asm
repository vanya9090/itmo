            org 0x0;

v0:         word $default, 0x180;
v1:         word $int1, 0x180;
v2:         word $int2, 0x180;
v3:         word $default, 0x180;
v4:         word $default, 0x180;
v5:         word $default, 0x180;
v6:         word $default, 0x180;
v7:         word $default, 0x180;
default:    iret;

            org 0xc;
x:          word ?;
min:        word 0xffea;
max:        word 0x14;

start:      di;
            cla;
            out 0x1;
            out 0x7;
            out 0xb;
            out 0xd;
            out 0x11;
            out 0x15;
            out 0x19;
            out 0x1d;
            
            ld #0x9;
            out 0x3;
            ld #0xa;
            out 0x5;

            ei;
            cla;

incloop:    di;
            ld x;
            inc;
            call check;
            st x;
            ei;
            jump incloop;
            
int1:       di;
            ld x;
            asl;
            asl;
            add x;
            add x;
            add #0x7;
            out 0x2;
            ei;
            iret;

int2:       di;
            cla;
            in 0x4;
            sub x;
            st x;
            iret;
            ei;

check:      cmp min;
            bmi ld_min;

            cmp max;
            bmi return;

ld_min:     ld min;
return:     ret;
