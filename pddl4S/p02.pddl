; #######
; #     #
; #@   $#
; #    .#
; #######
; A five by three space with the box next to the 
; destination

(define (problem SOK02)
    (:domain SOKOBAN)
    (:objects
        f1 f2 f3 f4 f5 f6 f7 f8 f9 f10 f11 f12 f13 f14 - floor
        dest - destination
        b - box
    )

    (:init
        (empty f1)
        (empty f2)
        (empty f3)
        (empty f4)
        (empty f5)
        (on g f6)
        (empty f7)
        (empty f8)
        (empty f9)
        (on b f10)
        (withbox f10)
        (empty f11)
        (empty f12)
        (empty f13)
        (empty f14)
        (empty dest)

        ; path to right
        (path f1 f2 r)
        (path f2 f3 r)
        (path f3 f4 r)
        (path f4 f5 r)
        (path f6 f7 r)
        (path f7 f8 r)
        (path f8 f9 r)
        (path f9 f10 r)
        (path f11 f12 r)
        (path f12 f13 r)
        (path f13 f14 r)
        (path f14 dest r)

        ; path to down
        (path f1 f6 d)
        (path f2 f7 d)
        (path f3 f8 d)
        (path f4 f9 d)
        (path f5 f10 d)
        (path f6 f11 d)
        (path f7 f12 d)
        (path f8 f13 d)
        (path f9 f14 d)
        (path f10 dest d)

    )

    (:goal
        (and
            (withbox dest)
        )
    )

)