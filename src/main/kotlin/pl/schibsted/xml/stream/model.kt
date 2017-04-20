package pl.schibsted.xml.stream

import java.util.Currency

data class Name(val first: String, val last: String)

data class Account(val bank: String, val number: String, val currency: Currency)

data class Person(val name: Name, val accounts: List<Account>)

val USD: Currency = Currency.getInstance("USD")
val EUR: Currency = Currency.getInstance("EUR")

val persons = listOf(
        Person(Name("John", "Doe"),
                listOf(
                        Account("JP Morgan Chase & Co", "000-111-222-333", USD),
                        Account("Goldman Sachs", "000-111-222-444", EUR))),
        Person(Name("Jane", "Doe"),
                listOf(
                        Account("JP Morgan Chase & Co", "000-777-222-333", USD),
                        Account("Goldman Sachs", "000-777-222-444", EUR)))
)
