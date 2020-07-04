package net.downloadpizza.wikiakt

import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result as FuelResult

class Wikia(val basepath: String) {
    val klaxon = Klaxon()

    inline fun <reified T : Any> get(path: String, parameters: List<Pair<String, Any?>>? = null): RequestResult<T> {
        val str = when (val result = Fuel.get("$basepath/api/v1/$path", parameters).responseString().third) {
            is FuelResult.Success -> result.get()
            is FuelResult.Failure -> return Err(result.error.message)
        }
        return klaxon.parse<T>(str)?.run(::Ok) ?: Err("Failed to parse")
    }
}