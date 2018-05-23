package me.ddevil.lucinho.command

import me.ddevil.lucinho.exception.ArgumentOutOfRangeException
import me.ddevil.lucinho.exception.InvalidArgumentException

class CommandArgs
private constructor(
        private val args: Array<String>
) {
    val arguments get() = args
    val length get() = args.size
    @Throws(InvalidArgumentException::class, ArgumentOutOfRangeException::class)
    fun getString(index: Int): String {
        if (isOutOfRange(index)) {
            throw ArgumentOutOfRangeException(index, this)
        }
        return arguments[index]
    }

    fun isOutOfRange(index: Int) = index > length
}