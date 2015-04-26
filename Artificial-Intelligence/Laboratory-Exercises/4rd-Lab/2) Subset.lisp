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
(defun podM(A B)
(
COND
 ((NULL A) T)
 ((MEMBER (CAR A) B :test #'equal) (PODM (CDR A) B))
 (T NIL)
)
)




;--don't change below this line
(print (podM (readinput nil)(readinput nil)))
