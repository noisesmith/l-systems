(ns l-systems.core)

(def s '[a b c d e f g])


(def rule1
  '[[a b] [a b c]])

(def rule2
  '[[a b] [a b c]
    [e f] [a b]])

rule1
rule2


(take-while seq (iterate rest (range 10)))

(defn try-rule
  [rule sq]
  (let [[match replacement] rule
        length (count match)]
    ;(prn match replacement sq)
    (if (= (take length sq) match)
      [replacement (drop length sq)]
      nil)))


(def z '[b q 2 2 3])

(comment
  (try-rule rule1 s)
  (try-rule rule1 z)
)


(range 10)
(partition 2 (range 10))

(defn try-rules
  [rules sq]
  (first (keep identity (map #(try-rule % sq) (partition 2 rules)))))

(defn expand [rule-set sq]
  (loop [out [],
         in sq]
    (let [[replacement remaining :as result] (try-rules rule-set in)]
      (cond
       (empty? in) out
       (not (nil? result)) (recur (concat out replacement) remaining)
       :else (recur (concat out (take 1 in)) (rest in))))))


(comment
(expand rule1 s)
(expand rule1 z)
(expand rule2 s)

(clojure.pprint/pprint (take 10 (iterate (partial expand rule2) s)))
'([a b c d e f g]
 (a b c c d a b g)
 (a b c c c d a b c g)
 (a b c c c c d a b c c g)
 (a b c c c c c d a b c c c g)
 (a b c c c c c c d a b c c c c g)
 (a b c c c c c c c d a b c c c c c g)
 (a b c c c c c c c c d a b c c c c c c g)
 (a b c c c c c c c c c d a b c c c c c c c g)
 (a b c c c c c c c c c c d a b c c c c c c c c g))
)
