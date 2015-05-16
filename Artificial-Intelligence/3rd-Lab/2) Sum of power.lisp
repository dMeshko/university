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
(defun stepen (m n)
(
COND
 ((= n 1) m)
 (T (* (stepen m (- n 1)) m))
)
)

(defun suma_stepen (m n)
(
COND
 ((= m 0) 0)
 (T (+ (SUMA_STEPEN (- m 1) N) (STEPEN M N)))
)
)



;--don't change below this line
(print (suma_stepen (car (readinput nil)) (car (readinput nil))))
