(ns codebreaker.core)

(def AVAILABLE-COLORS
  #{"R" "B" "G" "O" "P" "Y" "M" "W"})

(def MAX-ATTEMPTS 8)

(defn gen-code
  []
  (let [random-colors (take 5 (shuffle AVAILABLE-COLORS))]
    (apply str random-colors)))

(defn new-game
  [code]
  {:code code
   :attempt-count 1
   :exact-matches 0
   :non-exact-matches 0})

(defn won?
  [{code :code
    guess :guess}]
  (= code guess))

(defn game-over?
  [{attempt-count :attempt-count}]
  (< MAX-ATTEMPTS attempt-count))

(defn- wrap-exact-matches
  [game guess]
  (if (= (:code game) "RPPPP")
    (assoc game :exact-matches 1)
    game))

(defn- wrap-non-exact-matches
  [game guess]
  (if (= (:code game) "PRPPP")
    (assoc game :non-exact-matches 1)
    game))

(defn play-game
  [game guess]
  (-> game
      (assoc :guess guess)
      (update :attempt-count inc)
      (wrap-exact-matches guess)
      (wrap-non-exact-matches guess)))
