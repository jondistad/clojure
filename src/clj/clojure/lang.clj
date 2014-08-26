(ns clojure.lang)

(declare-protocol ISeq)

(defprotocol IFirst
  (^{:on first} -first [_]))

(defprotocol IRest
  (^{:tag ISeq :on more} -rest [_]))

(defprotocol INext
  (^{:tag ISeq :on next} -next [_]))

(defprotocol Seqable
  (^{:tag ISeq :on seq} -seq [s]))

(defprotocol IEquiv
  (^{:tag boolean :on equiv} -equiv [o other]))

(declare-protocol IPersistentCollection)

(defprotocol IConjoinable
  (^{:tag IPersistentCollection :on cons} -conj [coll o]))

(defprotocol IEmptyableCollection
  (^{:tag IPersistentCollection :on empty} -empty [coll]))

(defprotocol Counted
  (^{:tag int :on count} -count [coll]))

(union-protocols IPersistentCollection
  Seqable
  IEquiv
  IConjoinable
  IEmptyableCollection
  Counted)

(union-protocols ISeq
  IFirst
  IRest
  INext
  IPersistentCollection)

(declare-protocol IObj)
(declare-protocol IPersistentMap)

(defprotocol IMeta
  (^{:tag IPersistentMap :on meta} -meta [_]))

(defprotocol IWithMeta
  (^{:tag IObj :on withMeta} -with-meta [_ ^IPersistentMap m]))

(union-protocols IObj
  IMeta
  IWithMeta)

(defprotocol ILookup
  (^{:on valAt} -val-at [_ o] [_ o not-found]))

(declare-protocol Associative)
(declare-protocol IMapEntry)

(defprotocol IAssociative
  (^{:tag boolean :on containsKey} -contains-key? [_ k])
  (^{:tag IMapEntry :on entryAt} -entry-at [_ k])
  (^{:tag Associative :on assoc} -assoc [_ k v]))

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

(declare-protocol IChunk)

(defprotocol IChunked
  (^{:tag IChunk :on dropFirst} -drop-first [_])
  (^{:on reduce} -chunk-reduce [_ ^clojure.lang.IFn f start]))

(union-protocols IChunk
  IChunked
  Indexed)

(defprotocol IChunkedFirst
  (^{:tag IChunk :on chunkedFirst} -chunked-first [_]))

(defprotocol IChunkedNext
  (^{:tag ISeq :on chunkedNext} -chunked-next [_])
  (^{:tag ISeq :on chunkedMore} -chunked-rest [_]))

(union-protocols IChunkedSeq
  ISeq
  Sequential
  IChunkedFirst
  IChunkedNext)

(defprotocol IExceptionInfo
  (^{:tag IPersistentMap :on getData} -get-data [_]))

(defprotocol IHashEq
  (^{:tag int :on hasheq} -hasheq [_]))

(defprotocol ILookupThunk
  (^{:on get} -lookup-thunk-get [_ o]))
(defprotocol IKeywordLookup
  (^{:tag ILookupThunk :on getLookupThunk} -get-lookup-thunk [_ ^clojure.lang.Keyword k]))
(defprotocol ILookupSite
  (^{:tag ILookupThunk :on fault} -fault [_ target]))

(defprotocol Fn)

(defprotocol IDeref
  (^{:on deref} -deref [_]))

(defprotocol IBlockingDeref
  (^{:on deref} -blocking-deref [_ ^long ms timeout]))

(declare-protocol IRef)
(defprotocol IValidated
  (^{:tag void :on setValidator} -set-validator! [_ ^clojure.lang.IFn vf])
  (^{:tag clojure.lang.IFn :on getValidator} -validator [_]))
(defprotocol IWatched
  (^{:tag IPersistentMap :on getWatches} -watches [_])
  (^{:tag IRef :on addWatch} -add-watch! [_ key ^clojure.lang.IFn callback])
  (^{:tag IRef :on removeWatch} -remove-watch! [_ key]))

(union-protocols IRef
  IDeref
  IValidated
  IWatched)

(defprotocol IRefMeta
  (^{:tag IPersistentMap :on alterMeta} -alter-meta! [_ ^clojure.lang.IFn alter ^ISeq args])
  (^{:tag IPersistentMap :on resetMeta} -reset-meta! [_ ^IPersistentMap m]))

(union-protocols IReference
  IMeta
  IRefMeta)

(defprotocol IPending
  (^{:tag boolean :on isRealized} -realized? [_]))

(defprotocol IProxy
  (^{:tag void :on __initClojureFnMappings} -init-clojure-fn-mappings! [_ ^IPersistentMap m])
  (^{:tag void :on __updateClojureFnMappings} -update-clojure-fn-mappings! [_ ^IPersistentMap m])
  (^{:tag IPersistentMap :on __getClojureFnMappings} -clojure-fn-mappings [_]))

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

(declare-protocol IPersistentStack)

(defprotocol IStack
  (^{:on peek} -peek [_])
  (^{:tag IPersistentStack :on pop} -pop [_]))

(union-protocols IPersistentStack
  IStack
  IPersistentCollection)

(union-protocols IPersistentList
  Sequential
  IPersistentStack)

(defprotocol IMap
  (^{:tag IPersistentMap :on assoc} -map-assoc [_ k v])
  (^{:tag IPersistentMap :on assocEx} -assoc-ex [_ k v])
  (^{:tag IPersistentMap :on without} -dissoc [_ k]))

(wrap-interface Iterable
  JavaIterable
  (^{:tag java.util.Iterator :on iterator} -iterator [_]))

(union-protocols IPersistentMap
  JavaIterable
  Associative
  Counted
  IMap)

(declare-protocol IPersistentSet)

(defprotocol ISet
  (^{:tag boolean :on contains} -contains [_ k])
  (^{:on get} -get [_ k]))

(defprotocol IPersistentDisjoin
  (^{:tag IPersistentSet :on disjoin} -disjoin [_ k]))

(union-protocols IPersistentSet
  Counted
  IPersistentCollection
  ISet
  IPersistentDisjoin)

(defprotocol Reversible
  (^{:tag ISeq :on rseq} -rseq [_]))

(declare-protocol IPersistentVector)

(defprotocol IVector
  (^{:tag IPersistentVector :on assocN} -assoc-n [_ ^int i val])
  (^{:tag IPersistentVector :on cons} -vec-conj [_ o]))

(union-protocols IPersistentVector
  Associative
  Sequential
  IPersistentStack
  Reversible
  Indexed
  IVector)

(defprotocol IType)
(defprotocol IRecord)
(defprotocol MapEquivalence)

(defprotocol Named
  (^{:tag String :on getNamespace} -namespace [_])
  (^{:tag String :on getName} -name [_]))

(defprotocol Settable
  (^{:on doSet} -set! [_ val])
  (^{:on doReset} -reset! [_ val]))

(defprotocol Sorted
  (^{:tag java.util.Comparator :on comparator} -comparator [_])
  (^{:on entryKey} -entry-key [_ entry])
  (^{:tag ISeq :on seq} -sorted-seq [_ ^boolean ascending])
  (^{:tag ISeq :on seqFrom} -sorted-seq-from [_ key ^boolean ascending]))

(defprotocol IReduce
  (^{:on reduce} -reduce [_ ^clojure.lang.IFn f] [_ ^clojure.lang.IFn f start]))

(defprotocol ITransientCollection
  (^{:tag this :on conj} -conj! [_ o])
  (^{:tag IPersistentCollection :on persistent} -persistent! [_]))

(defprotocol IEditableCollection
  (^{:tag ITransientCollection :on asTransient} -as-transient [_]))

(declare-protocol ITransientAssociative)

(defprotocol ITransientAssoc
  (^{:tag ITransientAssociative :on assoc} -assoc! [_ k v]))

(union-protocols ITransientAssociative
  ITransientCollection
  ILookup
  ITransientAssoc)

(declare-protocol ITransientMap)

(defprotocol ITransientMapAssoc
  (^{:tag ITransientMap :on assoc} -map-assoc! [_ k v])
  (^{:tag ITransientMap :on without} -map-dissoc! [_ k]))
(defprotocol ITransientMapPersist
  (^{:tag IPersistentMap :on persistent} -map-persistent! [_]))

(union-protocols ITransientMap
  ITransientAssociative
  Counted
  ITransientMapAssoc
  ITransientMapPersist)

(declare-protocol ITransientSet)

(defprotocol ITransientDisjoin
  (^{:tag ITransientSet :on disjoin} -disjoin! [_ k]))

(union-protocols ITransientSet
  ITransientCollection
  Counted
  ISet
  ITransientDisjoin)

(declare-protocol ITransientVector)

(defprotocol ITransientVec
  (^{:tag ITransientVector :on assocN} -assoc-n! [_ ^int i val])
  (^{:tag ITransientVector :on pop} -pop! [_]))

(union-protocols ITransientVector
  ITransientAssociative
  Indexed
  ITransientVec)

;; Abstract Classes

(wrap-interface java.io.Serializable
  JavaSerializable)

(union-protocols Obj
  IObj
  JavaSerializable)

(defmacro add-protocol-defaults
  [name fields & methods]
  (when-not (and (var? (resolve name))
                 (:on-interface @(resolve name)))
    (throw (IllegalArgumentException. (str name " is not a protocol."))))
  (when-not (vector? fields)
    (throw (IllegalArgumentException. "Fields must be a vector.")))
  `(alter-var-root (var ~name) assoc
                   :default-fields '[~@fields]
                   :default-methods [~@methods]))
(defmacro deftype-ext
  [name fields & opts+sigs]
  )

