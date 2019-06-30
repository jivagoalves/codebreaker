(ns user
  (:require [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [clojure.test :refer [run-all-tests]]))

(defn run-tests
  []
  (refresh-all)
  (run-all-tests #"^.*feature.*$")
  (run-all-tests #"^codebreaker.*test$"))
