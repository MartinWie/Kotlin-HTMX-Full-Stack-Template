package de.mw.utils

import kotlinx.html.*
import kotlinx.html.stream.appendHTML


fun htmlBasePage(pageTitle: String, bodyTags: TagConsumer<StringBuilder>.() -> Unit): String {
    return "<!DOCTYPE html>" + buildHTMLString {
        head {
            title = pageTitle

            // HTMX 1.9.11 minified
            script { src = "/static/htmx.min.js" }

            // CSS (mainly Tailwind)
            link {
                rel = "stylesheet"
                href = "/static/output.css"
            }

            script(type = ScriptType.textJavaScript) {
                unsafe {
                    // Using unsafe{}.raw() to insert raw HTML/JS.
                    // Be cautious with 'unsafe' as it can potentially open up for XSS vulnerabilities
                    // if untrusted user input is inserted into the HTML.
                    raw(
                        """
            document.addEventListener("DOMContentLoaded", (event) => {
                document.body.addEventListener('htmx:beforeSwap', function(evt) {
                    if (evt.detail.xhr.status === 422) {
                        console.log("setting status to paint");
                        // allow 422 responses to swap as we are using this as a signal that
                        // a form was submitted with bad data and want to rerender with the errors
                        //
                        // set isError to false to avoid error logging in console
                        evt.detail.shouldSwap = true;
                        evt.detail.isError = false;
                    }
                });
            });
        """.trimIndent()
                    )
                }
            }

        }
        body {
            classes = setOf("text-center", "items-center")
            bodyTags()
        }
    }
}

/**
 * Builds an HTML string using the provided builder action.
 *
 * @param builderAction The action to build the HTML content.
 * @return The generated HTML string.
 *
 * Example usage:
 * body {
 *     h1 { +"Hello, World!" }
 * }
 */
fun buildHTMLString(builderAction: TagConsumer<StringBuilder>.() -> Unit): String {
    return buildString {
        appendHTML().builderAction()
    }
}
