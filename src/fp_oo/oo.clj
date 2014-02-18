(ns fp-oo.oo)

;; Invoke a method on an instance of a class
;; by sending it a message (in this case, the
;; message is a keyword identifying the method)
(defn send-to
  [instance message & args]

  (assert (map? instance))
  (assert (keyword? message))

  (let [clazz (eval (:__class_symbol__ instance))
        method (message (:__instance_methods__ clazz))]
    (apply method instance args)))

;; Alias
(def dispatch send-to)

;; Invoke a method on an instance of a class
;; by sending it a message (in this case, the
;; message is a keyword identifying the method).
;; If there is no such method, attempt to access
;; an instance variable identified by the message
;; keyword.
(defn send-to-or-access
  [instance message & args]

  (assert (map? instance))
  (assert (keyword? message))

  (let [clazz (eval (:__class_symbol__ instance))
        method (message (:__instance_methods__ clazz))]
    (if (nil? method)
      (message instance)
      (apply method instance args))))

;; Create a new instance of a class:
;; 1) Allocate memory as a map {}
;; 2) Populate the instance with class metadata
;; 3) Invoke the constructor, identified by the
;;    keyword :add-instance-values
(defn make
  [clazz & args]

  (assert (map? clazz) "Not a valid class definition - expected map")
  (assert (:__own_symbol__ clazz) "Not a valid class definition")
  (assert (:__instance_methods__ clazz) "Not a valid class definition")
  (assert (:add-instance-values (:__instance_methods__ clazz)) "Not a valid class definition - no constructor found")

  (let [allocated { :__class_symbol__ (:__own_symbol__ clazz) }]
    (apply send-to allocated :add-instance-values args)))
