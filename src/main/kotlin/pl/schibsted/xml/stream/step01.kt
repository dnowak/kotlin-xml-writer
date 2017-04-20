package pl.schibsted.xml.stream

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter
import java.time.LocalDateTime
import javax.xml.stream.XMLOutputFactory

fun main(args: Array<String>) {
    val out = System.out
    val writer = IndentingXMLStreamWriter(XMLOutputFactory.newFactory().createXMLStreamWriter(out, "UTF-8"))
    serialize01(persons, writer)
}

fun serialize01(persons: List<Person>, writer: IndentingXMLStreamWriter) {
    writer.writeStartDocument()
    writer.writeStartElement("persons")
    writer.writeAttribute("version", "1.1")
    writer.writeAttribute("created", LocalDateTime.now().toString())
    persons.forEach { (name, accounts) ->
        writer.writeStartElement("person")
        writer.writeStartElement("name")
        writer.writeStartElement("first")
        writer.writeCharacters(name.first)
        writer.writeEndElement()
        writer.writeStartElement("last")
        writer.writeCharacters(name.last)
        writer.writeEndElement()
        writer.writeEndElement()
        writer.writeStartElement("accounts")
        accounts.forEach { (bank, number, currency) ->
            writer.writeStartElement("account")
            writer.writeStartElement("bank")
            writer.writeCharacters(bank)
            writer.writeEndElement()
            writer.writeStartElement("number")
            writer.writeCharacters(number)
            writer.writeEndElement()
            writer.writeStartElement("currency")
            writer.writeCharacters(currency.currencyCode)
            writer.writeEndElement()
            writer.writeEndElement()
        }
        writer.writeEndElement()
        writer.writeEndElement()
    }
    writer.writeEndElement()
    writer.writeEndDocument()
    writer.flush()
}

