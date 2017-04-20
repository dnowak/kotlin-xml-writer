package pl.schibsted.xml.stream

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter
import java.time.LocalDateTime
import javax.xml.stream.XMLOutputFactory

fun main(args: Array<String>) {
    val out = System.out
    val writer = IndentingXMLStreamWriter(XMLOutputFactory.newFactory().createXMLStreamWriter(out, "UTF-8"))
    serialize02(persons, writer)
}

fun serialize02(persons: List<Person>, writer: IndentingXMLStreamWriter) {
    with(writer) {
        writeStartDocument()
        writeStartElement("persons")
        writeAttribute("version", "1.1")
        writeAttribute("created", LocalDateTime.now().toString())
        persons.forEach { (name, accounts) ->
            writeStartElement("person")
            writeStartElement("name")
            writeStartElement("first")
            writeCharacters(name.first)
            writeEndElement()
            writeStartElement("last")
            writeCharacters(name.last)
            writeEndElement()
            writeEndElement()
            writeStartElement("accounts")
            accounts.forEach { (bank, number, currency) ->
                writeStartElement("account")
                writeStartElement("bank")
                writeCharacters(bank)
                writeEndElement()
                writeStartElement("number")
                writeCharacters(number)
                writeEndElement()
                writeStartElement("currency")
                writeCharacters(currency.currencyCode)
                writeEndElement()
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

