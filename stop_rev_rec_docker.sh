#!/usr/bin/env bash
CONTAINER_NAME=rev_rec
docker stop ${CONTAINER_NAME} && docker rm -f ${CONTAINER_NAME}
