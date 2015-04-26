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
(defun inversion(lista)
	(APPEND lista (CDR (REVERSE lista)))
)




;--don't change below this line
(print (inversion (readinput nil)))
