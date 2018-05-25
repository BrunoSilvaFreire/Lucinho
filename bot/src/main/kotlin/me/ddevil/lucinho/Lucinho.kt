package me.ddevil.lucinho

import me.ddevil.lucinho.command.CommandManager
import me.ddevil.lucinho.command.CommandOwner
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import kotlin.concurrent.thread

class Lucinho(token: String) : CommandOwner {

    override val prefix get() = "lucinho"
    val jda: JDA = JDABuilder(AccountType.BOT)
            .setToken(token)
            .buildBlocking()
    val commandManager = CommandManager(this)

    init {
        Runtime.getRuntime().addShutdownHook(
                thread(false) {
                    println("Shuting down")
                    jda.shutdownNow()
                }
        )
    }

}