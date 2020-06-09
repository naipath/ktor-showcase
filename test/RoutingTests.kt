package `2routing`

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class RoutingTests {
    @Test
    fun testRoot() {
        withTestApplication({ routing() }) {

            // No header
            handleRequest(HttpMethod.Get, "/resources/get-a-id").apply {
                assertNull(response.status())
            }

            // With header
            handleRequest(HttpMethod.Get, "/resources/get-a-id") {
                this.addHeader("Content-Type", "application/xml")
            }.apply {

                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Requested get-a-id", response.content)
                assertEquals("OpenValue", response.headers.get("X-Powered-by"))

            }
        }
    }
}
