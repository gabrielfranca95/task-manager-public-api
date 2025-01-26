(ns task-manager.db
  (:require [clojure.java.jdbc :as jdbc]))

;; Configurações do banco de dados (usando variáveis de ambiente)
(def db-spec
  {:dbtype "postgresql"
   :dbname (System/getenv "DB_NAME")
   :host (System/getenv "DB_HOST")
   :port (Integer. (or (System/getenv "DB_PORT") "5432"))
   :user (System/getenv "DB_USER")
   :password (System/getenv "DB_PASSWORD")})

;; Inicializa a tabela "tasks"
(defn init-db []
  (jdbc/execute! db-spec
                 ["CREATE TABLE IF NOT EXISTS tasks (
                   id UUID PRIMARY KEY,
                   titulo TEXT NOT NULL,
                   descricao TEXT,
                   status TEXT NOT NULL DEFAULT 'pendente'
                 )"]))
