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
(defun presmetaj(lista)
(
COND
 ((EQ '+ (NTH 1 LISTA)) (+ (NTH 0 LISTA) (NTH 2 LISTA)))
 ((EQ '- (NTH 1 LISTA)) (- (NTH 0 LISTA) (NTH 2 LISTA)))
 ((EQ '* (NTH 1 LISTA)) (* (NTH 0 LISTA) (NTH 2 LISTA)))
 ((EQ '/ (NTH 1 LISTA)) (/ (NTH 0 LISTA) (NTH 2 LISTA)))
)
)




;--don't change below this line
(print (presmetaj (readinput nil)))
