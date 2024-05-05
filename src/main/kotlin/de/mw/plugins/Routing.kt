package de.mw.plugins

import de.mw.utils.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import java.util.*

fun Application.configureRouting() {

    var countState = 0

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        get("/") {
            val htmlContent = htmlBasePage("Getting started page") {
                div {
                    h1 {
                        classes = setOf("text-4xl", "font-bold", "underline")
                        +"Getting started page"
                    }

                    button {
                        hxPost("/increase-counter")
                        hxSwap(HxSwapOption.NONE)
                        classes =
                            setOf("ring-offset-background focus-visible:ring-ring whitespace-nowrap rounded-md bg-black px-3 py-2 text-sm font-medium text-white transition-colors hover:bg-black/90 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50")
                        +"Add"
                    }

                    div {
                        id = "count-value"
                        classes = setOf("mb-2 space-y-1 min-w-10")
                        +"$countState"
                    }
                }
            }

            call.respondText(htmlContent, ContentType.Text.Html)
        }

        post("/increase-counter") {
            countState++
            val htmlContent = buildHTMLString {
                div {
                    id = "count-value"
                    hxSwapOob()
                    +"$countState"
                }
            }

            call.respondText(htmlContent, ContentType.Text.Html)
        }

        // Configure static resources
        staticResources("/static", "static")
    }
}
