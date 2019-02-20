org #4000
; Inputs a, b
; Outputs a = gcd(a, b)
; Destroys c
; Assumes a and b are positive one-byte integers
ld a, 18
ld b, 24
gcd:
    cp b
    ret z                   ; while a != b
 
    jr c, other              ; if a > b
 
    sub b                   ; a = a - b

    jr #4009
 
other:
    ld c, a                 ; Save a
    ld a, b                 ; Swap b into a so we can do the subtraction
    sub c                   ; b = b - a
    ld b, a                 ; Put a and b back where they belong
    ld a, c
 
    jr gcd