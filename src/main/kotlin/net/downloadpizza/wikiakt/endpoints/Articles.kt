package net.downloadpizza.wikiakt.endpoints

import net.downloadpizza.wikiakt.*

class Articles(private val api: WikiaApi) {
    fun asSimpleJson(
        id: Int
    ): RequestResult<ContentResult> =
        api.get("Articles/AsSimpleJson", listOf("id" to id), listOf(400, 404))

    fun details(
        ids: List<Int>,
        titles: List<String>,
        abstract: Int,
        width: Int,
        height: Int
    ): RequestResult<ExpandedArticleResultSet> =
        api.get(
            "Articles/Details", listOf(
                "ids" to ids.intCommaList(),
                "titles" to titles.stringCommaList(),
                "abstract" to abstract,
                "width" to width,
                "height" to height
            ), listOf(400)
        )

    fun list(
        category: String,
        namespaces: List<String>,
        limit: Int,
        offset: String
    ): RequestResult<UnexpandedListArticleResultSet> =
        api.get(
            "Articles/List", listOf(
                "category" to category,
                "namespaces" to namespaces.stringCommaList(),
                "limit" to limit,
                "offset" to offset
            ), listOf(400, 404)
        )

    fun expandedList(
        category: String,
        namespaces: List<String>,
        limit: Int,
        offset: String
    ): RequestResult<ExpandedListArticleResultSet> =
        api.get(
            "Articles/List?expand=1", listOf(
                "category" to category,
                "namespaces" to namespaces.stringCommaList(),
                "limit" to limit,
                "offset" to offset
            ), listOf(400, 404)
        )

}