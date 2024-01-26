package no.uio.ifi.in2000.erlenjoa.oblig1

fun isPalindrome(str: String): Boolean {
    return str.reversed().lowercase() == str.lowercase()
}