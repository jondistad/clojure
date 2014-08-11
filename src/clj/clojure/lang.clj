(ns clojure.lang)

(defprotocol Seqable
  (^clojure.lang.ISeq seq [_]))
