package pl.schibsted.xml.stream

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter
import java.time.LocalDateTime
import javax.xml.stream.XMLOutputFactory
import javax.xml.stream.XMLStreamWriter

fun main(args: Array<String>) {
    val out = System.out
    val writer = IndentingXMLStreamWriter(XMLOutputFactory.newFactory().createXMLStreamWriter(out, "UTF-8"))
    serialize04(writer)
}

private fun serialize04(writer: IndentingXMLStreamWriter) {

    fun XMLStreamWriter.document(init: XMLStreamWriter.() -> Unit): XMLStreamWriter {
        this.writeStartDocument()
        this.init()
        this.writeEndDocument()
        return this
    }

    fun XMLStreamWriter.element(name: String, init: XMLStreamWriter.() -> Unit): XMLStreamWriter {
        this.writeStartElement(name)
        this.init()
        this.writeEndElement()
        return this
    }

    fun XMLStreamWriter.element(name: String, content: String) {
        element(name) {
            writeCharacters(content)
        }
    }

    fun XMLStreamWriter.attribute(name: String, value: String) = writeAttribute(name, value)

    writer.document {
        element("persons") {
            attribute("version", "1.1")
            attribute("created", LocalDateTime.now().toString())
            persons.forEach { (name, accounts) ->
                element("person") {
                    element("name") {
                        element("first", name.first)
                        element("last", name.last)
                    }
                    element("accounts") {
                        accounts.forEach { (bank, number, currency) ->
                            element("account") {
                                element("bank", bank)
                                element("number", number)
                                element("currency", currency.currencyCode)
                            }
                        }
                    }
                }
            }
        }
    }
    writer.flush()
}



