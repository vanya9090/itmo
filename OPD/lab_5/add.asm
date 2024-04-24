;Сравнение беззнаковых чисел.
;С ВУ-3 вводится два беззнаковых 16-разрядных числа
;(в два захода, сначала старшая часть, затем младшая).
;На ВУ-7 (семисегментный индикатор) выводится наибольшее из двух чисел.
org 0x100
nums_start: word 0x200;
temp: word 0x200;
counter: word ?;
first_num: word ?;
start:cla;
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
hlt;

load_num: 
  ld nums_start;
  inc;
  st temp;

  ld (nums_start);
  asl
  asl
  asl
  asl
  asl
  asl
  asl
  asl
  add (temp);
  ret;



org 0x100
position: word ?;
symbol: word ?;
start:cla;
call clear;

ld #0x03;
st position;
ld #0x03;
st symbol;

ld position;
rol;
rol;
rol;
rol;
add symbol;
out 0x14;
hlt;

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
