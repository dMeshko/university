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
(defun popravi(lista)
(
COND
 ((NULL LISTA) NIL)
 (T (APPEND (BUTLAST LISTA 2) (LIST (CAR (LAST (BUTLAST LISTA))) LISTA)))
)
)




;--don't change below this line
(print (popravi (readinput nil)))
