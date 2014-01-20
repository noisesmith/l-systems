(ns l-systems.example.basic
  (:require [l-systems.core :as l]
            [clojure.pprint :as pprint]))

(def input-1 '[a b c d e f g])

(def input-2 '[b q 2 2 3])

(def rule-1
  [(l/simple-rule '[a b] '[a b c])])

(def rule-2
  [(l/simple-rule '[a b] '[a b c])
   (l/simple-rule '[e f] '[a b])])

(def rule-3
  [(l/string-rule '[a] '[a b])
   (l/string-rule '[b a] '[c])])

(defn try-rule-1
  []
  (l/try-rules rule-1 input-1))
#_ [[a b c] [c d e f g]]

(defn try-rule-1'
  []
  (l/try-rules rule-1 input-2))
#_ [[b] [q 2 2 3]]

(defn expand-1
  []
  (l/expand rule-1 input-1))
#_ [a b c c d e f g]

(defn expand-2
  []
  (l/expand rule-1 input-2))
#_ [b q 2 2 3]

(defn expand-3
  []
  (l/expand rule-2 input-1))
#_ [a b c c d a b g]

(defn expansions
  [n]
  (l/expansions n rule-3 "abababaaabbbaaabbb"))

#_ ["abababaaabbbaaabbb"
    "abcccababbbcababbbb"
    "abbcccabcbbbcabcbbbb"
    "abbbcccabbcbbbcabbcbbbb"
    "abbbbcccabbbcbbbcabbbcbbbb"
    "abbbbbcccabbbbcbbbcabbbbcbbbb"
    "abbbbbbcccabbbbbcbbbcabbbbbcbbbb"
    "abbbbbbbcccabbbbbbcbbbcabbbbbbcbbbb"
    "abbbbbbbbcccabbbbbbbcbbbcabbbbbbbcbbbb"
    "abbbbbbbbbcccabbbbbbbbcbbbcabbbbbbbbcbbbb"]
