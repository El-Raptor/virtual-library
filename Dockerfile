FROM ubuntu:latest
LABEL authors="Raptor"

ENTRYPOINT ["top", "-b"]