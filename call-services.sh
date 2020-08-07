#!/bin/sh

/jdk8u265-b01-jre/bin/java -XX:MinHeapFreeRatio=20 -XX:MaxHeapFreeRatio=40 -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -jar /usr/local/lib/evolution-of-moths-1.0.0.jar &

exec "$@"
