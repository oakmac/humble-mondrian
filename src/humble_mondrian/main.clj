(ns humble-mondrian.main
  (:require
    [io.github.humbleui.paint :as paint]
    [io.github.humbleui.ui :as ui]
    [io.github.humbleui.window :as window]))

(set! *warn-on-reflection* true)

(def dark-grey 0xff404040)
(def blue 0xff0d7fbe)
(def yellow 0xfffae317)
(def red 0xfff50f0f)
(def white 0xfff3f3f3)
(def black 0xff000000)

(def outside-padding 15)
(def inner-padding 10)

(defonce *window
  (atom nil))

(defn redraw []
  (some-> @*window window/request-frame))

;; TODO: is there an empty component I can use instead of label = "" ?
(defn Box [color]
  (ui/rect (paint/fill color) (ui/label "")))

(def LeftColumn
  (ui/column
    [:stretch 1 (ui/row
                  [:stretch 130 (Box white)]
                  (ui/gap inner-padding 0)
                  [:stretch 220 (Box white)])]
    (ui/gap 0 inner-padding)
    [:stretch 8 (ui/row
                  [:stretch 9 (ui/column
                                [:stretch 2 (Box white)]
                                (ui/gap 0 inner-padding)
                                [:stretch 3 (Box white)]
                                (ui/gap 0 inner-padding)
                                [:stretch 3 (Box yellow)])]
                  (ui/gap inner-padding 0)
                  [:stretch 27 (ui/column
                                 [:stretch 1 (Box red)]
                                 (ui/gap 0 inner-padding)
                                 [:stretch 1 (ui/row
                                              [:stretch 1
                                                (ui/column
                                                  [:stretch 1 (Box black)]
                                                  (ui/gap 0 inner-padding)
                                                  [:stretch 1 (Box white)])]
                                              (ui/gap inner-padding 0)
                                              [:stretch 1
                                                (ui/column
                                                  [:stretch 1 (Box white)]
                                                  (ui/gap 0 inner-padding)
                                                  [:stretch 1 (Box white)]
                                                  (ui/gap 0 inner-padding)
                                                  [:stretch 1 (Box black)]
                                                  (ui/gap 0 inner-padding)
                                                  ;; "cheat" here and create two boxes, the second of which
                                                  ;; gets pushed down below it's container.
                                                  ;; If you do not do this, then there is sometimes a one-pixel bug
                                                  ;; between these boxes and their continuation below.
                                                  [:stretch 1
                                                    (ui/row
                                                      [:stretch 1
                                                        (ui/stack
                                                         (Box yellow)
                                                         (ui/valign 0.1 1 (Box yellow)))]
                                                      (ui/gap inner-padding 0)
                                                      [:stretch 1
                                                        (ui/stack
                                                         (Box white)
                                                         (ui/valign 0.1 1 (Box white)))])])])])])]
    (ui/row
      [:stretch 9 (Box dark-grey)]
      (ui/gap inner-padding 0)
      [:stretch 27 (ui/row
                      [:stretch 1 (Box dark-grey)]
                      (ui/gap inner-padding 0)
                      [:stretch 1 (ui/row
                                    [:stretch 1 (Box yellow)]
                                    (ui/gap inner-padding 0)
                                    [:stretch 1 (Box white)])])])
    [:stretch 1
     (ui/row
      [:stretch 9 (Box white)]
      (ui/rect (paint/fill white)
        (ui/gap inner-padding 0))
      [:stretch 27 (ui/row
                      [:stretch 1 (Box white)]
                      (ui/gap inner-padding 0)
                      [:stretch 1
                       (ui/row
                        [:stretch 1 (Box yellow)]
                        (ui/gap inner-padding 0)
                        [:stretch 1 (Box white)])])])]))

(def CenterTopLeft
  (ui/column
    [:stretch 1 (Box yellow)]
    (ui/gap 0 inner-padding)
    [:stretch 2 (Box yellow)]
    (ui/gap 0 inner-padding)
    [:stretch 2 (ui/row
                  [:stretch 1 (Box white)]
                  (ui/gap inner-padding 0)
                  [:stretch 1 (Box white)])]))

(def CenterTopRight
  (ui/column
    [:stretch 2 (Box blue)]
    (ui/gap 0 inner-padding)
    [:stretch 2 (Box red)]
    (ui/gap 0 inner-padding)
    [:stretch 1 (Box white)]))

(def CenterBottomLeft
  (ui/column
    [:stretch 1 (Box white)]
    (ui/gap 0 inner-padding)
    [:stretch 2 (Box blue)]
    (ui/gap 0 inner-padding)
    [:stretch 2 (Box red)]))

(def CenterBottomRight
  (ui/column
    [:stretch 2 (ui/row
                  [:stretch 1 (Box white)]
                  (ui/gap inner-padding 0)
                  [:stretch 1 (Box white)])]
    (ui/gap 0 inner-padding)
    [:stretch 2 (Box yellow)]
    (ui/gap 0 inner-padding)
    [:stretch 1 (ui/row
                  [:stretch 1 (Box white)]
                  (ui/gap inner-padding 0)
                  [:stretch 1 (Box black)])]))

(def CenterTop
  (ui/row
    [:stretch 1 CenterTopLeft]
    (ui/gap inner-padding 0)
    [:stretch 1 CenterTopRight]))

(def CenterBottom
  (ui/row
    [:stretch 1 CenterBottomLeft]
    (ui/gap inner-padding 0)
    [:stretch 1 CenterBottomRight]))

(def CenterColumn
  (ui/column
    [:stretch 1 CenterTop]
    (ui/gap 0 inner-padding)
    [:stretch 1 CenterBottom]))

(def RightColumnTop
  (ui/row
    [:stretch 1 (Box yellow)]
    (ui/gap inner-padding 0)
    [:stretch 1 (Box black)]
    (ui/gap inner-padding 0)
    [:stretch 4 (Box white)]))

(def RightColumnCenter
  (ui/row
    [:stretch 4
      (ui/column
        [:stretch 1 (ui/row
                      [:stretch 1 (ui/column
                                    [:stretch 1 (Box yellow)]
                                    (ui/gap 0 inner-padding)
                                    [:stretch 1 (Box black)]
                                    (ui/gap 0 inner-padding)
                                    [:stretch 1 (Box white)]
                                    (ui/gap 0 inner-padding)
                                    [:stretch 1 (Box white)])]
                      (ui/gap inner-padding 0)
                      [:stretch 1 (ui/column
                                    [:stretch 1 (Box white)]
                                    (ui/gap 0 inner-padding)
                                    [:stretch 1 (Box black)])])]
        (ui/gap 0 inner-padding)
        [:stretch 1 (Box blue)])]
    (ui/gap inner-padding 0)
    [:stretch 1
      (ui/column
        [:stretch 3 (Box yellow)]
        (ui/gap 0 inner-padding)
        [:stretch 3 (Box white)]
        (ui/gap 0 inner-padding)
        [:stretch 2 (Box white)])]))

(def RightColumnBottom
  (ui/row
    [:stretch 3 (Box white)]
    (ui/gap inner-padding 0)
    [:stretch 2 (Box white)]))

(def RightColumn
  (ui/column
    [:stretch 1 RightColumnTop]
    (ui/gap 0 inner-padding)
    [:stretch 8 RightColumnCenter]
    (ui/gap 0 inner-padding)
    [:stretch 1 RightColumnBottom]))

(def MondrianApp
  (ui/default-theme {}
    (ui/rect (paint/fill dark-grey)
      (ui/padding outside-padding
        (ui/row
          [:stretch 1 LeftColumn]
          (ui/gap inner-padding 0)
          [:stretch 1 CenterColumn]
          (ui/gap inner-padding 0)
          [:stretch 1 RightColumn])))))

;; re-draw the UI when we load this namespace
(redraw)

(defn -main [& args]
  (ui/start-app!
    (reset! *window
      (ui/window
        {:title    "HumbleUI Mondrian"
         :bg-color 0xFFFFFFFF}
        #'MondrianApp))))
