package tokyo.punchdrunker.shibuya.response

import tokyo.punchdrunker.shibuya.entity.ConnpassEvent

/**
 * Created by punchdrunker on 2017/09/30.
 */
data class ListEventsResponse(
        val results_returned: Int,
        val results_available: Int,
        val results_start: Int,
        val events: List<ConnpassEvent>)
