package net.downloadpizza.wikiakt

import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.Fuel
import net.downloadpizza.wikiakt.endpoints.*
import com.github.kittinunf.result.Result as FuelResult

class WikiaApi(val basepath: String) {
    val klaxon = Klaxon()

    inline fun <reified T : Any> get(
        path: String,
        parameters: List<Pair<String, Any?>>? = null
    ): RequestResult<T> {
        check(parameters?.any { it.second is List<*> } ?: false) // lists have to be joined to strings
        val (_, _, result) = Fuel.get("$basepath/api/v1/$path", parameters).responseString()
        val str = when (result) {
            is FuelResult.Success -> result.get()
            is FuelResult.Failure -> return Err(result.error.message)
        }
        return klaxon.parse<T>(str)?.run(::Ok) ?: Err("Failed to parse JSON")
    }

    val activity = ActivityModule(this)
    val articles = ArticlesModule(this)
    val mercury = MercuryModule(this)
    val navigation = NavigationModule(this)
    val relatedPages = RelatedPagesModule(this)
}