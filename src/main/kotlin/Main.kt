fun main() {
    println("Введите сообщение для шифрования:")
    val message = readLine() ?: ""

    println("Введите ключевое слово:")
    val keyword = readLine() ?: ""

    val encrypted = vigenereCipher(message, keyword, true)
    println("Зашифрованное сообщение: ${toUnicodeString(encrypted)}")

    val decrypted = vigenereCipher(encrypted, keyword, false)
    println("Расшифрованное сообщение: $decrypted")
}

fun vigenereCipher(message: String, keyword: String, encrypt: Boolean): String {
    val encryptedText = StringBuilder()
    val a = 'A'.toInt()

    for (i in message.indices) {
        val messageChar = message[i].toUpperCase()
        val keywordChar = keyword[i % keyword.length].toUpperCase()
        if (messageChar < 'A' || messageChar > 'Z') {
            encryptedText.append(messageChar)
            continue
        }
        val op = if (encrypt) 1 else -1
        val newChar = (a + (messageChar.toInt() - a + op * (keywordChar.toInt() - a) + 26) % 26).toChar()
        encryptedText.append(newChar)
    }

    return encryptedText.toString()
}

fun toUnicodeString(input: String): String {
    val unicodeChars = input.toCharArray().joinToString("") { "\\u" + it.toInt().toString(16).padStart(4, '0') }
    return "\"$unicodeChars\""
}