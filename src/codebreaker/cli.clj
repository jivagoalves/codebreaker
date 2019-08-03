(ns codebreaker.cli
  (:require [codebreaker.core :refer :all]))

(defn- prompt []
  (print "> ")
  (flush))

(defn- ask-for-guess []
  (prompt)
  (read-line))

(defn- print-marking
  [game]
  (when (any-matches? game)
    (dotimes [_ (count-exact-matches game)] (print "O"))
    (dotimes [_ (count-non-exact-matches game)] (print "X"))
    (println)
    (flush)))

(defn start
  [game]
  (println "Welcome!")
  (loop [current-game game]
    (print-marking current-game)

    (cond
      (won? current-game)
      (println "You won!")

      (game-over? current-game)
      (println "You lost!")

      :else
      (when-let [guess (ask-for-guess)]
        (recur (play-game current-game guess))))))
