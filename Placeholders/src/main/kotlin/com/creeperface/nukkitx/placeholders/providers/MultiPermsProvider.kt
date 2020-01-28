package com.creeperface.nukkitx.placeholders.providers

import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI
import ru.nukkit.multiperms.MultiPerms
import java.util.function.BiFunction
import java.util.function.Function

object MultiPermsProvider {

    private const val PREFIX = "multiperms_"

    fun registerPlaceholders(papi: PlaceholderAPI) {

        papi.visitorSensitivePlaceholder<String?>("${PREFIX}prefix", BiFunction { p, _ ->
            MultiPerms.getPrefix(p)
        })

        papi.visitorSensitivePlaceholder<String?>("${PREFIX}suffix", BiFunction { p, _ ->
            MultiPerms.getSuffix(p)
        })

        papi.visitorSensitivePlaceholder<String?>("${PREFIX}group", BiFunction { p, _ ->
            MultiPerms.getGroup(p)
        })

        papi.visitorSensitivePlaceholder<String>("${PREFIX}groups", BiFunction { p, _ ->
            MultiPerms.getGroups(p).joinToString(", ")
        })

        papi.staticPlaceholder<String?>(
            name = "${PREFIX}group_prefix",
            loader = Function { params ->
                MultiPerms.getGroupPrefix(params.single())
            },
            processParameters = true
        )

        papi.staticPlaceholder<String?>(
            name = "${PREFIX}group_suffix",
            loader = Function { params ->
                MultiPerms.getGroupSuffix(params.single())
            },
            processParameters = true
        )

        papi.staticPlaceholder<Int>(
            name = "${PREFIX}group_priority",
            loader = Function { params ->
                MultiPerms.getGroupPriority(params.single())
            },
            processParameters = true
        )
    }
}