#!/usr/bin/env bash

set -x

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

java -cp /Users/jon/.m2/repository/org/clojure/clojure/1.6.0/clojure-1.6.0.jar:src/clj -Dclojure.compile.path=target clojure.lang.Compile $(echo "$NAMESPACES" | xargs)
