package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import java.util.function.BiFunction

object KDRProvider {

    private const val PREFIX = "kdr_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        val plugin = kdr.Main.plugin

        papi.visitorSensitivePlaceholder<Int>(
                name = "${PREFIX}deaths",
                loader = BiFunction { p, _ -> plugin.getDeaths(p) },
                processParameters = true
        )
        papi.visitorSensitivePlaceholder<Int>(
                name = "${PREFIX}kills",
                loader = BiFunction { p, _ -> plugin.getKills(p) },
                processParameters = true
        )
        papi.visitorSensitivePlaceholder<Int>(
                name = "${PREFIX}kdr",
                loader = BiFunction { p, _ -> plugin.getKDR(p).hashCode() },
                processParameters = true
        )
    }
}