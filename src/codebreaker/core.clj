(ns codebreaker.core
  (:require [clojure.string :as s]
            [clojure.set :refer [intersection]])
  )

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

(defn- count-all-matches
  [code guess]
  (let [matches (intersection (set code) (set guess))]
    (count matches)))

(defn- count-exact-matches
  [code guess]
  (let [bool->number {true 1
                      false 0}]
    (->> (map (comp bool->number =) code guess)
         (reduce +))))

(defn- wrap-exact-matches
  [{:as game :keys [code]} guess]
  (let [exact-matches (count-exact-matches code guess)]
    (assoc game :exact-matches exact-matches)))

(defn- wrap-non-exact-matches
  [{:as game :keys [code]} guess]
  (let [all-matches (count-all-matches code guess)
        exact-matches (count-exact-matches code guess)
        non-exact-matches (- all-matches exact-matches)]
    (assoc game :non-exact-matches non-exact-matches)))

(defn play-game
  [game guess]
  (-> game
      (assoc :guess guess)
      (update :attempt-count inc)
      (wrap-exact-matches guess)
      (wrap-non-exact-matches guess)))
