;Сравнение беззнаковых чисел.
;С ВУ-3 вводится два беззнаковых 16-разрядных числа
;(в два захода, сначала старшая часть, затем младшая).
;На ВУ-7 (семисегментный индикатор) выводится наибольшее из двух чисел.
org 0x100
nums_start: word 0x200;
temp: word 0x200;
counter: word ?;
first_num: word ?;
second_num: word ?;
biggest_num: word ?;
p_2500: word 0x9c4;
p_1000: word 0x3e8;
p_100: word 0x064;
p_10: word 0x00a;
c_1000: word 0x00;
c_100: word 0x00;
c_10: word 0x00;
c_1: word 0x00;
position: word ?;
symbol: word ?;

start:cla;
call clear;
;---------------
;in
s1: in 0x07;
and #0x40;
beq s1;
in 0x06;
st (temp)+;

ld counter;
inc;
st counter;

cmp #0x04;
bne s1;
;--------------
;compare nums
;200, 201
;202, 203
call load_num;
st first_num;

ld nums_start;
add #0x02;
st nums_start;
call load_num;
st second_num;

cmp first_num;
bpl write_second;
bmi write_first;
beq write_first;

printing: call count_1000;
hlt;

load_num: 
  ld nums_start;
  inc;
  st temp;

  ld (nums_start);
  asl;
  asl;
  asl;
  asl;
  asl;
  asl;
  asl;
  asl;
  add (temp);
  ret;

write_first:
  ld first_num;
  st biggest_num;
  jump printing;

write_second:
  ld second_num;
  st biggest_num;
  jump printing;

count_10000:
  ld biggest_num;
  sub p_2500;

count_1000:
  ld biggest_num;
  sub p_1000;
  bmi count_100;
  st biggest_num;
  ld c_1000;
  inc;
  st c_1000;
  jump count_1000;

count_100:
  ld #0x03;
  st position;
  ld c_1000;
  st symbol;
  call print;

  ld biggest_num;
  sub p_100;
  bmi count_10;
  st biggest_num;
  ld c_100;
  inc;
  st c_100;
  jump count_100;

count_10:
  ld #0x02;
  st position;
  ld c_100;
  st symbol;
  call print;

  ld biggest_num;
  sub p_10;
  bmi count_1;
  st biggest_num;
  ld c_10;
  inc;
  st c_10;
  jump count_10;

count_1:
  ld #0x01;
  st position;
  ld c_10;
  st symbol;
  call print;

  ld biggest_num;
  sub #0x01;
  bmi last_print;
  st biggest_num;
  ld c_1;
  inc;
  st c_1;
  jump count_1;

last_print:
  ld #0x00;
  st position;
  ld c_1;
  st symbol;
  call print;
  hlt;

print:
  ld position;
  rol;
  rol;
  rol;
  rol;
  add symbol;
  out 0x14;
  ret;

clear:
  ld #0x07;
  st position;
    clear_start: ld position;
    rol;
    rol;
    rol;
    rol;
    add #0x0f
    out 0x14;
    loop position;
    jump clear_start;
  cla;
  add #0x0f;
  out 0x14;
  ret;


ORG 0x38D
NUM1:WORD 0x600;
START:CLA;
S1: IN 0x07;
temp: WORD ?;
count: WORD ?;
AND #0x40; 
BEQ S1; 		
IN 0x06; 	
ST (NUM1);

S2: IN 0x07 ; 		Ожидание ввода второго символа
AND #0x40 ; 	Бит 6 SR == 0 (“Готов” нажата?)
BEQ S2 ; 		Нет – «Спин-луп»
LD (NUM1) ; 	Иначе загрузим предыдущий символ строки
SWAB ; 		Выберем второй символ в младшем байте…
IN 0x06 ; 		И введем второй символ с ВУ-2
ST (NUM1) ;


LD (NUM1);
CALL print_2_num

LD (NUM1);
SWAB;
CALL print_2_num


print_2_num:
LD (NUM1);
SWAB;
ASL;
ASL;
ASL;
ASL;
SWAB;
ASR;
ASR;
ASR;
ASR;
OUT 0x14;

LD (NUM1);
ROR;
ROR;
ROR;
ROR;
ADD count;
ADD #01;
OUT 0x14;

HLT;
ORG 0x600; 
WORD ? 
