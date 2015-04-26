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
(defun nenum(lista)
(
COND 
 ((NULL LISTA) NIL)
 ((SYMBOLP (CAR LISTA)) (CONS (CAR LISTA) (NENUM (CDR LISTA))))
 (T (NENUM(CDR LISTA)))
)
)




;--don't change below this line
(print (nenum (readinput nil)))
