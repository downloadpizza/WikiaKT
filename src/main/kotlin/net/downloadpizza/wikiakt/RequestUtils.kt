package net.downloadpizza.wikiakt

fun List<Int>.intCommaList() = this.joinToString(",")

fun List<String>.stringCommaList() = this.joinToString(",", transform = String::replaceWhitespace)

fun String.replaceWhitespace() = this.replace(Regex("\\w"), "_")