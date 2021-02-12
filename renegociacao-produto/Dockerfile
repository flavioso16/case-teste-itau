FROM openjdk:11-oraclelinux7

LABEL source="https://github.com/flavioso16/case-teste-itau" \
      maintainer="flavioso16@gmail.com"

ADD ./build/libs/renegociacao-produto-0.0.1-SNAPSHOT.jar renegociacao-produto.jar
ADD ./docker-entrypoint.sh /

RUN chmod +x /docker-entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/docker-entrypoint.sh"]