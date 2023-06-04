package com.alicimsamil.harmonyhub.core.domain

/**
 * The BaseMapper class is a generic mapper for data transformation,
 * with abstract method for mapping source item
 * to a target item.
 */
abstract class BaseMapper<T, R> {

    /**
     * This method transforms a source item into a target item of type "R".
     */
    abstract fun map(source: T): R

}