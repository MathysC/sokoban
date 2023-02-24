(define (domain SOKOBAN)

	(:requirements :strips :typing)

    (:types
        ; wall ; #  
        ; guard ; @ ; (+ is this guard on a storage place)  
        ; box  ; $ ; (* is a box on a storage place)
        ; destination ; .
        ; floor ; " " (space)

        ; objects that can move
        interactive - object
        guard box - interactive

        ; background objects, where interactive can be on
        position - object
        floor destination - position  
        
        ; direction the interactive object apply to move.
        direction
	)
    (:constants
        g - guard
        r l u d - direction
    )
	(:predicates
            (path ?a ?b - position ?d - direction)
            (on ?i - interactive ?a - position)
            (withbox ?p - position)
            (empty ?p - position)
	)

	(:action move
        :parameters (?a ?b - position ?d - direction)
        :precondition (and 
            (on g ?a) ; guard on position 'a'
            (path ?a ?b ?d) ; there is a path between 'a' and 'b'
            (empty ?b) ; position 'b' is empty
        )
        :effect (and 
            (on g ?b)   ; guard on 'b'
            (not (empty ?b)); --------
            (not (on g ?a)) ; guard not on 'a' anymore
            (empty ?a) ; -----------------------------
        )
    )
	
	(:action push ; TODO: missing interactive box 
        :parameters (?a ?b ?c - position ?d - direction)
        :precondition (and 
            (on g ?a) ; guard on position 'a'
            (withbox ?b) ; box on position 'b'
            (empty ?c) ; position next to the box is empty
            (path ?a ?b ?d) ; guard can go on box’s position 'b'
            (path ?b ?c ?d) ; box can go on empty position 'c'
        )
        :effect (and 
            ; Move the guard
            (on g ?b)   ; guard on 'b'
            (not (empty ?b)); --------
            (not (on g ?a)) ; guard not on 'a' anymore
            (empty ?a) ; -----------------------------
            ; TODO: move the box
            (on g ?b)   ; guard on 'b'
            (not (empty ?b)); --------
            (not (on g ?a)) ; guard not on 'a' anymore
            (empty ?a) ; -----------------------------
            ; TODO: 'c' become withbox
        )
    )
    
    
	;;;;;;;;;;;;;;;;;;;;;;;;;;;
	;; Reverse methods
	;;;;;;;;;;;;;;;;;;;;;;;;;;;
	(:action reverse_rl ; reverse right to left
        :parameters (?a ?b - position)
		:precondition (and
            (path ?a ?b r)
		)
		:effect (and
            (path ?b ?a l)
		)
	)

	(:action reverse_lr ; reverse left to right
        :parameters (?a ?b - position)
		:precondition (and
            (path ?a ?b l)
		)
		:effect (and
            (path ?b ?a r)
		)
	)

    (:action reverse_ud ; reverse up to down
        :parameters (?a ?b - position)
		:precondition (and
            (path ?a ?b u)
		)
		:effect (and
            (path ?b ?a d)
		)
	)  

    (:action reverse_du ; reverse down to up
        :parameters (?a ?b - position)
		:precondition (and
            (path ?a ?b d)
		)
		:effect (and
            (path ?b ?a u)
		)
	)   
)