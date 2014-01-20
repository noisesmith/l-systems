(ns l-systems.core)

(defn step
  [input pre]
  (split-at 1 input))

(def base-rules [step])

(defn try-rules
  "Tries all rules in order, returns the first non-nil result.
   A rule should take two args pre and post, and return its
   replacement sequence and the part of pre it did not consume as a
   two element vector."
  [rules input & [pre]]
  (first (keep #(% input pre)
               (concat rules base-rules))))

(defn expand-
  [rule-set sq]
  (loop [out [],
         in (seq sq)]
    (let [[replacement remaining :as result] (try-rules rule-set in out)]
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

(defn simple-rule
  [match replacement]
  (let [length (count match)]
    (fn replacer [input & [pre]]
      (if (= (take length input) match)
        [replacement (drop length input)]
        nil))))

(defn string-rule
  [match replacement]
  (simple-rule (map (comp first name) match)
               (map (comp first name) replacement)))
