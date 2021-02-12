#!/bin/bash

WORKDIR=$(pwd)

echo $WORKDIR

echo "############## Building Discovery ##############"
cd $WORKDIR/renegociacao-discovery
./gradlew clean build

echo "############## Building Gateway ##############"
cd $WORKDIR/renegociacao-gateway
./gradlew clean build


echo "############## Building Renegociacao ##############"
cd $WORKDIR/renegociacao
./gradlew clean build

echo "############## Building Renegociacao Produto ##############"
cd $WORKDIR/renegociacao-produto
./gradlew clean build
