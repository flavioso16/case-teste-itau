# Projeto Health2Gether Fiap - 69AOJ

## Resumo

Portas:

```
renegociacao-discovery:8761
renegociacao-gateway:9091

renegociacao:8080
renegociacao-produto:8081

```

## Build projetos

```
./build-projects.sh

```

## Deploy

Iniciando aplicações:

```
sudo docker-compose up -d --build
```

Parando aplicações:

```
sudo docker-compose down
```

Visualizando logs:

```
sudo docker-compose logs -f
```

Rodando no docker swarm
```
docker stack deploy --compose-file docker-compose.yml stackdemo
```