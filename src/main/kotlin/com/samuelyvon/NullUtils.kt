@file:Suppress("unused")

package com.samuelyvon

/**
 * Execute a block if the item is not null
 * @param block the block to execute
 */
inline fun <T, R> T?.whenNotNull(block: (T) -> R) {
    if (null != this) {
        block(this)
    }
}

/**
 * Execute a block if the item is null
 * @param block the block to execute
 */
inline fun <T, R> T?.whenNull(block: () -> R) {
    if (null == this) {
        block()
    }
}

/**
 * Switches over a value to test the existence of a value or not (null or not). It asks
 * first for the existenceBlock to execute if the value ifExists, and then you can use
 * com.samuelyvon.ExistenceSwitchBlock.otherwise to add a existenceBlock for the case where the value does not exist (is null)
 * and execute the check.
 * @see ExistenceSwitchBlock
 * @see ExistenceSwitchBlock.otherwise
 * @param block the existenceBlock to execute if the item does ifExists
 */
fun <T, R> T?.ifExists(block: (T) -> R): ExistenceSwitchBlock<T, R> {
    return ExistenceSwitchBlock(this, block)
}

/**
 * Contains the existence block to execute if the value T? is not-null
 */
class ExistenceSwitchBlock<T, R>(val item: T?, val existenceBlock: (T) -> R) {
    /**
     * Completes the existence check by accepting a block to execute if the value is null.
     * The block does not contain an argument since none can be provided
     * @param block the existenceBlock to execute if the item does not exist
     */
    inline infix fun otherwise(block: () -> R): R {
        return if (null != this.item) {
            this.existenceBlock(this.item)
        } else {
            block()
        }
    }
}