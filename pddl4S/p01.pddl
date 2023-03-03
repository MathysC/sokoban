; #@   $.#
; A five position problem in one line with one box
; next to the destination

(define (problem SOK01)
(:domain SOKOBAN)
(:objects 
    f1 f2 f3 f4 - floor
    dest - destination
    b - box
)

(:init
    (path f1 f2 r)
    (path f2 f3 r)
    (path f3 f4 r)
    (path f4 dest r)
    (on g f1)
    (empty f2)
    (empty f3)
    (on b f4) (withbox f4)
    (empty dest)
    
)

(:goal (and
    (withbox dest)
))

)
