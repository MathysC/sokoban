; ##########
; #@    ####   
; #    $####
; #     ####
; #     ####
; #       .#
; ##########

(define (problem SOK07) (:domain SOKOBAN)
(:objects 
    f22 f23 f24 f25 f26
    f32 f33 f34 f35 f36
    f42 f43 f44 f45 f46
    f52 f53 f54 f55 f56
    f62 f63 f64 f65 f66 f67 f68 - floor

    d69 - destination

    b - box
)

(:init
    ; path to right
    (path f22 f23 r)
    (path f23 f24 r)
    (path f24 f25 r)
    (path f25 f26 r)
    (path f32 f33 r)
    (path f33 f34 r)
    (path f34 f35 r)
    (path f35 f36 r)
    (path f42 f43 r)
    (path f43 f44 r)
    (path f44 f45 r)
    (path f45 f46 r)
    (path f52 f53 r)
    (path f53 f54 r)
    (path f54 f55 r)
    (path f55 f56 r)
    (path f62 f63 r)
    (path f63 f64 r)
    (path f64 f65 r)
    (path f65 f66 r)
    (path f66 f67 r)
    (path f67 f68 r)
    (path f68 d69 r)

    ; path to down
    (path f22 f32 d)   
    (path f32 f42 d)   
    (path f42 f52 d)   
    (path f52 f62 d)   
    (path f23 f33 d)   
    (path f33 f43 d)   
    (path f43 f53 d)   
    (path f53 f63 d)   
    (path f24 f34 d)   
    (path f34 f44 d)   
    (path f44 f54 d)   
    (path f54 f64 d)
    (path f25 f35 d)   
    (path f35 f45 d)   
    (path f45 f55 d)   
    (path f55 f65 d)
    (path f26 f36 d)   
    (path f36 f46 d)   
    (path f46 f56 d)   
    (path f56 f66 d) 
    
    ; empty
    (empty f23)
    (empty f24)
    (empty f25)
    (empty f26)
    (empty f32)
    (empty f33)
    (empty f34)
    (empty f35)
    (empty f42)
    (empty f43)
    (empty f44)
    (empty f45)
    (empty f46)
    (empty f52)
    (empty f53)
    (empty f54)
    (empty f55)
    (empty f56)
    (empty f62)
    (empty f63)
    (empty f64)
    (empty f65)
    (empty f66)
    (empty f67)
    (empty f68)
    (empty d69)

    ; box
    (on b f36)
    (withbox f36)

    ; guard
    (on g f22)
)

(:goal (and
    (withbox d69)
))

)
