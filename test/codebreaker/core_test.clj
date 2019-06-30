(ns codebreaker.core-test
  (:require [clojure.test :refer :all]
            [codebreaker.core :refer :all]))

(deftest check-test
  (is (= "O" (check "RYOGP" "RRRRR"))))
