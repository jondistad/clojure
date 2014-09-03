/**
 *   Copyright (c) Rich Hickey. All rights reserved.
 *   The use and distribution terms for this software are covered by the
 *   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *   which can be found in the file epl-v10.html at the root of this distribution.
 *   By using this software in any fashion, you are agreeing to be bound by
 * 	 the terms of this license.
 *   You must not remove this notice, or any other, from this software.
 **/

package clojure.lang;

import java.io.Serializable;
import java.util.*;

public abstract class ASeq extends Obj implements ASeq_STAR_ {

final ASeq_impl _inner_aseq;

public String toString(){
	return _inner_aseq.toString();
}

public IPersistentCollection empty(){
	return _inner_aseq.empty();
}

protected ASeq(IPersistentMap meta) {
	super(meta);
	this._inner_aseq = new ASeq_impl(-1, -1);
}


protected ASeq(){
	super(null);
	this._inner_aseq = new ASeq_impl(-1, -1);
}

public boolean equiv(Object obj){
	return _inner_aseq.equals(obj);
}

public boolean equals(Object obj){
	return _inner_aseq.equals(obj);
}

public int hashCode(){
	return _inner_aseq.hashCode();
}

public int hasheq(){
	return _inner_aseq.hasheq();
}


//public Object reduce(IFn f) {
//	Object ret = first();
//	for(ISeq s = rest(); s != null; s = s.rest())
//		ret = f.invoke(ret, s.first());
//	return ret;
//}
//
//public Object reduce(IFn f, Object start) {
//	Object ret = f.invoke(start, first());
//	for(ISeq s = rest(); s != null; s = s.rest())
//		ret = f.invoke(ret, s.first());
//	return ret;
//}

//public Object peek(){
//	return first();
//}
//
//public IPersistentList pop(){
//	return rest();
//}

public int count(){
	return _inner_aseq.count();
}

final public ISeq seq(){
	return this;
}

public ISeq cons(Object o){
	return new PCons(o, this, -1, -1, null);
}

public ISeq more(){
	return next() == null ? PersistentList.EMPTY : next();
}

//final public ISeq rest(){
//    Seqable m = more();
//    if(m == null)
//        return null;
//    return m.seq();
//}

// java.util.Collection implementation

public Object[] toArray(){
	return _inner_aseq.toArray();
}

public boolean add(Object o){
	return _inner_aseq.add(o);
}

public boolean remove(Object o){
	return _inner_aseq.remove(o);
}

public boolean addAll(Collection c){
	return _inner_aseq.addAll(c);
}

public void clear(){
	_inner_aseq.clear();
}

public boolean retainAll(Collection c){
	return _inner_aseq.retainAll(c);
}

public boolean removeAll(Collection c){
	return _inner_aseq.removeAll(c);
}

public boolean containsAll(Collection c){
	return _inner_aseq.containsAll(c);
}

public Object[] toArray(Object[] a){
    return _inner_aseq.toArray(a);
}

public int size(){
	return _inner_aseq.size();
}

public boolean isEmpty(){
	return _inner_aseq.isEmpty();
}

public boolean contains(Object o){
	return _inner_aseq.contains(o);
}


public Iterator iterator(){
	return _inner_aseq.iterator();
}



//////////// List stuff /////////////////

public List subList(int fromIndex, int toIndex){
	return _inner_aseq.subList(fromIndex, toIndex);
}

public Object set(int index, Object element){
	return _inner_aseq.set(index, element);
}

public Object remove(int index){
	return _inner_aseq.remove(index);
}

public int indexOf(Object o){
	return _inner_aseq.indexOf(o);
}

public int lastIndexOf(Object o){
	return _inner_aseq.lastIndexOf(o);
}

public ListIterator listIterator(){
	return _inner_aseq.listIterator();
}

public ListIterator listIterator(int index){
	return _inner_aseq.listIterator(index);
}

public Object get(int index){
	return _inner_aseq.get(index);
}

public void add(int index, Object element){
	_inner_aseq.add(index, element);
}

public boolean addAll(int index, Collection c){
	return _inner_aseq.addAll(index, c);
}

}
