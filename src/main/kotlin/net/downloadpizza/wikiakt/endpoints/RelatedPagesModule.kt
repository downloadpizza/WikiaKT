package net.downloadpizza.wikiakt.endpoints

import net.downloadpizza.wikiakt.RelatedPages
import net.downloadpizza.wikiakt.RequestResult
import net.downloadpizza.wikiakt.WikiaApi

class RelatedPagesModule(private val api: WikiaApi) {
    fun list(
        ids: List<Int>,
        limit: Int?
    ): RequestResult<RelatedPages> =
        api.get("RelatedPages/List", listOf(
            "ids" to ids,
            "limit" to limit
        ))
}