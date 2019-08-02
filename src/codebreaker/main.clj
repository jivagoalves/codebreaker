(ns codebreaker.main
  (:gen-class)
  (:require [codebreaker.cli :as cli]
            [codebreaker.core :refer [new-game gen-code]]))

(defn -main
  [& args]
  (let [code (gen-code)]
    (cli/start (new-game code))
    (println (str "Secret was " code "!"))))
