(ns l-systems.core-test
  (:require [l-systems.core :as l]
            [clojure.test :as test]))

(test/deftest step
  (test/is (= [[:a] [:b :c :d]]
              (l/step [:a :b :c :d] nil))
           "the step rule should move forward by one element"))

(test/deftest try-rules
  (test/is (= [[:a] [:b :c :d]]
              (l/try-rules [] [:a :b :c :d]))
           "the step rule should be applied if none others match"))

(test/deftest expand
  (test/is (= '[a b c c d e f g]
              (l/expand- [(l/simple-rule '[a b] '[a b c])] '[a b c d e f g])))
  (test/is (= "abccdefg"
              (l/expand [(l/string-rule '[a b] '[a b c])] "abcdefg"))
           "expand matches input types"))

(test/deftest expansions
  (test/is (= ["a" "ab" "abc" "abd" "abcd" "a" "ab" "abc"]
              (l/expansions 8 [(l/string-rule '[a b c d] '[a])
                               (l/string-rule '[b c] '[d])
                               (l/string-rule '[b] '[c])
                               (l/string-rule '[a] '[a b])]
                            "a"))))
