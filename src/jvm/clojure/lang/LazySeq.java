/**
 *   Copyright (c) Rich Hickey. All rights reserved.
 *   The use and distribution terms for this software are covered by the
 *   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *   which can be found in the file epl-v10.html at the root of this distribution.
 *   By using this software in any fashion, you are agreeing to be bound by
 * 	 the terms of this license.
 *   You must not remove this notice, or any other, from this software.
 **/

/* rich Jan 31, 2009 */

package clojure.lang;

import java.util.*;

public final class LazySeq extends ALazySeq {

private IFn fn;
private Object sv;
private ISeq s;

public LazySeq(IFn fn){
        super(null);
	this.fn = fn;
}

private LazySeq(IPersistentMap meta, ISeq s){
	super(meta);
	this.fn = null;
	this.s = s;
}

public Obj withMeta(IPersistentMap meta){
	return new LazySeq(meta, seq());
}

final synchronized Object sval(){
	if(fn != null)
		{
                sv = fn.invoke();
                fn = null;
		}
	if(sv != null)
		return sv;
	return s;
}

final synchronized public ISeq seq(){
	sval();
	if(sv != null)
		{
		Object ls = sv;
		sv = null;
		while(ls instanceof LazySeq)
			{
			ls = ((LazySeq)ls).sval();
			}
		s = RT.seq(ls);
		}
	return s;
}

synchronized public boolean isRealized(){
	return fn == null;
}
}
