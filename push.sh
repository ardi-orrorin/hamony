#!/bin/sh

echo "Git Push Script Start"

git add .
git commit -m "${1:-'update'}"
git push

echo "Git Push Script End"