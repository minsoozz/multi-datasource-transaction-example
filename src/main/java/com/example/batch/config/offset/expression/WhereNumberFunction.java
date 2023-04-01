package com.example.batch.config.offset.expression;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;

/**
 * Created by jojoldu@gmail.com on 23/01/2020
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
@FunctionalInterface
public interface WhereNumberFunction<N extends Number & Comparable<?>> {

    BooleanExpression apply(NumberPath<N> id, int page, N currentId);

}
