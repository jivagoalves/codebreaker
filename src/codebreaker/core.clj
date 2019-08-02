(ns codebreaker.core)

(def AVAILABLE-COLORS
  #{"R" "B" "G" "O" "P" "Y" "M" "W"})

(def MAX-ATTEMPTS 8)

(defn- gen-code
  []
  (let [random-colors (take 5 (shuffle AVAILABLE-COLORS))]
    (apply str random-colors)))

(defn new-game
  ([]
   (new-game (gen-code)))
  ([code]
  {:code code
   :attempt-count 1}))

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
