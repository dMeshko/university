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


; write your functions here
(DEFUN SUM (LIST)
(
COND
 ((NULL LIST) 0)
 ((NUMBERP (CAR LIST)) (+ (- (CAR LIST) 2) (SUM (CDR LIST))))
 (T (SUM (CDR LIST)))
)
)

(DEFUN DEC (LISTA)
(COND
 ((NULL LISTA) NIL)
 ((NUMBERP (CAR LISTA)) (CONS (- (CAR LISTA) 2) (DEC (CDR LISTA))))
 (T (DEC (CDR LISTA)))
)
)

(defun namali(lista)
(
PROG()
(SETQ L (DEC LISTA))
(SETQ L1 (SUM LISTA))
(RETURN (APPEND L (CONS L1 NIL)))
)
)




;--don't change below this line
(print (namali (readinput nil)))
