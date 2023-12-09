package com.github.numbersir.twee3highlight;

import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;

final class Twee3LanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    @NotNull
    @Override
    public Language getLanguage() {
        return Twee3Language.INSTANCE;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.SPACING_SETTINGS) {
            consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS");
            consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Separator");
        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
            consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
        }
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return """
                :: Hello World [widget]
                <<widget>>
                    hello world $name!
                    <span>this is a _desc test</span>
                    <<set $arg to 123>>
                    <!-- some comments -->
                    /* other comments */
                <</widget>>
                """;
    }

}
