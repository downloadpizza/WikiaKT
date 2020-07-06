import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.getAs
import net.downloadpizza.wikiakt.WikiaApi
import net.downloadpizza.wikiakt.value
import org.junit.jupiter.api.Test

const val BASEPATH = "https://jellesmarbleruns.fandom.com"

class TestActivity {
    private val api = WikiaApi(BASEPATH)

    @Test
    fun `test latest activity`() {
        val resp = api.activity.latestActivity(10, listOf(1,2,3))
        assert(resp !is Error)
        assert(resp.value().basepath == BASEPATH)
    }
}