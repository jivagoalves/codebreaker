(ns codebreaker.core
  (:gen-class))

(def MAX-ATTEMPTS 8)

(defn new-game
  [code]
  {:code code
   :attempt-count 1})

(defn won?
  [{code :code
    guess :guess}]
  (= code guess))

(defn game-over?
  [{attempt-count :attempt-count}]
  (< MAX-ATTEMPTS attempt-count))

(defn play-game
  [game guess]
  (-> game
      (assoc :guess guess)
      (update :attempt-count inc)))

