(defproject task-manager "0.1.0-SNAPSHOT"
  :description "Gerenciador de tarefas com PostgreSQL"
  :url "http://example.com/task-manager"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring "1.9.5"]
                 [compojure "1.6.3"]
                 [cheshire "5.11.0"]
                 [ring/ring-mock "0.4.0"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.postgresql/postgresql "42.6.0"]]
  :main ^:skip-aot task-manager.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
