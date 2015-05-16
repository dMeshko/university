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
(defun broevi (L)
(
COND
 ((NULL L) NIL)
 ((LISTP (CAR L)) (APPEND (BROEVI (CAR L)) (BROEVI (CDR L))))
 ((NUMBERP (CAR L)) (CONS (CAR L) (BROEVI (CDR L))))
 (T (BROEVI (CDR L)))
)
)



;--don't change below this line
(print (broevi (readinput nil)))
