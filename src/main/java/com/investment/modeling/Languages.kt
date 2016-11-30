package main.java.com.investment.modeling

import com.investment.modeling.langResourceProvider

enum class LANGUAGES(val literal: String, val fileName: String){
    RUS("rus", "configs/lang/rus.dat"),
    ENG("eng", "configs/lang/eng.dat");

    companion object {
        @JvmStatic
        fun getLanguage(language: String): LANGUAGES {
            LANGUAGES.values()
                    .filter { it.literal.equals(language.toLowerCase()) }
                    .forEach { return it }

            throw IllegalArgumentException(langResourceProvider.getText("languages.error.unsupported-language-exception"))
        }
    }
}
