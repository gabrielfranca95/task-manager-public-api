# Usando a imagem base do Clojure com Leiningen já instalado
FROM clojure:lein

# Configura o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para o contêiner
COPY . /app

# Instala dependências do Clojure (caso necessário)
# RUN lein deps

# Expondo a porta
EXPOSE 3000

# Adiciona variáveis de ambiente do banco de dados
# NO RENDER, essas variáveis serão configuradas diretamente no painel (não use localhost no DB_HOST)
ENV DB_PORT=5432 \
    DB_NAME=task_manager \
    DB_USER=user \
    DB_PASSWORD=password

# Comando para rodar o servidor
CMD ["lein", "run"]
