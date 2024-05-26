; Дан массив 2x2x2 в памяти. Найти сумму всех элементов 
org 0x100;
word 0x110; 0
word 0x120; 1

org 0x110;
word 0x130; 00
word 0x140; 01

org 0x120;
word 0x150; 10
word 0x160; 11

org 0x130; 
word 0x1; 000
word 0x2; 001

org 0x140;
word 0x2; 010
word 0x3; 011

org 0x150;
word 0x3; 100
word 0x4; 101

org 0x160;
word 0x4; 110
word 0x5; 111


org 0x170; 
result: word 0x0;
k_pointer: word 0x100;
j_pointer: word 0x0;
i_pointer: word 0x0;

k_arr_length: word 0x2;
j_arr_length: word 0x2;
i_arr_length: word 0x2;

j_iter_counter: word 0x0;
i_iter_counter: word 0x0;


start:
setup: 
ld (k_pointer)+;
st j_pointer;
ld j_arr_length;
st j_iter_counter;

call j_counting;
loop k_arr_length;
jump setup;
jump the_end;

j_counting: 
ld (j_pointer)+;
st i_pointer;
ld i_arr_length;
st i_iter_counter;

call i_counting;
loop j_iter_counter;
jump j_counting;
ret;

i_counting:
ld (i_pointer)+
add result;
st result;

loop i_iter_counter;
jump i_counting;
ret;

the_end: 
ld result;
hlt;
