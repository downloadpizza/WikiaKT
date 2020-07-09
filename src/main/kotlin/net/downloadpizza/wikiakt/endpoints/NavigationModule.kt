package net.downloadpizza.wikiakt.endpoints

import net.downloadpizza.wikiakt.NavigationResultSet
import net.downloadpizza.wikiakt.RequestResult
import net.downloadpizza.wikiakt.WikiaApi

class NavigationModule(private val api: WikiaApi) {
    fun data(): RequestResult<NavigationResultSet> =
        api.get("Navigation/Data")
}