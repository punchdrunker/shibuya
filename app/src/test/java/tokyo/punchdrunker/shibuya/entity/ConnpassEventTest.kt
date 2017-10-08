package tokyo.punchdrunker.shibuya.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConnpassEventTest {

    @Test
    fun createEvent() {
        val event = ConnpassEvent(1, "title", "catch", "desc", "place", "http://example.com", "started_at", 100, 10)
        assertEquals(1, event.event_id)
        assertEquals("title", event.title)
        assertEquals("catch", event.catch)
        assertEquals("desc", event.description)
        assertEquals("place", event.place)
        assertEquals("http://example.com", event.event_url)
        assertEquals("started_at", event.started_at)
        assertEquals(100, event.limit)
        assertEquals(10, event.accepted)
    }
}
