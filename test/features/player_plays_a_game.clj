(ns features.player-plays-a-game
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
              (is (= "You won!" (last (lines out))))))))))

  (Scenario "Player loses the game"
    (Given "a new game with a code"
      (let [code "ROYGP"
            game (new-game code)]

        (When "the player can't break the code after 8 attempts"
          (let [out (with-out-str
                      (with-in-str (s/join (take 8 (repeat "BBBBB\n")))
                        (cli/start game)))]

            (Then "the player sees 'You lost!' after seeing the prompt 8 times"
              (is (= "> > > > > > > > You lost!" (last (lines out))))))))))

  (Scenario "Player attempts to break the code"
    (Given "a new game with a code"
      (let [code "RPPPP"
            game (new-game code)]

        (When "the player places marbles in the right position"
          (let [out (with-out-str
                      (with-in-str "RBBBB\n"
                        (cli/start game)))]

            (Then "the player sees an 'O' for each correct marble"
              (let [[_welcome prompt-and-mark] (lines out)]
                (is (= "> O" prompt-and-mark))))))))

    (Given "a new game with a code"
      (let [code "PRPPP"
            game (new-game code)]

        (When "the player places marbles that are present in the code"
          (let [out (with-out-str
                      (with-in-str "RBBBB\n"
                        (cli/start game)))]

            (Then "the player sees an 'X' for each marble present"
              (let [[_welcome prompt-and-mark] (lines out)]
                (is (= "> X" prompt-and-mark))))))))))
