/**
 *   Copyright (c) Rich Hickey. All rights reserved.
 *   The use and distribution terms for this software are covered by the
 *   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *   which can be found in the file epl-v10.html at the root of this distribution.
 *   By using this software in any fashion, you are agreeing to be bound by
 * 	 the terms of this license.
 *   You must not remove this notice, or any other, from this software.
 **/

/* rich Mar 25, 2006 3:44:58 PM */

package clojure.lang;

import java.io.Serializable;

public abstract class Obj implements Obj_STAR_ {

final Obj_impl _inner_obj;

public Obj(Obj_STAR_ inner){
	this._inner_obj = inner;
}

final public IPersistentMap meta(){
	return _inner_obj.meta();
}

abstract public Obj withMeta(IPersistentMap meta);

}
