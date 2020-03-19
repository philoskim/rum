(ns rum.examples.self-reference
  (:require
   [rum.core :as rum]))


;; Self-referencing component


(rum/defc self-reference < rum/static
  ([form] (self-reference form 0))
  ([form depth]
   (let [offset {:style {:margin-left (* 10 depth)}}]
     (if (sequential? form)
       [:.branch offset (map #(rum/with-key (self-reference % (inc depth)) (str %)) form)]
       [:.leaf   offset (str form)]))))

#?(:cljs
   (defn mount! [mount-el]
     (rum/hydrate (self-reference [:a [:b [:c :d [:e] :g]]]) mount-el)))
