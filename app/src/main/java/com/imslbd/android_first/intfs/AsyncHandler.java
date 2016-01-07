package com.imslbd.android_first.intfs;

import com.annimon.stream.function.BiConsumer;
import com.annimon.stream.function.Consumer;

/**
 * Created by shahadat on 1/7/16.
 */
public interface AsyncHandler<T> extends BiConsumer<Throwable, T> {
}
