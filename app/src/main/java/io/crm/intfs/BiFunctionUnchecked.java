package io.crm.intfs;

/**
 * Created by someone on 08/11/2015.
 */
public interface BiFunctionUnchecked<T1, T2, R> {
    public R apply(T1 t1, T2 t2) throws Exception;
}
