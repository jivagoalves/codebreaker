(ns support
  (:require [clojure.test :refer :all]
            [clojure.string :as s]))

(defn- lisp-case
  [string]
  (s/replace (s/lower-case string) #"\s+" "-"))

(defmacro Feature
  [description & body]
  (let [test-name (symbol (lisp-case (s/lower-case description)))]
    `(deftest ~(vary-meta test-name assoc :feature true)
       (testing (str "\nFeature: " ~description)
         ~@body))))

(defmacro Scenario
  [message & body]
  `(testing (str "\n Scenario: " ~message)
     ~@body))

(defmacro Given
  [message & body]
  `(testing (str "\n  Given " ~message)
     ~@body))

(defmacro When
  [message & body]
  `(testing (str "\n  When " ~message)
     ~@body))

(defmacro Then
  [message & body]
  `(testing (str "\n  Then " ~message)
     ~@body))

