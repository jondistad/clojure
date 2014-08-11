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

rm -rf "$BUILD_DIR"
mkdir -p "$BUILD_DIR"
CLJ_JAR="/Users/jon/.m2/repository/org/clojure/clojure/1.7.0-master-SNAPSHOT/clojure-1.7.0-master-SNAPSHOT.jar"

if [[ "$1" == "repl" ]]; then
    java -cp src/clj:"$CLJ_JAR" clojure.main
else 
    java -cp src/clj:"$CLJ_JAR" -Dclojure.compile.path="$BUILD_DIR" clojure.lang.Compile $(echo "$NAMESPACES" | xargs)
fi


ant compile-java
