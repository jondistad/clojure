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

/* Jon Distad, August 15, 2015 */

public final class Concat extends ALazySeq {

private final Object head;
private final Object tail;
private ISeq _seq;

public Concat(Object head, Object tail) {
        this(null, head, tail);
}

private Concat(IPersistentMap meta, Object head, Object tail) {
        super(meta);
        this.head = head;
        this.tail = tail;
}

public Obj withMeta(IPersistentMap meta) {
        return new Concat(meta, head, tail);
}

private static Concat extrude(Concat conc) {
        if (conc.isRealized()) return conc;
        Object h = conc.head;
        Object t = conc.tail;
        while (h instanceof Concat) {
                Concat c = (Concat)h;
                if (c.isRealized())
                        return new Concat(c.seq(), t);
                t = new Concat(c.tail, t);
                h = c.head;
        }
        return new Concat(h, t);
}

private static ISeq headSeq(Concat conc) {
        if (conc.isRealized()) return conc.seq();
        ISeq s = RT.seq(conc.head);
        if (s != null) {
                if (s instanceof IChunkedSeq) {
                        IChunkedSeq cs = (IChunkedSeq)s;
                        IChunk f = cs.chunkedFirst();
                        if (RT.count(f) == 0)
                                return new Concat(cs.chunkedMore(), conc.tail);
                        else
                                return new ChunkedCons(f, new Concat(cs.chunkedMore(), conc.tail));
                } else {
                        return new Cons(s.first(), new Concat(s.more(), conc.tail));
                }
        }
        return null;
}

private final synchronized ISeq doSeq() {
        if (_seq != null) return _seq;
        Concat c = extrude(this);
        _seq = headSeq(c);
        while (_seq == null && c.tail instanceof Concat) {
                c = extrude((Concat)c.tail);
                _seq = headSeq(c);
        }
        if (_seq == null)
                _seq = RT.seq(c.tail);
        if (_seq == null)
                _seq = PersistentList.EMPTY;
        return _seq;
}

public final synchronized ISeq seq() {
        doSeq();
        return _seq.seq();
}

public final synchronized boolean isRealized() {
        return _seq != null;
}

}
