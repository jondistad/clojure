(ns clojure.lang)

(defprotocol Seqable
  (^{:tag clojure.lang.Seqable :on seq} -seq [s]))

(defprotocol IEquiv
  (^{:tag boolean :on equiv} -equiv [o other]))

(defprotocol ICollection
  (^{:tag ICollection :on cons} -conj [coll o]))

(defprotocol IEmptyableCollection
  (^{:tag IEmptyableCollection :on empty} -empty [coll]))

(defprotocol Counted
  (^{:tag clojure.lang.Counted :on count} -count [coll]))

(union-protocols IPersistentCollection
  Seqable
  IEquiv
  ICollection
  IEmptyableCollection
  Counted)

(defprotocol IFirst
  (^{:on first} -first [_]))

(defprotocol IRest
  (^{:tag clojure.lang.ISeq :on rest} -rest [_]))

(defprotocol INext
  (^{:tag clojure.lang.ISeq :on next} -next [_]))

(union-protocols ISeq
  IFirst
  IRest
  INext)

(defprotocol IMeta
  (^{:tag clojure.lang.IPersistentMap :on meta} -meta [_]))

(defprotocol IWithMeta
  (^{:tag clojure.lang.IObj :on withMeta} -with-meta [_ ^clojure.lang.IPersistentMap m]))

(union-protocols IObj
  IMeta
  IWithMeta)

(defprotocol ILookup
  (^{:on valAt} -val-at [_ o] [_ o not-found]))

(defprotocol IAssociative
  (^{:tag boolean :on containsKey} -contains-key? [_ k])
  (^{:tag clojure.lang.IMapEntry :on entryAt} -entry-at [_ k])
  (^{:tag clojure.lang.Associative :on assoc} -assoc [_ k v]))

(union-protocols Associative
  IPersistentCollection
  ILookup
  IAssociative)

(defprotocol IIndexed
  (^{:on nth} -nth [_ ^int i] [_ ^int i not-found]))

(union-protocols Indexed
  Counted
  IIndexed)

(defprotocol Sequential)

(defprotocol IOrdinal
  (^{:tag int :on index} -index [_]))

(union-protocols IndexedSeq
  ISeq
  Sequential
  Counted
  IOrdinal)

(defprotocol IChunked
  (^{:tag clojure.lang.IChunked :on dropFirst} -drop-first [_])
  (^{:on reduce} -chunk-reduce [_ ^clojure.lang.IFn f start]))

(union-protocols IChunk
  IChunked
  Indexed)

(defprotocol IChunkedFirst
  (^{:tag clojure.lang.IChunked :on chunkedFirst} -chunked-first [_]))

(defprotocol IChunkedNext
  (^{:tag clojure.lang.ISeq :on chunkedNext} -chunked-next [_])
  (^{:tag clojure.lang.ISeq :on chunkedMore} -chunked-more [_]))

(union-protocols IChunkedSeq
  ISeq
  Sequential
  IChunkedFirst
  IChunkedNext)

(defprotocol IEditableCollection
  (^{:tag clojure.lang.ITransientCollection :on asTransient} -transient [_]))

(defprotocol IExceptionInfo
  (^{:tag clojure.lang.IPersistentMap :on getData} -get-data [_]))

(defprotocol IHashEq
  (^{:tag int :on hasheq} -hasheq [_]))

(defprotocol ILookupThunk
  (^{:on get} -lookup-thunk-get [_ o]))
(defprotocol IKeywordLookup
  (^{:tag clojure.lang.ILookupThunk :on getLookupThunk} -get-lookup-thunk [_ ^clojure.lang.Keyword k]))
(defprotocol ILookupSite
  (^{:tag clojure.lang.ILookupThunk :on fault} -fault [_ target]))

(defprotocol Fn)

(defprotocol IDeref
  (^{:on deref} -deref [_]))

(defprotocol IBlockingDeref
  (^{:on deref} -blocking-deref [_ ^long ms timeout]))

(defprotocol IPending
  (^{:tag boolean :on isRealized} -realized? [_]))

(defprotocol IProxy
  (^{:tag void :on __initClojureFnMappings} -init-clojure-fn-mappings! [_ ^clojure.lang.IPersistentMap m])
  (^{:tag void :on __updateClojureFnMappings} -update-clojure-fn-mappings! [_ ^clojure.lang.IPersistentMap m])
  (^{:tag clojure.lang.IPersistentMap :on __getClojureFnMappings} -clojure-fn-mappings [_]))

(wrap-interface java.util.Map$Entry
  JavaMapEntry
  (^{:tag boolean :on equals} -jme-equiv [_ o])
  (^{:on getKey} -jme-key [_])
  (^{:on getValue} -jme-val [_])
  (^{:tag int :on hashCode} -jme-hash-code [_])
  (^{:on setValue} -jme-set-value! [_ v]))

(defprotocol IKeyed
  (^{:on key} -key [_]))
(defprotocol IValued
  (^{:on val} -val [_]))
(union-protocols IMapEntry
  IKeyed
  IValued
  JavaMapEntry)

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
