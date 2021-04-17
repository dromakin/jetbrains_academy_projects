#!/bin/bash

# using read command without any variable

echo "1. Cinema : "
commando1="java ./Cinema/src/cinema/Cinema.java"
echo "2. SimpleBot : "
commando2="java ./SimpleBot/src/bot/SimpleBot.java"
echo "Enter number of project : "

read

# echo "Name : $REPLY"

if [ $REPLY  -eq  1 ]
then
    echo "Welcome to Cinema project!"
    eval $commando1
elif [ $REPLY  -eq  2 ]
then
    echo "Welcome to SimpleBot project!"
    eval $commando2
else
    echo "Error input. Rerun program please."
fi