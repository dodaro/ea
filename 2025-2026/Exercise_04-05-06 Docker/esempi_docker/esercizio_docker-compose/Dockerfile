FROM alpine:latest

RUN apk update && apk upgrade

RUN mkdir -p /condivisa

CMD ping -c 5 google.com > /condivisa/ping.txt 2>&1 && cat /condivisa/ping.txt
