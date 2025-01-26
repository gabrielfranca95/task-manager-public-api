(ns task-manager.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [task-manager.routes :refer [app-routes]]
            [task-manager.db :as db]))

(defn -main []
  ;; Inicializa o banco de dados
  (db/init-db)
  ;; Pega a porta da variável de ambiente ou usa 3000 por padrão
  (let [port (Integer. (or (System/getenv "PORT") "3000"))]
    (println "Server is running on port:" port) ;; Exibe a porta no console para facilitar o diagnóstico
    ;; Inicia o servidor
    (run-jetty app-routes {:port port :join? false})))
