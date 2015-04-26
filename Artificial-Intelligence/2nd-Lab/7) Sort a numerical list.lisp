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
(DEFUN FINDMIN (LIST)
(
COND
 ((NULL LIST) NIL)
 ((= (LENGTH LIST) 1) (CAR LIST))
 (T (IF (> (CAR LIST) (CADR LIST)) (FINDMIN(CDR LIST)) (FINDMIN(CONS (CAR LIST) (CDDR LIST)))))
)
)

(DEFUN FINDREST (LIST ELEMENT)
(
COND
 ((NULL LIST) NIL)
 ((= (CAR LIST) ELEMENT) (FINDREST (CDR LIST) ELEMENT))
 (T (CONS (CAR LIST) (FINDREST (CDR LIST) ELEMENT)))
)
)

(defun sortiraj(lista)
(
COND
 ((NULL LISTA) NIL)
 (T (CONS (FINDMIN LISTA) (SORTIRAJ (FINDREST LISTA (FINDMIN LISTA)))))
)
)




;--don't change below this line
(print (sortiraj (readinput nil)))
