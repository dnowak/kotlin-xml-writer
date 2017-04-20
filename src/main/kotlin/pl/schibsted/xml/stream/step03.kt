package pl.schibsted.xml.stream

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter
import java.time.LocalDateTime
import javax.xml.stream.XMLOutputFactory
import javax.xml.stream.XMLStreamWriter

fun main(args: Array<String>) {
    val out = System.out
    val writer = IndentingXMLStreamWriter(XMLOutputFactory.newFactory().createXMLStreamWriter(out, "UTF-8"))
    serialize03(persons, writer)
}

fun serialize03(persons: List<Person>, writer: IndentingXMLStreamWriter) {
    fun XMLStreamWriter.element(name: String, content: String) {
        writeStartElement(name)
        writeCharacters(content)
        writeEndElement()
    }

    fun XMLStreamWriter.attribute(name: String, value: String) = writeAttribute(name, value)
    with(writer) {
        writeStartDocument()
        writeStartElement("persons")
        attribute("version", "1.1")
        attribute("created", LocalDateTime.now().toString())
        persons.forEach { (name, accounts) ->
            writeStartElement("person")
            writeStartElement("name")
            element("first", name.first)
            element("last", name.last)
            writeEndElement()
            writeStartElement("accounts")
            accounts.forEach { (bank, number, currency) ->
                writeStartElement("account")
                element("bank", bank)
                element("number", number)
                element("currency", currency.currencyCode)
                writeEndElement()
            }
            writeEndElement()
            writeEndElement()
        }
        writeEndElement()
        writeEndDocument()
        flush()
    }

}


