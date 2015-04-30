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


; write your function here
(defun stenci(lista)
(
COND
 ((NULL LISTA) NIL)
 ((EQ (CAR LISTA) (CADR LISTA)) (STENCI (CDR LISTA)))
 (T (CONS (CAR LISTA) (STENCI (CDR LISTA))))
)
)




;--don't change below this line
(print (stenci (readinput nil)))
