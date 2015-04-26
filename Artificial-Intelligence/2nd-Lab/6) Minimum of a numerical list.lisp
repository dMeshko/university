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
(defun najmal(lista)
(
COND
 ((NULL LISTA) NIL)
 ((= (LENGTH LISTA) 1) (CAR LISTA))
 ((< (CAR LISTA) (CADR LISTA)) (NAJMAL (CONS (CAR LISTA) (CDDR LISTA))))
 (T (NAJMAL (CDR LISTA)))
)
)




;--don't change below this line
(print (najmal (readinput nil)))
