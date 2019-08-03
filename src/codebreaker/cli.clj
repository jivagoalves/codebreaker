(ns codebreaker.cli
  (:require [codebreaker.core :refer :all]))

(defn- prompt []
  (print "> ")
  (flush))

(defn- ask-for-guess []
  (prompt)
  (read-line))

(defn- print-marking
  [{:keys [exact-matches
           non-exact-matches]}]
  (when (or (not (zero? exact-matches))
            (not (zero? non-exact-matches)))
    (dotimes [_ exact-matches] (print "O"))
    (dotimes [_ non-exact-matches] (print "X"))
    (println)
    (flush)))

(defn start
  [game]
  (println "Welcome!")
  (loop [{:as current-game
          :keys [exact-matches
                 non-exact-matches]} game]
    (print-marking current-game)

    (cond
      (won? current-game) (println "You won!")
      (game-over? current-game) (println "You lost!")
      :else (when-let [guess (ask-for-guess)]
              (recur (play-game current-game guess))))))
