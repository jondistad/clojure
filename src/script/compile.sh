#!/usr/bin/env bash

#set -x

NAMESPACES="
clojure.core
clojure.core.protocols
clojure.main
clojure.set
clojure.edn
clojure.xml
clojure.zip
clojure.inspector
clojure.walk
clojure.stacktrace
clojure.template
clojure.test
clojure.test.tap
clojure.test.junit
clojure.pprint
clojure.java.io
clojure.repl
clojure.java.browse
clojure.java.javadoc
clojure.java.shell
clojure.java.browse-ui
clojure.string
clojure.data
clojure.reflect
clojure.lang
"

BUILD_DIR="$PWD/bootstrap"

CLJ_JAR="/Users/jon/.m2/repository/org/clojure/clojure/1.7.0-master-SNAPSHOT/clojure-1.7.0-master-SNAPSHOT-slim.jar"

if [[ "$1" == "repl" ]]; then
    java -cp src/clj:"$BUILD_DIR":"$PWD/target/classes" clojure.main
else 
    rm -rf "$BUILD_DIR"
    mkdir -p "$BUILD_DIR"

    cd "$BUILD_DIR"
    jar xf "$CLJ_JAR"
    cd -

    java -cp src/clj:"$BUILD_DIR" -Dclojure.compile.path="$BUILD_DIR" clojure.lang.Compile $(echo "$NAMESPACES" | xargs)
fi


ant compile-java
