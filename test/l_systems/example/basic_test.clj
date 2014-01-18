(ns l-systems.example.basic-test
  (:require [l-systems.example.basic :as basic]
            [clojure.test :as test]))

(test/deftest try-rule-1
  (test/is (= '[[a b c] [c d e f g]]
              (basic/try-rule-1))))

(test/deftest try-rule-1'
  (test/is (= nil (basic/try-rule-1'))))

(test/deftest expand-1
  (test/is (= '[a b c c d e f g]
            (basic/expand-1))))

(test/deftest expand-2
  (test/is (= '[b q 2 2 3]
              (basic/expand-2))))

(test/deftest expand-3
  (test/is (= '[a b c c d a b g]
              (basic/expand-3))))

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
