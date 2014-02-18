(ns fp-oo.core
  (:gen-class)
  (:use [fp-oo.oo])
  (:use [fp-oo.point]))

(defn -main
  [& args]
  (def p (make Point 1 2))
  (println p))
