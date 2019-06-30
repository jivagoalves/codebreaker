(ns codebreaker.core
  (:gen-class))

(defn check
  [code guess])

(defn new-game
  [code]
  {:code code})

(defn play
  [game])

(defn last-message
  [game]
  "Welcome!")
