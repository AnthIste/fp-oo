(ns fp-oo.point
  (:use [fp-oo.oo]))

(def Point
  {
   :__own_symbol__ 'fp-oo.point/Point

   ;; Static function definitions that operate on
   ;; a specific Point instance ("this")
   :__instance_methods__
   {
    :__class_symbol__ nil

    ;; Use :__class_symbol__ as an /accessor/, which
    ;; retrieves the internal value set by 'make'.
    ;; This is equivalent to the following code:
    ;; (fn [this] (:__class_symbol__ this))
    :class-name :__class_symbol__

    ;; Returns the actual class definition of Point.
    ;; That is, return the value definied by
    ;; (def Point { ... })
    :class (fn [this] Point)

    ;; Constructor, called when a new instance is
    ;; created using 'make'.
    :add-instance-values
    (fn [this x y]
      (assoc this :x x :y y))

    ;; Standard member function
    :origin
    (fn [this] (make Point 0 0))

    ;; Standard member function
    :shift
    (fn [this dx dy]
      (make Point
            (+ (:x this) dx)
            (+ (:y this) dy)))
    }
   })

;; Create an instance using class definition
(def p (make Point 1 2))

;; Dynamically get the class type and use it
;; to create a second instance
(def clazz (dispatch p :class))
(def pp (make clazz 3 4))

(dispatch p :class-name)
(dispatch p :class)
(dispatch pp :class-name)
(dispatch pp :class)

(dispatch p :shift 3 4)

;; Class defs are the same, returns true
(= (dispatch p :class) (dispatch pp :class))
