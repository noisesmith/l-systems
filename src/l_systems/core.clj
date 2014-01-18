(ns l-systems.core)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn try-rule
  [rule sq]
  (let [[match replacement] rule
        length (count match)]
    ;(prn match replacement sq)
    (if (= (take length sq) match)
      [replacement (drop length sq)]
      nil)))

(defn try-rules
  [rules sq]
  (or (first (keep #(try-rule % sq) (partition 2 rules)))
      [(take 1 sq) (drop 1 sq)]))

(defn expand-
  [rule-set sq]
  (loop [out [],
         in (seq sq)]
    (let [[replacement remaining :as result] (try-rules rule-set in)]
      (if (seq remaining)
        (recur (concat out replacement) remaining)
        (concat out replacement)))))

(defn coercion-from
  [input]
  (get
   {(class "") (partial apply str)
    (class []) (partial into [])}
   (class input)
   identity))

(defn expand
  [rule-set sq]
  ((coercion-from sq) (expand- rule-set sq)))

(defn expansions
  [count rule-set sq]
  (->> (seq sq)
       (iterate (partial expand- rule-set))
       (map (coercion-from sq))
       (take count)))

(defn string-rules
  "given a rule-set for one character symbols, makes a string rule"
  [rule-set]
  (for [clause rule-set]
    (map (comp first name) clause)))
