package io.crm.util.touple.immutable;

import io.crm.intfs.HexaConsumerUnchecked;
import io.crm.intfs.HexaFunctionUnchecked;

/**
 * Created by someone on 08/11/2015.
 */
final public class Tpl6<T1, T2, T3, T4, T5, T6> {
    public final T1 t1;
    public final T2 t2;
    public final T3 t3;
    public final T4 t4;
    public final T5 t5;
    public final T6 t6;

    public Tpl6(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.t6 = t6;
    }

    public <T7> Tpl7<T1, T2, T3, T4, T5, T6, T7> ps(final T7 val) {
        return new Tpl7<>(t1, t2, t3, t4, t5, t6, val);
    }

    public Tpl5<T1, T2, T3, T4, T5> pp() {
        return new Tpl5<>(t1, t2, t3, t4, t5);
    }

    public <T7> Tpl7<T1, T2, T3, T4, T5, T6, T7> al(final T7 val) {
        return new Tpl7<>(t1, t2, t3, t4, t5, t6, val);
    }

    public <T7> Tpl7<T7, T1, T2, T3, T4, T5, T6> af(final T7 val) {
        return new Tpl7<>(val, t1, t2, t3, t4, t5, t6);
    }

    public Tpl5<T2, T3, T4, T5, T6> df() {
        return new Tpl5<>(t2, t3, t4, t5, t6);
    }

    public Tpl5<T1, T2, T3, T4, T5> dl() {
        return new Tpl5<>(t1, t2, t3, t4, t5);
    }

    public T1 ft() {
        return t1;
    }

    public T6 lt() {
        return t6;
    }

    public <R> R apply(final HexaFunctionUnchecked<T1, T2, T3, T4, T5, T6, R> functionUnchecked) {
        try {
            return functionUnchecked.apply(t1, t2, t3, t4, t5, t6);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    public void accept(final HexaConsumerUnchecked<T1, T2, T3, T4, T5, T6> consumerUnchecked) {
        try {
            consumerUnchecked.accept(t1, t2, t3, t4, t5, t6);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    public <T7, T8> Tpl8<T1, T2, T3, T4, T5, T6, T7, T8> jn(final Tpl2<T7, T8> tpl2) {
        return new Tpl8<>(t1, t2, t3, t4, t5, t6, tpl2.t1, tpl2.t2);
    }
}
