(ns codebreaker.cli
  (:require [codebreaker.core :refer :all]))

(defn- prompt []
  (print "> ")
  (flush))

(defn- ask-for-guess []
  (prompt)
  (read-line))

(defn start
  [game]
  (println "Welcome!")
  (loop [current-game game]
    (cond
      (won? current-game) (println "You won!")
      (game-over? current-game) (println "You lost!")
      :else (when-let [guess (ask-for-guess)]
              (recur (play-game current-game guess))))))
