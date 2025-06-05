#!/bin/bash
echo "Compilando o projeto..."
mkdir -p bin
javac -cp ".:lib/*" -d bin @sources.txt

if [ $? -ne 0 ]; then
    echo "Erro na compilacao!"
    read -p "Pressione Enter para continuar..."
    exit 1
fi

echo "Executando o programa..."
java -cp ".:bin:lib/*" src.Main
read -p "Pressione Enter para continuar..." 