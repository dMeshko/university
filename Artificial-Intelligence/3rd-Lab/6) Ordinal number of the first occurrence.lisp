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
(DEFUN FND (X L R)
(
COND
 ((NULL L) R)
 ((LISTP (CAR L)) (IF (= (FND X (CAR L) R) 0) (FND X (CDR L) R) 1))
 ((EQ X (CAR L)) (FND X NIL 1))
 (T (FND X (CDR L) R))
)
)

(DEFUN CNT (X L)
(
COND
 ((NULL L) 0)
 ((LISTP (CAR L)) (IF (= 1 (FND X (CAR L) 0)) (CNT X (CAR L)) (+ (CNT X (CAR L)) (CNT X (CDR L)))))
 ((EQ X (CAR L)) (+ 1 (CNT X NIL)))
 (T (+ 1 (CNT X (CDR L))))
)
)

(defun redenbr (x L)
(
 IF (= (FND X L 0) 1) (CNT X L) 0	
)          
)


;--don't change below this line
(print (redenbr (car (readinput nil)) (readinput nil)))
