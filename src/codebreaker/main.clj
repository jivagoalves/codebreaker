(ns codebreaker.main
  (:gen-class)
  (:require [codebreaker.cli :as cli]
            [codebreaker.core :refer [new-game]]))

(defn -main
  [& args]
  (cli/start (new-game)))
