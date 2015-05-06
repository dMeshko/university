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
(defun funny(n)
(
COND
 ((= N 1) 1)
 ;((= (MOD N 2) 0) (* 2 (FUNNY (- N 1))))
 ;((AND (/= (MOD N 2) 0) (> N 1)) (+ (FUNNY (- N 1)) (FUNNY (- N 2))))
 ((= (MOD N 2) 0) (* 2 (EXPT 3 (/ (- N 2) 2))))
 ((AND (/= (MOD N 2) 0) (> N 1)) (EXPT 3 (FLOOR(+ (/ (- N 2) 2) 1))))
 (T (NIL))
)
)




;--don't change below this line
(print (funny (car (readinput nil))))
