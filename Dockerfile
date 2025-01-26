# Usando a imagem base do Clojure com Leiningen já instalado
FROM clojure:lein

# Configura o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para o contêiner
COPY . /app

# Expondo a porta
EXPOSE 3000

# Adiciona variáveis de ambiente do banco de dados (serão configuradas no Render)
ENV DB_PORT=5432 \
    DB_NAME=task_manager \
    DB_USER=user \
    DB_PASSWORD=password

# Comando para rodar o servidor
CMD ["lein", "run"]
