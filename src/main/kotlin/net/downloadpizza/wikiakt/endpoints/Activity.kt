package net.downloadpizza.wikiakt.endpoints

import net.downloadpizza.wikiakt.*

class Activity(private val api: WikiaApi) {
    fun latestActivity(
        limit: Int,
        namespaces: List<Namespace>,
        allowDuplicates: Boolean = true
    ): RequestResult<ActivityResponseResult> =
        api.get(
            "Activity/LatestActivity",
            listOf(
                "limit" to limit,
                "namespaces" to namespaces.intCommaList(),
                "allowDuplicates" to allowDuplicates
            )
        )

    fun recentlyChanged(
        limit: Int,
        namespaces: List<Namespace>,
        allowDuplicates: Boolean = true
    ): RequestResult<ActivityResponseResult> =
        api.get(
            "Activity/RecentlyChanged",
            listOf(
                "limit" to limit,
                "namespaces" to namespaces.intCommaList(),
                "allowDuplicates" to allowDuplicates
            )
        )
}