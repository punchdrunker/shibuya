package tokyo.punchdrunker.shibuya.response

import tokyo.punchdrunker.shibuya.entity.ConnpassEvent

/**
 * Created by nanao on 2017/09/30.
 */
data class ListEventsResponse(
        val resultsReturned: Int,
        val resultsAvailable: Int,
        val resultsStart: Int,
        val events: List<ConnpassEvent>)