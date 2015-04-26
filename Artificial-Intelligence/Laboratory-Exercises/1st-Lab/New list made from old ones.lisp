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



(defun novalistaodstari (X Y)

; write your function here
	(CONS (CAR X) (CONS (CAR Y) NIL))
    ;(LIST (CAR X) (CAR Y))
)





;--don't change below this line
(print (novalistaodstari (readinput nil) (readinput nil))) 
