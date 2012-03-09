(ns noirmon.server
  (:require [noir.server     :as server]
            [noirmon.models  :as models]
            [ringmon.monitor :as monitor]))

(server/load-views "src/noirmon/views/")

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
        
    (models/initialize)
    (server/add-middleware monitor/wrap-ring-monitor) ; inject ringMon page
    (server/start port {:mode mode
                        :ns 'noirmon})))

  
