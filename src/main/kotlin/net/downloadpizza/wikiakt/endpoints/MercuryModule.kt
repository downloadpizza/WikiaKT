package net.downloadpizza.wikiakt.endpoints

import net.downloadpizza.wikiakt.RequestResult
import net.downloadpizza.wikiakt.WikiDataContainer
import net.downloadpizza.wikiakt.WikiaApi

class MercuryModule(private val api: WikiaApi) {
    fun wikiVariables(): RequestResult<WikiDataContainer> =
        api.get("Mercury/WikiVariables")
}