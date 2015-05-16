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
(DEFUN DEL (N L X)
(
COND
 ((NULL L) NIL)
 ((= X N) (DEL N (CDR L) (+ X 1)))
 ((LISTP (CAR L)) (CONS (DEL N (CAR L) 1) (DEL N (CDR L) (+ X 1))))
 (T (CONS (CAR L) (DEL N (CDR L) (+ X 1))))
)
)

(defun otstraniN (n L)
	(DEL n L 1)
)



;--don't change below this line
(print (otstraniN (car (readinput nil)) (readinput nil)))
