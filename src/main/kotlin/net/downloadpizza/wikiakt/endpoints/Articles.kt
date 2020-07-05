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
        namespaces: List<Int>,
        limit: Int,
        offset: String
    ): RequestResult<UnexpandedListArticleResultSet> =
        api.get(
            "Articles/List", listOf(
                "category" to category,
                "namespaces" to namespaces.intCommaList(),
                "limit" to limit,
                "offset" to offset
            ), listOf(400, 404)
        )

    fun expandedList(
        category: String,
        namespaces: List<Int>,
        limit: Int,
        offset: String
    ): RequestResult<ExpandedListArticleResultSet> =
        api.get(
            "Articles/List?expand=1", listOf(
                "category" to category,
                "namespaces" to namespaces.intCommaList(),
                "limit" to limit,
                "offset" to offset
            ), listOf(400, 404)
        )

    fun mostLinked(): RequestResult<UnexpandedMostLinkedResultSet> =
        api.get("Articles/MostLinked", errorStatusCodes = listOf(400, 404))

    fun expandedMostLinked(): RequestResult<ExpandedMostLinkedResultSet> =
        api.get("Articles/MostLinked?expand=1", errorStatusCodes = listOf(400, 404))

    fun new(
        namespaces: List<Int>,
        limit: Int,
        minArticleQuality: Int
    ): RequestResult<NewArticleResultSet> =
        api.get("Articles/New", listOf(
            "namespaces" to namespaces.intCommaList(),
            "limit" to limit,
            "minArticleQuality" to minArticleQuality
        ), listOf(400, 404))

    fun popular(
        limit: Int,
        baseArticleId: Int
    ): RequestResult<PopularListArticleResultSet> =
        api.get("Articles/Popular", listOf(
            "limit" to limit,
            "baseArticleId" to baseArticleId
        ), listOf(400, 404))

    fun expandedPopular(
        limit: Int,
        baseArticleId: Int
    ): RequestResult<ExpandedArticleResultSet> =
        api.get("Articles/Popular?expand=1", listOf(
            "limit" to limit,
            "baseArticleId" to baseArticleId
        ), listOf(400, 404))

    fun top(
        namespaces: List<Int>,
        category: String,
        limit: Int,
        baseArticleId: Int
    ): RequestResult<UnexpandedArticleResultSet> =
        api.get("Articles/Top", listOf(
            "namespaces" to namespaces,
            "category" to category,
            "limit" to limit,
            "baseArticleId" to baseArticleId
        ), listOf(400, 404))

    fun expandedTop(
        namespaces: List<Int>,
        category: String,
        limit: Int,
        baseArticleId: Int
    ): RequestResult<UnexpandedArticleResultSet> =
        api.get("Articles/Top?expand=1", listOf(
            "namespaces" to namespaces,
            "category" to category,
            "limit" to limit,
            "baseArticleId" to baseArticleId
        ), listOf(400, 404))

    fun topByHub(
        hub: String,
        lang: List<String>,
        namespaces: List<Int>
    ): RequestResult<HubArticleResultSet> =
        api.get("Articles/TopByHub", listOf(
            "hub" to hub,
            "lang" to lang,
            "namespaces" to namespaces
        ), listOf(400, 404))

}