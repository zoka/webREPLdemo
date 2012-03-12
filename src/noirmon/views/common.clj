(ns noirmon.views.common
  (use noir.core
       hiccup.core
       hiccup.page-helpers))

;; Links and includes
(def main-links [{:url "/blog/admin" :text "Admin"}])

(def admin-links [{:url "/blog/" :text "Blog"}
                  {:url "/blog/admin" :text "Posts"}
                  {:url "/blog/admin/users" :text "Users"}
                  {:url "/blog/logout" :text "Logout"}])

(def includes {:jquery (include-js "http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js")
               :default (include-css "/css/default.css")
               :reset (include-css "/css/reset.css")
               :blog.js (include-js "/js/blog.js")})

;; Helper partials

(defpartial build-head [incls]
            [:head
             [:title "noirMon"]
             (map #(get includes %) incls)])

(defpartial link-item [{:keys [url cls text]}]
            [:li
             (link-to {:class cls} url text)])

;; Layouts

(defpartial main-layout [& content]
            (html5
              (build-head [:reset :default :jquery :blog.js])
              [:body
               [:div#wrapper
                [:div.content
                 [:div#header
                  [:h1 "The " (link-to "https://github.com/zoka/ringMon" "ringMon")
                  " middleware demo"]
                  [:br] [:br]
                  [:h2 "Click
                  <a target=\"_blank\" href=\"/ringmon/monview.html\">here</a>
                  to open the ringMon monitoring page"]
                  ;[:h3 (link-to "https://github.com/zoka/noirMon" "Get the source code.")]
                  [:ul.nav
                   (map link-item main-links)]]
                 content]]]))

(defpartial admin-layout [& content]
            (html5
              (build-head [:reset :default :jquery :blog.js])
              [:body
               [:div#wrapper
                [:div.content
                 [:div#header
                  [:h1 (link-to "/blog/admin" "Admin")]
                  [:ul.nav
                   (map link-item admin-links)]]
                 content]]]))

