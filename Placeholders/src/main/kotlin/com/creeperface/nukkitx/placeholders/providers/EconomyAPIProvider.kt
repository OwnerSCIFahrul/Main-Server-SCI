package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import me.onebone.economyapi.EconomyAPI
import java.util.function.BiFunction

object EconomyAPIProvider {

    private const val PREFIX = "economy_"

    fun registerPlaceholders(papi: PlaceholderAPI) {
        val api = EconomyAPI.getInstance()

        papi.visitorSensitivePlaceholder<Double?>("${PREFIX}money", BiFunction { p, params ->
            var money = api.myMoney(p)

            params["acc"]?.let {
                money = money
            }

            return@BiFunction money
        })
    }
}