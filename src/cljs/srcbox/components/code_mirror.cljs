(ns srcbox.components.code-mirror
  (:require [code-mirror]
            [reagent.core :as reagent]))

(defn editor
  [{:keys [value mode on-before-change on-change]}]
  (reagent/create-class
    {:display-name "code-mirror-editor"
     :component-did-mount
                   (fn [this]
                     (let [el (reagent/dom-node this)
                           cm (code-mirror. el (clj->js {:lineNumbers true
                                                         :value       @value
                                                         :mode        mode
                                                         :theme       "darcula"
                                                         :height      "auto"}))]
                       (.on cm "beforeChange" (fn [cm change-object]
                                                (when on-before-change (on-before-change cm change-object (.. cm getDoc getValue)))))
                       (.on cm "change" (fn [cm change-object]
                                          (when on-change (on-change cm change-object (.. cm getDoc getValue)))))))
     :reagent-render
                   (fn []
                     [:div.code-mirror-editor])}))
