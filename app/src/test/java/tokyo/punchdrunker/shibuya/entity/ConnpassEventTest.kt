package tokyo.punchdrunker.shibuya.entity

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Test

class ConnpassEventTest {

    @Test
    fun createEvent() {
        val event = ConnpassEvent(1, "title", "catch", "desc", "place", "http://example.com", "started_at", 100, 10)
        Assert.assertThat(event.event_id, `is`(1))
        Assert.assertThat(event.title, `is`("title"))
        Assert.assertThat(event.catch, `is`("catch"))
        Assert.assertThat(event.description, `is`("desc"))
        Assert.assertThat(event.place, `is`("place"))
        Assert.assertThat(event.event_url, `is`("http://example.com"))
        Assert.assertThat(event.started_at, `is`("started_at"))
        Assert.assertThat(event.limit, `is`(100))
        Assert.assertThat(event.accepted, `is`(10))
    }
}