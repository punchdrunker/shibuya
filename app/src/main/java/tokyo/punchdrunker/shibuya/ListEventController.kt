package tokyo.punchdrunker.shibuya

import com.airbnb.epoxy.TypedEpoxyController
import tokyo.punchdrunker.shibuya.entity.ConnpassEvent

/**
 * Created by punchdrunker on 2017/10/01.
 */
class ListEventController : TypedEpoxyController<List<ConnpassEvent>>() {
    override fun buildModels(events: List<ConnpassEvent>?) {
        events ?: return

        events.forEach {
            FeedEventBindingModel_()
                    .id(it.event_id)
                    .event(it)
                    .addTo(this)
        }
    }
}
