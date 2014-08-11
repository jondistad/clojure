(in-ns 'clojure.core)

(defn- iobj-impl [tagname fields]
  (let [meta-gs (gensym)]
   (list `(meta [_] ~'__meta)
         `(withMeta [_ ~meta-gs] (new ~tagname ~@(replace {'__meta meta-gs} fields))))))

(defmacro deftype+meta
  [name fields & opts+specs]
  (validate-fields fields)
  (let [gname name
        [interfaces methods opts] (parse-opts+specs opts+specs)
        interfaces (conj interfaces 'clojure.lang.IObj)
        hinted-fields (conj fields '__meta '__extmap)
                                                ; compiler fails without both __meta and __extmap
        methods (concat methods (iobj-impl gname hinted-fields))
        fields (vec (map #(with-meta % nil) fields))
        ns-part (namespace-munge *ns*)
        classname (symbol (str ns-part "." gname))]
    `(let []
       (deftype* ~gname ~classname ~(vec hinted-fields)
         :implements ~(vec interfaces)
         ~@methods)
       (import ~classname)
       ~(build-positional-factory gname classname fields)
       ~classname)))

(declare pv->tv)

(deftype PersistentVector
    [meta cnt shift root tail ^:volatile-mutable __hash]

  clojure.lang.IPersistentVector
  (length [_] cnt)
  (assocN [this ^int i val])
  (cons [this o])
  
  clojure.lang.IEditableCollection
  (asTransient [this] (pv->tv this))
  
  )
