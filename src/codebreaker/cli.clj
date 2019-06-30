(ns codebreaker.cli)

(defn- exceeded-attempts?
  [attempt]
  (< 8 attempt))

(defn start
  ([]
   (start nil))
  ([{:as game
     :keys [code]}]
   (println "Welcome!")
   (when game
     (loop [attempt 1]
       (if-not (exceeded-attempts? attempt)
         (do
           (print "> ")
           (flush)
           (when-let [guess (read-line)]
             (if (= code guess)
               (println "You won!")
               (recur (inc attempt)))))
         (println "You lost!"))))))
