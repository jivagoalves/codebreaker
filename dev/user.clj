(ns user
  (:require [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [clojure.test :refer [run-all-tests]]
            [codebreaker.cli :as cli]
            [codebreaker.core :refer :all]))

(defn run-tests
  []
  (let [result (refresh-all)]
    (if (= result :ok)
      (run-all-tests #"(^.*feature.*$)|(^.*-test$)")
      result)))
