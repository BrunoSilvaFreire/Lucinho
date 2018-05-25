package me.ddevil.lucinho.command

import me.ddevil.lucinho.Lucinho
import me.ddevil.lucinho.command.internal.HelpCommand
import me.ddevil.lucinho.util.sendEmbed
import me.ddevil.util.getStackTraceText
import net.dv8tion.jda.core.events.Event
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.EventListener
import java.awt.Color

class CommandManager(
        val lucinho: Lucinho
) : EventListener {


    private val knownCommands = ArrayList<Command>()
    var commandChar: Char = '!'
    val commands get() = knownCommands

    init {
        lucinho.jda.addEventListener(this)
        registerCommand(HelpCommand(lucinho))
    }

    override fun onEvent(event: Event?) {
        if (event == null || event !is MessageReceivedEvent) {
            return
        }
        handleEvent(event)
    }

    private fun handleEvent(event: MessageReceivedEvent) {
        val message = event.message
        var content = message.contentRaw
        if (!content.startsWith(commandChar)) {
            return
        }
        content = content.removeRange(0, 1)
        val params = content.split(" ")
        val label = params.first()
        val args = params.slice(1..params.lastIndex).toTypedArray()
        val command = this[label]
        val channel = event.textChannel!!
        if (command == null) {
            channel.sendEmbed {
                setColor(Color.red).setTitle("(404) Command not found").setDescription("Try using ${commandChar}help")
            }
            return
        }
        try {
            val commandArgs = CommandArgs(event, label, args)
            command.execute(lucinho, commandArgs)
        } catch (exception: Throwable) {
            channel.sendEmbed {
                setColor(Color.red)
                        .setTitle("O boi")
                        .setDescription("Something went wrong when executing command '${command.mainLabel}'! ${System.lineSeparator()}```${exception.getStackTraceText()}```")
            }
        }
    }

    operator fun get(label: String): Command? {
        return knownCommands.firstOrNull {
            it.hasLabel(label)
        }
    }

    fun registerCommand(command: Command): Boolean {
        if (command in knownCommands) {
            return false
        }
        return knownCommands.add(command)
    }

    fun unregisterCommand(command: Command): Boolean {
        if (command !in knownCommands) {
            return false
        }
        return knownCommands.remove(command)
    }

}