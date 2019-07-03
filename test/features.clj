(ns features
  (:require [clojure.test :refer :all]
            [support :refer :all]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [codebreaker.cli :as cli]
            [codebreaker.core :refer [new-game]]))

(defn- lines
  [out]
  (s/split-lines out))

(Feature "Player plays a game"

  (Scenario "Player starts a game"
    (Given "a new game"
      (let [game (new-game "RYOGP")]

        (When "the player starts the game"
          (let [out (with-out-str
                      (with-in-str ""
                        (cli/start game)))]

            (Then "the player sees a welcoming message"
              (is (= "Welcome!" (first (lines out))))

              (And "the player sees a prompt for the code"
                (is (= "> " (last (lines out)))))))))))

  (Scenario "Player wins the game"
    (Given "a new game with a code"
      (let [code "ROYGP"
            game (new-game code)]

        (When "the player breaks the code by placing all marbles in the right order"
          (let [out (with-out-str
                      (with-in-str (str code "\n")
                        (cli/start game)))]

            (Then "the player sees a congratulation message after prompt"
              (is (= "> You won!" (last (lines out))))))))))

  (Scenario "Player loses the game"
    (Given "a new game with a code"
      (let [code "ROYGP"
            game (new-game code)]

        (When "the player can't break the code after 8 attempts"
          (let [out (with-out-str
                      (with-in-str (s/join (take 8 (repeat "RRRRR\n")))
                        (cli/start game)))]

            (Then "the player sees 'You lost!' after seeing the prompt 8 times"
              (is (= "> > > > > > > > You lost!" (last (lines out))))))))))

  #_(Scenario "Player attempts to break the code"
    (Given "a new game with a code"
      (let [code "ROYGP"
            game (new-game code)]

        (When "the player places marbles in the right position"
          (Then "the player sees an 'O' for each correct marble"
              ))))))
