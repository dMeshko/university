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
(defun suma (n)
(
COND
 ((= n 0) 0)
 (T (+ n (suma(- n 1))))
)   
)



;--don't change below this line
(print (suma (car (readinput nil))))
