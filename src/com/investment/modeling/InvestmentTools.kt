package com.investment.modeling

enum class InvestmentTools(val index: Int) {
    GAZPROM(0),
    SBERBANK(1),
    BASHNEFT(2),
    RUSAL(3),
    LUKOIL(4),
    GOLD(5),
    SILVER(6),
    USDRUB(7),
    EURRUB(8),
    YANDEX(9),
    ALROSA(10),
    AEROFLOT(11),
    MTS(12);
}
fun getTool(index: Int): InvestmentTools{
    InvestmentTools.values().forEach { e ->
        if (e.index == index)
            return e
    }
    throw IllegalArgumentException("Unable find tool for index " + index)
}