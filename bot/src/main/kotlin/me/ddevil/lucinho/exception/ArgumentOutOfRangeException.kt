package me.ddevil.lucinho.exception

import me.ddevil.lucinho.command.CommandArgs

class ArgumentOutOfRangeException(val index: Int, val commandArgs: CommandArgs) : Exception()