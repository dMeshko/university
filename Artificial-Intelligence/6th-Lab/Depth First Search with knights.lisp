(defun depth-first-search (start goal been-list moves)
  (cond ((equal start goal) 
         (reverse (cons start been-list)))
        (t (try-moves start goal been-list moves moves))))

; Try-moves scans down the list of moves in moves-to-try, 
; attempting to generate a child state.  If it produces 
; this state, it calls depth-first-search to complete the search.

(defun try-moves (start goal been-list moves-to-try moves)
  (cond ((null moves-to-try) nil)
        ((member start been-list :test #'equal) nil)
        (t (let ((child (funcall (car moves-to-try) start)))
             (if child 
               (or (depth-first-search (funcall (car moves-to-try) start)
                                       goal
                                       (cons start been-list)
                                       moves)
                   (try-moves start goal been-list (cdr moves-to-try) moves))
               (try-moves start goal been-list (cdr moves-to-try) moves))))))

; run-depth-first calls depth-first-search, initializing the been-list to ().
(defun run-depth (start goal moves)
  (depth-first-search start goal () moves))

(DEFUN UP-LEFT (POSITION)
	(LET ((X (- (CAR POSITION) 2)) (Y (+ (CADR POSITION) 1))) 
	(COND
		((SAFE X Y) (LIST X Y))
		(T NIL)
	)) 
)

(DEFUN UP-LEFTT (POSITION)
	(LET ((X (- (CAR POSITION) 1)) (Y (+ (CADR POSITION) 2))) 
	(COND
		((SAFE X Y) (LIST X Y))
		(T NIL)
	)) 
)

(DEFUN UP-RIGHT (POSITION)
	(LET ((X (+ (CAR POSITION) 2)) (Y (+ (CADR POSITION) 1))) 
	(COND
		((SAFE X Y) (LIST X Y))
		(T NIL)
	)) 
)

(DEFUN UP-RIGHTT (POSITION)
	(LET ((X (+ (CAR POSITION) 1)) (Y (+ (CADR POSITION) 2))) 
		(COND
		((SAFE X Y) (LIST X Y))
		(T NIL)
	)) 
)

(DEFUN DOWN-LEFT (POSITION)
	(LET ((X (- (CAR POSITION) 2)) (Y (- (CADR POSITION) 1))) 
	(COND
		((SAFE X Y) (LIST X Y))
		(T NIL)
	)) 
)

(DEFUN DOWN-LEFTT (POSITION)
	(LET ((X (- (CAR POSITION) 1)) (Y (- (CADR POSITION) 2))) 
	(COND
		((SAFE X Y) (LIST X Y))
		(T NIL)
	)) 
)

(DEFUN DOWN-RIGHT (POSITION)
	(LET ((X (+ (CAR POSITION) 2)) (Y (- (CADR POSITION) 1))) 
	(COND
		((SAFE X Y) (LIST X Y))
		(T NIL)
	)) 
)

(DEFUN DOWN-RIGHTT (POSITION)
	(LET ((X (+ (CAR POSITION) 1)) (Y (- (CADR POSITION) 2))) 
	(COND
		((SAFE X Y) (LIST X Y))
		(T NIL)
	)) 
)

(DEFUN SAFE (X Y)
	(AND (> X 0) (> Y 0) (< X 4) (< Y 4))
)

(SETQ MOVES '(UP-LEFT UP-LEFTT UP-RIGHT UP-RIGHTT DOWN-LEFT DOWN-LEFTT DOWN-RIGHT DOWN-RIGHTT))

; add the moves list and uncomment the following line:
(print (run-depth '(1 1) '(3 3) MOVES))
