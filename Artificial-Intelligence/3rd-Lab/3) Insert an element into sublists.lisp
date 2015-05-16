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
(defun vmetni (x L)
(
COND
 ((NULL (CAR L)) NIL)
 ((ATOM (CAR L)) (VMETNI X (CDR L)))
 ;((AND (LISTP (CAR L)) (NOT (NULL (CDR L)))) (CONS (CONS X (CAR L)) (VMETNI X (CDR L))))
 ;(T (CONS X (CAR L)))
 (T (CONS (CONS X (CAR L)) (VMETNI X (CDR L))))
)
)



;--don't change below this line
(print (vmetni (car (readinput nil)) (readinput nil)))
