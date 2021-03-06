package com.investment.modeling

import main.java.com.investment.modeling.LANGUAGES

class LanguageResourceProvider(language: LANGUAGES) :
        ResourceProvider(language.fileName) {

    fun reload(language: LANGUAGES){
        super.reload(language.fileName)
    }
}