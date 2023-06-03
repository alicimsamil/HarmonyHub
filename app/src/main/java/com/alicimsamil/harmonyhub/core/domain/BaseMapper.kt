package com.alicimsamil.harmonyhub.core.domain

/**
 * The BaseMapper class is a generic mapper for data transformation,
 * with abstract methods for mapping a single source item
 * to a target item and mapping a list of source items to a list of target items.
 */
abstract class BaseMapper<T, R> {

    /**
     * This method transforms a single source item into a target item of type "R".
     */
    abstract fun map(source: T): R

    /**
     * This method maps a list of source items to a list of target items.
     */
    abstract fun map(source: List<T>): List<R>

}