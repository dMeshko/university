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
(DEFUN FND (sL L)
(
COND
 ((NULL L) 0)
 ;((> (CAR sL) (CAAR L)) 0)
 ((= (CAR sL) (CAAR L)) (* (CADR sL) (CADAR L)))
 (T (FND sL (CDR L)))
)
)

(defun skalaren(niza1 niza2)
(
COND
 ((NULL NIZA1) 0)
 (T (+ (FND (CAR NIZA1) NIZA2) (SKALAREN (CDR NIZA1) NIZA2)))
)
)




;--don't change below this line
(print (skalaren (readinput nil)(readinput nil)))
