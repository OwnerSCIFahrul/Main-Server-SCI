package com.creeperface.nukkitx.placeholders

import cn.nukkit.plugin.PluginBase
import cn.nukkit.utils.TextFormat
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import com.creeperface.nukkitx.placeholders.providers.*
import java.util.function.Function

class Placeholders : PluginBase() {

    override fun onEnable() {
        val api = PlaceholderAPI.getInstance()

        initConfig(api)

        NukkitProvider.registerPlaceholders(api)
        logger.info("${TextFormat.AQUA}PlaceholderAPI dimuat")

        with(server.pluginManager) {
            getPlugin("LuckPerms")?.let {
                LuckPermsProvider.registerPlaceholders(api)
                logger.info("${TextFormat.AQUA}Placeholders LuckPerms dimuat")
            }

            getPlugin("MultiPerms")?.let {
                MultiPermsProvider.registerPlaceholders(api)
                logger.info("${TextFormat.AQUA}Placeholders MultiPerms dimuat")
            }

            getPlugin("Factions")?.let {
                FactionsProvider.registerPlaceholders(api)
                logger.info("${TextFormat.AQUA}Placeholders Factions dimuat")
            }

            getPlugin("EconomyAPI")?.let {
                EconomyAPIProvider.registerPlaceholders(api)
                logger.info("${TextFormat.AQUA}Placeholders EconomyAPI dimuat")
            }

            getPlugin("KDR")?.let {
                KDRProvider.registerPlaceholders(api)
                logger.info("${TextFormat.AQUA}Placeholders KDR dimuat")
            }

            getPlugin("Residence")?.let {
                ResidenceProvider.registerPlaceholders(api)
                logger.info("${TextFormat.AQUA}Placeholders Residence dimuat")
            }

            getPlugin("SynapseAPI")?.let {
                SynapseAPIProvider.registerPlaceholders(api)
                logger.info("${TextFormat.AQUA}Placeholders SynapseAPI dimuat")
            }
        }

    }

    private fun initConfig(api: PlaceholderAPI) {
        saveDefaultConfig()

        val cfg = config

        val section = cfg.getSection("placeholders")

        if (section.isNullOrEmpty()) {
            return
        }

        section.forEach { name, value ->
            if (value is Map<*, *> || value is List<*>) {
                return@forEach
            }

            api.staticPlaceholder<String>(name, Function { value.toString() })
        }
    }
}
