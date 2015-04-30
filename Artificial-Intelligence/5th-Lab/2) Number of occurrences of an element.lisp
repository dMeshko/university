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
(defun broj(atom lista)
(
COND
 ((NULL LISTA) 0)
 ((= ATOM (CAAR LISTA)) (+ 1 (BROJ ATOM (CDR LISTA))))
 (T (BROJ ATOM (CDR LISTA)))
)
)




;--don't change below this line
(print (broj (car (readinput nil))(readinput nil)))
