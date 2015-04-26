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
(defun unija(A B)
(
COND
 ((NULL A) (APPEND A B))
 ((MEMBER (CAR A) B) (UNIJA (CDR A) B))
 (T (CONS (CAR A) (UNIJA (CDR A) B)))
)
)




;--don't change below this line
(print (unija (readinput nil)(readinput nil)))
