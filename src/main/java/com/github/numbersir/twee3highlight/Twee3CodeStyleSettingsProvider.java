package com.github.numbersir.twee3highlight;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.psi.codeStyle.CodeStyleConfigurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class Twee3CodeStyleSettingsProvider extends CodeStyleSettingsProvider {

    @Override
    public CustomCodeStyleSettings createCustomSettings(@NotNull CodeStyleSettings settings) {
        return new Twee3CodeStyleSettings(settings);
    }

    @Nullable
    @Override
    public String getConfigurableDisplayName() {
        return "Twee3";
    }

    @NotNull
    public CodeStyleConfigurable createConfigurable(@NotNull CodeStyleSettings settings, @NotNull CodeStyleSettings modelSettings) {
        return new CodeStyleAbstractConfigurable(settings, modelSettings, this.getConfigurableDisplayName()) {
            @Override
            protected @NotNull CodeStyleAbstractPanel createPanel(@NotNull CodeStyleSettings settings) {
                return new Twee3CodeStyleMainPanel(getCurrentSettings(), settings);
            }
        };
    }

    private static class Twee3CodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
        public Twee3CodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(Twee3Language.INSTANCE, currentSettings, settings);
        }

    }

}
