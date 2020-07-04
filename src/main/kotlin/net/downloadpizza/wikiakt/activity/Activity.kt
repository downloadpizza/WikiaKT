package net.downloadpizza.wikiakt.activity

import net.downloadpizza.wikiakt.Namespace
import net.downloadpizza.wikiakt.RequestResult
import net.downloadpizza.wikiakt.Wikia

/*
{
  "items": [
    {
      "article": "integer",
      "user": "integer",
      "revisionId": "integer",
      "timestamp": "integer"
    }
  ],
  "basepath": "string"
}
 */

data class ActivityResponseResult(
    val items: List<ActivityResponseItem>,
    val basepath: String
)

data class ActivityResponseItem(
    val article: Int,
    val user: Int,
    val revisionId: Int,
    val timestamp: Int
)

fun Wikia.latestActivity(limit: Int, namespaces: List<Namespace>, allowDuplicates: Boolean = true): RequestResult<ActivityResponseResult> {
    val nsList = namespaces.joinToString(",")
    return get(
        "Activity/LatestActivity",
        listOf(
            "limit" to limit,
            "namespaces" to nsList,
            "allowDuplicates" to allowDuplicates
        )
    )
}

fun Wikia.recentlyChangedArticles(limit: Int, namespaces: List<Namespace>, allowDuplicates: Boolean = true): RequestResult<ActivityResponseResult> {
    val nsList = namespaces.joinToString(",")
    return get(
        "Activity/RecentlyChangedArticles",
        listOf(
            "limit" to limit,
            "namespaces" to nsList,
            "allowDuplicates" to allowDuplicates
        )
    )
}