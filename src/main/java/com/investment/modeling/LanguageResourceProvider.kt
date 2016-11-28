package com.investment.modeling

class LanguageResourceProvider(language: LANGUAGES) :
        ResourceProvider(language.fileName) {

    enum class LANGUAGES(val fileName: String){
        RUS("configs/lang/rus.dat"),
        ENG("configs/lang/eng.dat")
    }

    fun reload(language: LANGUAGES){
        super.reload(language.fileName)
    }
}