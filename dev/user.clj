(ns user
  (:require [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [clojure.test :refer [run-all-tests]]))

(defn run-tests
  []
  (let [result (refresh-all)]
    (if (= result :ok)
      (run-all-tests #"^.*feature.*$")
      result)))
