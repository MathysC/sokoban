; #######
; #     #
; #@ $# #
; #    .#
; #######
; A five by three space with the box at the center
;   and a wall between the box and the destination

(define (problem SOK02)
    (:domain SOKOBAN)
    (:objects
        f11 f12 f13 f14 f15 
        f21 f22 f23 f25 - floor 
        f31 f32 f33 f34 - floor
        dest - destination ;f35 
        b - box
    )

    (:init
        ; path to right
        (path f11 f12 r)
        (path f12 f13 r)
        (path f13 f14 r)
        (path f14 f15 r)
        (path f21 f22 r)
        (path f22 f23 r)
        (path f31 f32 r)
        (path f32 f33 r)
        (path f33 f34 r)
        (path f34 dest r)

        ; path to down
        (path f11 f21 d)
        (path f12 f22 d)
        (path f13 f23 d)
        (path f15 f25 d)
        (path f21 f31 d)
        (path f22 f32 d)
        (path f23 f33 d)
        (path f25 dest d)

        ; empty
        (empty f11)
        (empty f12)
        (empty f13)
        (empty f14)
        (empty f15)
        (empty f22)
        (empty f25) 
        (empty f31)
        (empty f32)
        (empty f33)
        (empty f34)
        (empty dest) ;f35

        ; box
        (on b f23)
        (withbox f23)

        ; guard
        (on g f21)
        

    )

    (:goal
        (and
            (withbox dest)
        )
    )

)