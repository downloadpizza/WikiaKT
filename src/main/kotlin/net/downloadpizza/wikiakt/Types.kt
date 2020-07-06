package net.downloadpizza.wikiakt

data class ContentResult(
    val sections: List<Section>
)

data class Section(
    val title: String,
    val level: Int,
    val content: List<SectionContent>,
    val images: List<SectionImages>
)

data class SectionContent(
    val type: String,
    val text: String?,
    val elements: List<ListElement>?
)

data class ListElement(
    val text: String,
    val elements: List<ListElement>
)

data class SectionImages(
    val src: String,
    val caption: String
)

data class ExpandedArticleResultSet(
    val items: List<ExpandedArticle>,
    val basepath: String
)

data class ExpandedArticle(
    val original_dimensions: OriginalDimension?,
    val url: String,
    val ns: Int,
    val abstract: String,
    val thumbnail: String?,
    val revision: Revision,
    val id: Int,
    val title: String,
    val type: String,
    val comments: Int
)

data class OriginalDimension(
    val width: Int,
    val height: Int
)

data class Revision(
    val id: Int,
    val user: String,
    val user_id: Int,
    val timestamp: Int
)

data class UnexpandedListArticleResultSet(
    val items: List<UnexpandedArticle>,
    val offset: String,
    val basepath: String
)

data class UnexpandedArticle(
    val id: Int,
    val title: String,
    val url: String,
    val ns: Int
)

data class ExpandedListArticleResultSet(
    val items: List<ExpandedArticle>,
    val offset: String,
    val basepath: String
)

data class UnexpandedMostLinkedResultSet(
    val items: List<UnexpandedMostLinked>,
    val basepath: String
)

data class UnexpandedMostLinked(
    val url: String,
    val ns: Int,
    val id: Int,
    val title: String,
    val backlink_cnt: Int
)

data class ExpandedMostLinkedResultSet(
    val items: List<ExpandedMostLinked>,
    val basepath: String
)

data class ExpandedMostLinked(
    val url: String,
    val ns: Int,
    val abstract: String,
    val revision: Revision,
    val id: Int,
    val title: String,
    val type: String,
    val backlink_cnt: Int,
    val comments: Int
)

data class NewArticleResultSet(
    val quality: Int,
    val original_dimensions: OriginalDimension?,
    val url: String,
    val ns: Int,
    val abstract: String,
    val creator: Creator?,
    val thumbnail: String?,
    val creation_date: String,
    val id: Int,
    val title: String
)

data class Creator(
    val avatar: String,
    val name: String
)

data class PopularListArticleResultSet(
    val items: List<PopularArticle>,
    val basepath: String
)

data class PopularArticle(
    val id: Int,
    val title: String,
    val url: String
)

data class UnexpandedArticleResultSet(
    val items: List<UnexpandedArticle>,
    val basepath: String
)

data class HubArticleResultSet(
    val items: List<HubArticleResult>
)

data class HubArticleResult(
    val wiki: Wikia,
    val articles: List<HubArticle>
)

data class Wikia(
    val id: Int,
    val name: String,
    val language: String,
    val domain: String
)

data class HubArticle(
    val id: Int,
    val ns: Int
)

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

data class WikiDataContainer(
    val data: WikiData
)
data class WikiData(
    val cacheBuster: Int,
    val dbName: String,
    val defaultSkin: String,
    val id: Int,
    val language: WikiLanguageData,
    val namespaces: List<NamespacesObject>,
    val sitename: String,
    val mainPageTitle: String,
    val wikiCategories: List<String>,
    val navData: NavigationResultSet,
    val vertical: String,
    val basePath: String,
    val isGASpecialWiki: Boolean,
    val articlePath: String,
    val facebookAppId: String
)

data class NamespacesObject(
    val id: Int,
    val name: String
)

data class WikiLanguageData(
    val user: String,
    val userDir: String,
    val content: String,
    val contentDir: String
)
data class NavigationResultSet(
    val navigation: NavigationItem
)
data class NavigationItem(
    val wikia: List<WikiaItem>,
    val wiki: List<WikiaItem>
)
data class WikiaItem(
    val text: String,
    val href: String,
    val children: List<ChildrenItem>
)
data class ChildrenItem(
    val text: String,
    val href: String
)