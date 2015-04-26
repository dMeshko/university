; read input

(defun read-input(p)
(cond
	((null p) nil)
    (T (cons p (read-input (read nil nil))))
)
)

(defun readinput(i)
	(cdr (read-input 1))
)  
;--don't change above this line


; write your function(s) here
(defun findVowels(str)
(
COND
 ((NULL STR) NIL)
 ((OR (EQ (CAR STR) 'a) (EQ (CAR STR) 'e) (EQ (CAR STR) 'i) (EQ (CAR STR) 'o) (EQ (CAR STR) 'u)) (CONS (CAR STR) (FINDVOWELS (CDR STR))))
 (T (FINDVOWELS (CDR STR)))
)
)

(defun findConsonants(str)
(
COND
 ((NULL STR) NIL)
 ((OR (EQ (CAR STR) 'a) (EQ (CAR STR) 'e) (EQ (CAR STR) 'i) (EQ (CAR STR) 'o) (EQ (CAR STR) 'u)) (FINDCONSONANTS (CDR STR)))
 ((NUMBERP (CAR STR)) (FINDCONSONANTS (CDR STR)))
 (T (CONS (CAR STR) (FINDCONSONANTS (CDR STR))))
)
)

(DEFUN MAKESET (L)
(
COND
 ((NULL L) NIL)
 ((NOT (MEMBER (CAR L) (CDR L) :test #'equal)) (CONS (CAR L) (MAKESET (CDR L))))
 (T (CONS (CAR L) (MAKESET (REMOVE (CAR L) L))))
)
)

(defun izvadi_znaenje(str)
(
	LIST (MAKESET (FINDCONSONANTS STR)) (MAKESET (FINDVOWELS STR)) 
)
)


;--don't change below this line
(print (izvadi_znaenje (readinput nil)))
