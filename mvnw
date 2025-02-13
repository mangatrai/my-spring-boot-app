#!/bin/sh
set -e

MAVEN_VERSION=3.8.6
MVN_CMD="mvn"

if [ ! -f "$HOME/.m2/repository/org/apache/maven/apache-maven/$MAVEN_VERSION/apache-maven-$MAVEN_VERSION-bin.zip" ]; then
  echo "Downloading Maven $MAVEN_VERSION..."
  curl -L -o /tmp/apache-maven-$MAVEN_VERSION-bin.zip https://archive.apache.org/dist/maven/binaries/apache-maven-$MAVEN_VERSION-bin.zip
  echo "Installing Maven..."
  unzip -q /tmp/apache-maven-$MAVEN_VERSION-bin.zip -d $HOME/.m2/repository/org/apache/maven/apache-maven/$MAVEN_VERSION/
  rm /tmp/apache-maven-$MAVEN_VERSION-bin.zip
fi

export MAVEN_HOME="$HOME/.m2/repository/org/apache/maven/apache-maven/$MAVEN_VERSION/apache-maven-$MAVEN_VERSION"
export PATH="$MAVEN_HOME/bin:$PATH"

exec "$MVN_CMD" "$@"