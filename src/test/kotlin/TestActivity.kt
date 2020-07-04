import net.downloadpizza.wikiakt.Wikia
import net.downloadpizza.wikiakt.activity.latestActivity
import net.downloadpizza.wikiakt.value
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

const val BASEPATH = "https://jellesmarbleruns.fandom.com"

class TestActivity {
    private val api = Wikia(BASEPATH)

    @Test
    fun `test latest activity`() {
        val resp = api.latestActivity(10, listOf(0), true)
        assert(resp !is Error)
        assert(resp.value().basepath == BASEPATH)
    }
}