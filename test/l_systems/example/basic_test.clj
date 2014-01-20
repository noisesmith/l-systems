(ns l-systems.example.basic-test
  (:require [l-systems.example.basic :as basic]
            [clojure.test :as test]))

(test/deftest try-rule-1
  (test/is (= '[[a b c] [c d e f g]]
              (basic/try-rule-1))
           "apply a simple rule to a sequence"))

(test/deftest try-rule-1'
  (test/is (= '[[b] [q 2 2 3]]
              (basic/try-rule-1'))
           "default to a single step forward if no match"))

(test/deftest expand-1
  (test/is (= '[a b c c d e f g]
            (basic/expand-1))
           "fully expand a sequence by a ruleset"))

(test/deftest expand-2
  (test/is (= '[b q 2 2 3]
              (basic/expand-2))
           "no change if no rules match"))

(test/deftest expand-3
  (test/is (= '[a b c c d a b g]
              (basic/expand-3))
           "two matches in a ruleset"))

(test/deftest expansions
  (test/testing "expansions and string-rule"
    (test/is (= ["abababaaabbbaaabbb"
                 "abcccababbbcababbbb"
                 "abbcccabcbbbcabcbbbb"
                 "abbbcccabbcbbbcabbcbbbb"
                 "abbbbcccabbbcbbbcabbbcbbbb"
                 "abbbbbcccabbbbcbbbcabbbbcbbbb"
                 "abbbbbbcccabbbbbcbbbcabbbbbcbbbb"
                 "abbbbbbbcccabbbbbbcbbbcabbbbbbcbbbb"
                 "abbbbbbbbcccabbbbbbbcbbbcabbbbbbbcbbbb"
                 "abbbbbbbbbcccabbbbbbbbcbbbcabbbbbbbbcbbbb"]
                (basic/expansions 10)))))
