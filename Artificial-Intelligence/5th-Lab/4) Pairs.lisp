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
(defun podzagradi(lista)
(
COND
 ((NULL LISTA) NIL)
 ((= (LENGTH LISTA) 1) (APPEND (LIST (LIST (CAR LISTA))) (PODZAGRADI (CDR LISTA))))
 (T (APPEND (LIST (LIST (CAR (SORT (LIST (CAR LISTA) (CADR LISTA)) #'<=)) (CADR (SORT (LIST (CAR LISTA) (CADR LISTA)) #'<=)))) (PODZAGRADI (CDDR LISTA))))
)
)




;--don't change below this line
(print (podzagradi (readinput nil)))
