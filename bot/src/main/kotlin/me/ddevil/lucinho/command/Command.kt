package me.ddevil.lucinho.command

import me.ddevil.lucinho.Lucinho

abstract class Command(
        val owner: CommandOwner,
        val mainLabel: String,
        vararg val alternateLabels: String
) {
    abstract fun execute(lucinho: Lucinho, args: CommandArgs)

    fun hasLabel(label: String, ignoreCase: Boolean = true): Boolean {
        println("'$mainLabel' vs '$label'")
        if (isMainLabel(label, ignoreCase)) {
            return true
        }
        return hasAnyAlternate(label, ignoreCase)
    }

    fun isMainLabel(label: String, ignoreCase: Boolean): Boolean {
        return mainLabel.equals(label, ignoreCase)
    }

    fun hasAnyAlternate(label: String, ignoreCase: Boolean): Boolean {
        return alternateLabels.any { label.equals(it, ignoreCase) }
    }

    override fun toString() = "${owner.prefix}.$mainLabel"

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is Command) {
            return false
        }

        if (owner != other.owner) {
            return false
        }
        return mainLabel == other.mainLabel
    }

    override fun hashCode(): Int {
        var result = owner.hashCode()
        result = 31 * result + mainLabel.hashCode()
        return result
    }
}