package com.investment.modeling

data class Investment(
        val id: String,
        val investmentTool: InvestmentTools,
        val startDate: Int,
        val endDate: Int,
        val maxProfitPercentage: Double,
        val maxLossPercentage: Double,
        val balance: Int
        ){

        override fun toString(): String {
                return "Инвестиция(" +
                        "ID = $id, " +
                        "Акция = $investmentTool, " +
                        "Начало = $startDate, " +
                        "Конец = $endDate, " +
                        "Прибыль = $maxProfitPercentage%, " +
                        "Убыток = $maxLossPercentage%, " +
                        "Баланс = $balance)"
        }
}