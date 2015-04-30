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
(defun vmetni(A mnoz)
(
COND
 ((/= (LENGTH MNOZ) (LENGTH (REMOVE A MNOZ))) MNOZ)
 (T (CONS A MNOZ))
)
)




;--don't change below this line
(print (vmetni (car (readinput nil))(readinput nil)))
