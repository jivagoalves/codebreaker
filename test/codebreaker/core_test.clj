(ns codebreaker.core-test
  (:require [clojure.test :refer :all]
            [codebreaker.core :as core :refer [new-game
                                               play-game
                                               count-exact-matches
                                               count-non-exact-matches]]))

(deftest play-game-test
  (testing "when there is 1 exact match"
    (let [game (play-game (new-game "RBBBB") "RGGGG")]
      (is (= 1 (count-exact-matches game)))))

  (testing "when there is 1 non-exact match"
    (let [game (play-game (new-game "RBBBB") "GRGGG")]
      (is (= 1 (count-non-exact-matches game)))))

  (testing "when there is 1 exact match and 1 non-exact match"
    (let [game (play-game (new-game "ROBBB") "RGOGG")]
      (is (= 1 (count-exact-matches game)))
      (is (= 1 (count-non-exact-matches game))))))
