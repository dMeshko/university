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
	(COND
    	((NULL lista) 0)
        ((/= (MOD (CAR lista) 2) 0) (+ (CAR lista) (presmetaj(CDDR lista))))
        (T (presmetaj (CDDR lista)))
    )
)




;--don't change below this line
(print (presmetaj (readinput nil)))
