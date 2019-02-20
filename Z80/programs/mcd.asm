ORG #4000
mcd	ld a,18 ;110 en a
	ld b,a  
	ld a,24 ; 165 en a
	ld c, a 
loop	ld a,b ;110 en a
	cp c ;resta a-165
	jr z, output ;if zero flag
	jr c, minor ; if carry flag
	sub c; a-c
	ld b,a 
	jr loop
minor	ld a,c
	sub b
	ld c,a
	jr loop
output	out (4020),a
final	HALT