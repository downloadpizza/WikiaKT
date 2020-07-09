package net.downloadpizza.wikiakt.endpoints

import net.downloadpizza.wikiakt.*

class ArticlesModule(private val api: WikiaApi) {
    fun asSimpleJson(
        id: Int
    ): RequestResult<ContentResult> =
        api.get("Articles/AsSimpleJson", listOf("id" to id))

    fun details(
        ids: List<Int>,
        titles: List<String>? = null,
        abstract: Int? = null,
        width: Int? = null,
        height: Int? = null
    ): RequestResult<ExpandedArticleResultSet> =
        api.get(
            "Articles/Details", listOf(
                "ids" to ids.intCommaList(),
                "titles" to titles?.stringCommaList(),
                "abstract" to abstract,
                "width" to width,
                "height" to height
            )
        )

    fun list(
        category: String? = null,
        namespaces: List<Int>? = null,
        limit: Int? = null,
        offset: String? = null
    ): RequestResult<UnexpandedListArticleResultSet> =
        api.get(
            "Articles/List", listOf(
                "category" to category,
                "namespaces" to namespaces?.intCommaList(),
                "limit" to limit,
                "offset" to offset
            )
        )

    fun expandedList(
        category: String? = null,
        namespaces: List<Int>? = null,
        limit: Int? = null,
        offset: String? = null
    ): RequestResult<ExpandedListArticleResultSet> =
        api.get(
            "Articles/List?expand=1", listOf(
                "category" to category,
                "namespaces" to namespaces?.intCommaList(),
                "limit" to limit,
                "offset" to offset
            )
        )

    fun mostLinked(): RequestResult<UnexpandedMostLinkedResultSet> =
        api.get("Articles/MostLinked")

    fun expandedMostLinked(): RequestResult<ExpandedMostLinkedResultSet> =
        api.get("Articles/MostLinked?expand=1")

    fun new(
        namespaces: List<Int>? = null,
        limit: Int? = null,
        minArticleQuality: Int? = null
    ): RequestResult<NewArticleResultSet> =
        api.get(
            "Articles/New", listOf(
                "namespaces" to namespaces?.intCommaList(),
                "limit" to limit,
                "minArticleQuality" to minArticleQuality
            )
        )

    fun popular(
        limit: Int? = null,
        baseArticleId: Int? = null
    ): RequestResult<PopularListArticleResultSet> =
        api.get(
            "Articles/Popular", listOf(
                "limit" to limit,
                "baseArticleId" to baseArticleId
            )
        )

    fun expandedPopular(
        limit: Int? = null,
        baseArticleId: Int? = null
    ): RequestResult<ExpandedArticleResultSet> =
        api.get(
            "Articles/Popular?expand=1", listOf(
                "limit" to limit,
                "baseArticleId" to baseArticleId
            )
        )

    fun top(
        namespaces: List<Int>? = null,
        category: String? = null,
        limit: Int? = null,
        baseArticleId: Int? = null
    ): RequestResult<UnexpandedArticleResultSet> =
        api.get(
            "Articles/Top", listOf(
                "namespaces" to namespaces,
                "category" to category,
                "limit" to limit,
                "baseArticleId" to baseArticleId
            )
        )

    fun expandedTop(
        namespaces: List<Int>? = null,
        category: String? = null,
        limit: Int? = null,
        baseArticleId: Int? = null
    ): RequestResult<UnexpandedArticleResultSet> =
        api.get(
            "Articles/Top?expand=1", listOf(
                "namespaces" to namespaces?.intCommaList(),
                "category" to category,
                "limit" to limit,
                "baseArticleId" to baseArticleId
            )
        )

    fun topByHub(
        hub: String,
        lang: List<String>? = null,
        namespaces: List<Int>? = null
    ): RequestResult<HubArticleResultSet> =
        api.get(
            "Articles/TopByHub", listOf(
                "hub" to hub,
                "lang" to lang?.stringCommaList(),
                "namespaces" to namespaces?.intCommaList()
            )
        )

}