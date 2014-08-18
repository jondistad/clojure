(ns clojure.lang)

(defprotocol Seqable
  (^{:tag clojure.lang.ISeq :on seq} -seq [_]))

(defprotocol IPersistentCollection
  :continues [Seqable]
  (^{:tag int :on count} -coll-count [_])
  (^{:tag clojure.lang.IPersistentCollection :on cons} -conj [_ o])
  (^{:tag clojure.lang.IPersistentCollection :on empty} -empty [_])
  (^{:tag boolean :on equiv} -equiv [_ o]))

(defprotocol ISeq
  :continues [IPersistentCollection]
  (^{:on first} -first [_])
  (^{:tag clojure.lang.ISeq :on next} -next [_])
  (^{:tag clojure.lang.ISeq :on more} -rest [_]))


(comment

 (defprotocol Fn
   "Marker protocol")

 (defprotocol IFn
   (-invoke
     [this]
     [this a]
     [this a b]
     [this a b c]
     [this a b c d]
     [this a b c d e]
     [this a b c d e f]
     [this a b c d e f g]
     [this a b c d e f g h]
     [this a b c d e f g h i]
     [this a b c d e f g h i j]
     [this a b c d e f g h i j k]
     [this a b c d e f g h i j k l]
     [this a b c d e f g h i j k l m]
     [this a b c d e f g h i j k l m n]
     [this a b c d e f g h i j k l m n o]
     [this a b c d e f g h i j k l m n o p]
     [this a b c d e f g h i j k l m n o p q]
     [this a b c d e f g h i j k l m n o p q s]
     [this a b c d e f g h i j k l m n o p q s t]
     [this a b c d e f g h i j k l m n o p q s t rest]))

 (defprotocol ICloneable
   (^clj -clone [value]))

 (defprotocol ICounted
   (^number -count [coll] "constant time count"))

 (defprotocol IEmptyableCollection
   (-empty [coll]))

 (defprotocol ICollection
   (^clj -conj [coll o]))

 #_(defprotocol IOrdinal
     (-index [coll]))

 (defprotocol IIndexed
   (-nth [coll n] [coll n not-found]))

 (defprotocol ASeq)

 (defprotocol ISeq
   (-first [coll])
   (^clj -rest [coll]))

 (defprotocol INext
   (^clj-or-nil -next [coll]))

 (defprotocol ILookup
   (-lookup [o k] [o k not-found]))

 (defprotocol IAssociative
   (^boolean -contains-key? [coll k])
   #_(-entry-at [coll k])
   (^clj -assoc [coll k v]))

 (defprotocol IMap
   #_(-assoc-ex [coll k v])
   (^clj -dissoc [coll k]))

 (defprotocol IMapEntry
   (-key [coll])
   (-val [coll]))

 (defprotocol ISet
   (^clj -disjoin [coll v]))

 (defprotocol IStack
   (-peek [coll])
   (^clj -pop [coll]))

 (defprotocol IVector
   (^clj -assoc-n [coll n val]))

 (defprotocol IDeref
   (-deref [o]))

 (defprotocol IDerefWithTimeout
   (-deref-with-timeout [o msec timeout-val]))

 (defprotocol IMeta
   (^clj-or-nil -meta [o]))

 (defprotocol IWithMeta
   (^clj -with-meta [o meta]))

 (defprotocol IReduce
   (-reduce [coll f] [coll f start]))

 (defprotocol IKVReduce
   (-kv-reduce [coll f init]))

 (defprotocol IEquiv
   (^boolean -equiv [o other]))

 (defprotocol IHash
   (-hash [o]))

 (defprotocol ISeqable
  (^clojure.lang.ISeq -seq [o]))

 (defprotocol ISequential
   "Marker interface indicating a persistent collection of sequential items")

 (defprotocol IList
   "Marker interface indicating a persistent list")

 (defprotocol IRecord
   "Marker interface indicating a record object")

 (defprotocol IReversible
   (^clj -rseq [coll]))

 (defprotocol ISorted
   (^clj -sorted-seq [coll ascending?])
   (^clj -sorted-seq-from [coll k ascending?])
   (-entry-key [coll entry])
   (-comparator [coll]))

 (defprotocol IWriter
   (-write [writer s])
   (-flush [writer]))

 (defprotocol IPrintWithWriter
  "The old IPrintable protocol's implementation consisted of building a giant
  list of strings to concatenate.  This involved lots of concat calls,
  intermediate vectors, and lazy-seqs, and was very slow in some older JS
  engines.  IPrintWithWriter implements printing via the IWriter protocol, so it
  be implemented efficiently in terms of e.g. a StringBuffer append."
  (-pr-writer [o writer opts]))

 (defprotocol IPending
   (^boolean -realized? [d]))

 (defprotocol IWatchable
   (-notify-watches [this oldval newval])
   (-add-watch [this key f])
   (-remove-watch [this key]))

 (defprotocol IEditableCollection
   (^clj -as-transient [coll]))

 (defprotocol ITransientCollection
   (^clj -conj! [tcoll val])
   (^clj -persistent! [tcoll]))

 (defprotocol ITransientAssociative
   (^clj -assoc! [tcoll key val]))

 (defprotocol ITransientMap
   (^clj -dissoc! [tcoll key]))

 (defprotocol ITransientVector
   (^clj -assoc-n! [tcoll n val])
   (^clj -pop! [tcoll]))

 (defprotocol ITransientSet
   (^clj -disjoin! [tcoll v]))

 (defprotocol IComparable
   (^number -compare [x y]))

 (defprotocol IChunk
   (-drop-first [coll]))

 (defprotocol IChunkedSeq
   (-chunked-first [coll])
   (-chunked-rest [coll]))

 (defprotocol IChunkedNext
   (-chunked-next [coll]))

 (defprotocol INamed
   (^string -name [x])
   (^string -namespace [x]))

 (defprotocol IAtom)

 (defprotocol IReset
   (-reset! [o new-value]))

 (defprotocol ISwap
   (-swap! [o f] [o f a] [o f a b] [o f a b xs]))

 )
