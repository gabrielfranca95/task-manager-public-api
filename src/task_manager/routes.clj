(ns task-manager.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer [response status]]
            [cheshire.core :as json]
            [task-manager.db :as db]
            [clojure.java.jdbc :as jdbc]))

;; Endpoints
(defroutes app-routes
           ;; Criar uma nova tarefa
           (POST "/tasks" req
             (let [task (json/parse-string (slurp (:body req)) true)]
               (if (contains? task :titulo)
                 (let [id (str (java.util.UUID/randomUUID)) ; Gera um UUID como string
                       new-task (assoc task :id id :status "pendente")]
                   (jdbc/insert! db/db-spec :tasks new-task)
                   (response (json/generate-string new-task)))
                 (-> (response (json/generate-string {:error "O campo 'titulo' é obrigatório"}))
                     (status 400)))))

           ;; Listar todas as tarefas
           (GET "/tasks" []
             (let [tasks (jdbc/query db/db-spec ["SELECT * FROM tasks"])]
               (if (empty? tasks)
                 (response (json/generate-string {:message "Nenhuma tarefa encontrada"}))
                 (response (json/generate-string tasks)))))

           ;; Atualizar uma tarefa
           (PUT "/tasks/:id" [id :as req]
             (if-let [task (first (jdbc/query db/db-spec ["SELECT * FROM tasks WHERE id = ?" (java.util.UUID/fromString id)]))]
               (let [updated-task (merge task (json/parse-string (slurp (:body req)) true))]
                 (jdbc/update! db/db-spec :tasks updated-task ["id = ?" id])
                 (response (json/generate-string updated-task)))
               (-> (response (json/generate-string {:error "Tarefa não encontrada"}))
                   (status 404))))

           ;; Deletar uma tarefa
           (DELETE "/tasks/:id" [id]
             (if-let [task (first (jdbc/query db/db-spec ["SELECT * FROM tasks WHERE id = ?" (java.util.UUID/fromString id)]))]
               (do
                 (jdbc/delete! db/db-spec :tasks ["id = ?" id])
                 (response (json/generate-string {:message "Tarefa deletada"})))
               (-> (response (json/generate-string {:error "Tarefa não encontrada"}))
                   (status 404))))

           ;; Rota de erro para endpoints não definidos
           (route/not-found (json/generate-string {:error "Endpoint não encontrado"})))
