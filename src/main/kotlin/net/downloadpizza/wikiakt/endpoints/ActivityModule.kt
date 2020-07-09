package net.downloadpizza.wikiakt.endpoints

import net.downloadpizza.wikiakt.*

class ActivityModule(private val api: WikiaApi) {
    fun latestActivity(
        limit: Int? = null,
        namespaces: List<Int>? = null,
        allowDuplicates: Boolean? = null
    ): RequestResult<ActivityResponseResult> =
        api.get(
            "Activity/LatestActivity",
            listOf(
                "limit" to limit,
                "namespaces" to namespaces?.intCommaList(),
                "allowDuplicates" to allowDuplicates
            )
        )

    fun recentlyChanged(
        limit: Int? = null,
        namespaces: List<Int>? = null,
        allowDuplicates: Boolean? = null
    ): RequestResult<ActivityResponseResult> =
        api.get(
            "Activity/RecentlyChanged",
            listOf(
                "limit" to limit,
                "namespaces" to namespaces?.intCommaList(),
                "allowDuplicates" to allowDuplicates
            )
        )
}