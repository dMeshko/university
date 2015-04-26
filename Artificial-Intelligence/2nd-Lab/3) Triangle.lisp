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
(defun triagolnik(lista)
(
PROG()
(SETQ A (CAR LISTA))
(SETQ B (CADR LISTA))
(SETQ C (CADDR LISTA))
(IF (AND (> (+ A B) C) (> (+ A C) B) (> (+ B C) A)) (RETURN T))
)
)




;--don't change below this line
(print (triagolnik (readinput nil)))
