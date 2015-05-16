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
(defun izbrisi (x L)
(
COND
 ((NULL L) NIL)
 ((LISTP (CAR L)) (CONS (IZBRISI X (CAR L)) (IZBRISI X (CDR L))))
 ((EQ (CAR L) X) (IZBRISI X (CDR L)))
 (T (CONS (CAR L) (IZBRISI X (CDR L))))
)
)



;--don't change below this line
(print (izbrisi (car (readinput nil)) (readinput nil)))
