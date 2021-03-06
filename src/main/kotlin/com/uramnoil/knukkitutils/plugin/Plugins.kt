package com.uramnoil.knukkitutils.plugin

import cn.nukkit.command.CommandSender
import cn.nukkit.event.Event
import cn.nukkit.event.EventPriority
import cn.nukkit.event.Listener
import cn.nukkit.plugin.Plugin
import cn.nukkit.plugin.PluginManager

@Suppress("unused")
inline fun <reified T : Plugin> PluginManager.getPlugin(): Plugin? = plugins.values.filterIsInstance<T>().firstOrNull()

@Suppress("unused")
inline fun <reified T : Event> Plugin.on(
	priority: EventPriority = EventPriority.NORMAL,
	crossinline onEvent: (event: T) -> Unit
) = server.pluginManager.registerEvent(
	T::class.java,
	object : Listener {},
	priority,
	{ _, event -> onEvent(event as T) },
	this
)

typealias CommandExecuter = (sender: CommandSender, commandLabel: String, args: Array<out String>) -> Boolean