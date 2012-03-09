(ns noirmon.views.blog
  (:use noir.core
        hiccup.core
        hiccup.page-helpers)
  (:require [noirmon.models.post :as posts]
            [noirmon.views.common :as common]
            [noirmon.models.user :as user]
            [noir.response :as resp]))

;; Page structure

(defpartial post-item [{:keys [perma-link title md-body date tme] :as post}]
            (when post
              [:li.post
               [:h2 (link-to perma-link title)]
               [:ul.datetime
                [:li date]
                [:li tme]
                (when (user/admin?)
                  [:li (link-to (posts/edit-url post) "edit")])]
               [:div.content md-body]]))

(defpartial blog-page [items]
            (common/main-layout
              [:ul.posts
               (map post-item items)]))

;; Blog pages

(defpage "/" []
         (resp/redirect "/blog/"))

(defpage "/blog/" []
         (blog-page (posts/get-latest)))

(defpage "/blog/page/:page" {:keys [page]}
         (blog-page (posts/get-page page)))

(defpage "/blog/page/" []
         (render "/blog/page/:page" {:page 1}))

(defpage "/blog/view/:perma" {:keys [perma]}
         (if-let [cur (posts/moniker->post perma)]
           (blog-page [cur])))
