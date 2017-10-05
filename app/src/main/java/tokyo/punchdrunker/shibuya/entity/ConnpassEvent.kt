package tokyo.punchdrunker.shibuya.entity

/**
 * Created by punchdrunker on 2017/09/30.
 */
data class ConnpassEvent(
        val event_id: Int,
        val title: String,
        val catch: String,
        val description: String,
        val place: String,
        val event_url: String,
        val started_at: String,
        val limit: Int,
        val accepted: Int) {
    fun descriptionForFeed(): String {
        return description.replace("<.+?>".toRegex(), " ").substring(0, 100)
    }
}
