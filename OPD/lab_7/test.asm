org 0x0f;
ld #0x01;
hlt;

org     0x037E;
num: word 0xfff0;
start:  cla;
        ld num;
        word 0xfd0f;
        cla;
        hlt;
