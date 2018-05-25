package me.ddevil.lucinho.util

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.TextChannel

fun TextChannel.sendEmbed(builder: EmbedBuilder.() -> Unit) {
    val embedBuilder = EmbedBuilder()
    embedBuilder.builder()
    this.sendMessage(embedBuilder.build()).queue()
}