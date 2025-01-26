(ns task-manager.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [task-manager.routes :refer [app-routes]]
            [task-manager.db :as db]))

(defn -main []
  ;; Inicializa o banco de dados
  (db/init-db)
  ;; Inicia o servidor
  (run-jetty app-routes {:port 3000 :join? false}))
