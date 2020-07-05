package net.downloadpizza.wikiakt

import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.Fuel
import net.downloadpizza.wikiakt.endpoints.Activity
import net.downloadpizza.wikiakt.endpoints.Articles
import com.github.kittinunf.result.Result as FuelResult

open class WikiaApi(val basepath: String) {
    val klaxon = Klaxon()

    inline fun <reified T : Any> get(path: String, parameters: List<Pair<String, Any?>>? = null, errorStatusCodes: List<Int> = emptyList()): RequestResult<T> {
        val (_, response, result) = Fuel.get("$basepath/api/v1/$path", parameters).responseString()
        if(response.statusCode in errorStatusCodes) {
            return Err(response.responseMessage)
        }
        val str = when (result) {
            is FuelResult.Success -> result.get()
            is FuelResult.Failure -> return Err(result.error.message)
        }
        return klaxon.parse<T>(str)?.run(::Ok) ?: Err("Failed to parse JSON")
    }

    val activity = Activity(this)
    val articles = Articles(this)
}