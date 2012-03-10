(ns noirmon.models
  (:require [simpledb.core :as db]
            [noirmon.models.user :as users]
            [noirmon.models.post :as posts]))

(defn initialize []
  (db/init)
  (when-not (db/get :users)
    ;;db values need to be initialized.. this should only happen once.
    (users/init!)
    (posts/init!)))
