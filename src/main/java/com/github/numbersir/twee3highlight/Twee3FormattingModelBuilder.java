package com.github.numbersir.twee3highlight;

import com.github.numbersir.twee3highlight.psi.Twee3Types;
import com.intellij.formatting.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

final class Twee3FormattingModelBuilder implements FormattingModelBuilder {

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, Twee3Language.INSTANCE)
                .around(Twee3Types.SEPARATOR)
                .spaceIf(settings.getCommonSettings(Twee3Language.INSTANCE.getID()).SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .before(Twee3Types.PROPERTY)
                .none();
    }

    @Override
    public @NotNull FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        final CodeStyleSettings codeStyleSettings = formattingContext.getCodeStyleSettings();
        return FormattingModelProvider
                .createFormattingModelForPsiFile(formattingContext.getContainingFile(),
                        new Twee3Block(formattingContext.getNode(),
                                Wrap.createWrap(WrapType.NONE, false),
                                Alignment.createAlignment(),
                                createSpaceBuilder(codeStyleSettings)),
                        codeStyleSettings);
    }

}
