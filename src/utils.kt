data class Size(val width: Int, val height: Int)

// ЛАТИНИЦА

// Алфавит
val dictionaryEN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()

// Частотность символов
val baseFrequencySymbolsEN = mapOf(
    'E' to 12.7,
    'T' to 9.06,
    'A' to 8.17,
    'O' to 7.51,
    'I' to 6.97,
    'N' to 6.75,
    'S' to 6.33,
    'H' to 6.09,
    'R' to 5.99,
    'D' to 4.25,
    'L' to 4.03,
    'C' to 2.78,
    'U' to 2.76,
    'M' to 2.41,
    'W' to 2.36,
    'F' to 2.23,
    'G' to 2.02,
    'Y' to 1.97,
    'P' to 1.93,
    'B' to 1.49,
    'V' to 0.98,
    'K' to 0.77,
    'X' to 0.15,
    'J' to 0.15,
    'Q' to 0.1,
    'Z' to 0.05
)

// КИРИЛИЦА

// Алфавит
val dictionaryRU = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".toCharArray()

// Частотность символов
val baseFrequencySymbolsRU = mapOf(
    'О' to 10.97,
    'Е' to 8.45,
    'А' to 8.01,
    'И' to 7.35,
    'Н' to 6.70,
    'Т' to 6.26,
    'С' to 5.47,
    'Р' to 4.73,
    'В' to 4.54,
    'Л' to 4.40,
    'К' to 3.49,
    'М' to 3.21,
    'Д' to 2.98,
    'П' to 2.81,
    'У' to 2.62,
    'Я' to 2.01,
    'Ы' to 1.90,
    'Ь' to 1.74,
    'Г' to 1.70,
    'З' to 1.65,
    'Б' to 1.59,
    'Ч' to 1.44,
    'Й' to 1.21,
    'Х' to 0.97,
    'Ж' to 0.94,
    'Ш' to 0.73,
    'Ю' to 0.64,
    'Ц' to 0.48,
    'Щ' to 0.36,
    'Э' to 0.32,
    'Ф' to 0.26,
    'Ъ' to 0.04,
    'Ё' to 0.04
)
