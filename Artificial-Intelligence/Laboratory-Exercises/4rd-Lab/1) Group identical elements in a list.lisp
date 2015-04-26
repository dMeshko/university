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
(DEFUN GRP (X L)
(
COND
 ((NULL L) NIL)
 ((EQUAL (CAR L) X) (CONS X (GRP X (CDR L))))
 (T (GRP X (CDR L)))
)
)

(defun grupiraj(l)
(
COND
 ((NULL L) NIL)
 ((MEMBER (CAR L) (CDR L)) (APPEND (GRP (CAR L) L) (GRUPIRAJ (REMOVE (CAR L) L))))
 (T (CONS (CAR L) (GRUPIRAJ (CDR L))))
)
)




;--don't change below this line
(print (grupiraj (readinput nil)))
