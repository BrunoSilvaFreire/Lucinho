package me.ddevil.lucinho.command

abstract class Command(
        val owner: CommandOwner
) {
    abstract fun execute(args: CommandArgs)
}