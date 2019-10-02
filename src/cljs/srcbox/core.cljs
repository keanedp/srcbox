(ns ^:figwheel-hooks srcbox.core
  (:require
    [srcbox.components.code-mirror :as cm]
    [goog.dom :as gdom]
    [reagent.core :as reagent :refer [atom]]))

(defn get-app-element []
  (gdom/getElement "app"))

(def code (reagent/atom "(def m {:id 1 :name \"Test\"})\n\n(defn print-it [s]\n  (println \"Value: \" s))"))

(defn container []
  [:div.editor-container
   [cm/editor {:value code :mode "clojure" :on-before-change (fn [_ _ value]
                                                               (reset! code value)
                                                               (print "updated code: " @code))}]])

(defn mount [el]
  (reagent/render-component [container] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element))
