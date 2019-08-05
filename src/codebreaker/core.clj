(ns codebreaker.core
  (:require [clojure.string :as s]
            [clojure.set :refer [intersection]]))

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
   :attempt-count 1})

(defn won?
  [{code :code
    guess :guess}]
  (= code guess))

(defn game-over?
  [{attempt-count :attempt-count}]
  (< MAX-ATTEMPTS attempt-count))

(defn- count-all-matches
  [{:keys [code guess]}]
  (let [matches (intersection (set code) (set guess))]
    (count matches)))

(defn count-exact-matches
  [{:keys [code guess]}]
  (let [bool->number {true 1
                      false 0}]
    (->> (map (comp bool->number =) code guess)
         (reduce +))))

(defn count-non-exact-matches
  [game]
  (let [all-matches (count-all-matches game)
        exact-matches (count-exact-matches game)]
    (- all-matches exact-matches)))

(defn any-matches?
  [game]
  (not (zero? (count-all-matches game))))

(defn play-game
  [game guess]
  (-> game
      (assoc :guess guess)
      (update :attempt-count inc)))
