package me.ddevil.lucinho.command.internal

import me.ddevil.lucinho.Lucinho
import me.ddevil.lucinho.command.Command
import me.ddevil.lucinho.command.CommandArgs
import me.ddevil.lucinho.util.sendEmbed
import me.ddevil.util.emptyString
import java.awt.Color

class HelpCommand(owner: Lucinho) : Command(owner, "help", "ajuda") {
    override fun execute(lucinho: Lucinho, args: CommandArgs) {
        var message = emptyString()
        for (command in lucinho.commandManager.commands) {
            message += createMessage(command)
        }
        args.event.textChannel.sendEmbed {
            setColor(Color.GREEN)
            setTitle("Commands: ")
            setDescription(message)
        }

    }

    private fun createMessage(command: Command): String {
        return "${getLabels(command)} (Registered to ${command.owner.prefix})${System.lineSeparator()}"
    }

    private fun getLabels(command: Command): String {
        var msg = command.mainLabel
        val alternate = command.alternateLabels
        if (alternate.isNotEmpty()) {
            msg += " (${alternate.joinToString()}) "
        }
        return msg
    }

}