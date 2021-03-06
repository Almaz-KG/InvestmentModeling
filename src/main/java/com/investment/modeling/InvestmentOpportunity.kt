package com.investment.modeling

data class InvestmentOpportunity(
        val investmentTool: InvestmentTools,
        val maxProfitPercentage: Double,
        val maxLossPercentage: Double,
        val duration: Int) {

    override fun toString(): String {
        return "${langResourceProvider.getText("investment-opportunity.investment")} ($investmentTool," +
                "${langResourceProvider.getText("investment-opportunity.max-revenue")}=$maxProfitPercentage%, " +
                "${langResourceProvider.getText("investment-opportunity.max-loss")}=$maxLossPercentage%, " +
                "${langResourceProvider.getText("investment-opportunity.duration")}=$duration " +
                "${langResourceProvider.getText("investment-opportunity.time-period")})"
    }
}

