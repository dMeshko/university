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
(defun lastlist(arg)
(
COND
 	((NULL arg) NIL)
    ((NULL (CDR arg)) (LIST (CAR arg)))
    (T (LASTLIST(CDR arg)))
)
)



;--don't change below this line
(print (lastlist (readinput nil)))
