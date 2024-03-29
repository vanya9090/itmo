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

 
