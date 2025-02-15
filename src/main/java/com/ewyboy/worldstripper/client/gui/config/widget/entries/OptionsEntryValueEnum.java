package com.ewyboy.worldstripper.client.gui.config.widget.entries;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Locale;
import java.util.function.Consumer;

public class OptionsEntryValueEnum<T extends Enum<T>> extends OptionsEntryValue<T> {

    private final Button button;
    private final String translationKey;

    public OptionsEntryValueEnum(String optionName, T[] values, T selected, Consumer<T> save) {
        super(optionName, save);
        this.translationKey = optionName;
        this.button = new Button(0, 0, 100, 20, new TranslationTextComponent(selected.name()), pressed -> value = values[(value.ordinal() + 1) % values.length]);
        this.value = selected;
    }

    @Override
    protected void drawValue(MatrixStack matrixStack, int entryWidth, int entryHeight, int x, int y, int mouseX, int mouseY, boolean selected, float partialTicks) {
        this.button.x = x + 135;
        this.button.y = y + entryHeight / 6;
        //this.button.setMessage(new TranslationTextComponent(translationKey + "_" + value.name().toLowerCase(Locale.ROOT)));
        this.button.setMessage(new StringTextComponent(value.name().replace("_", " ")));
        this.button.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public IGuiEventListener getListener() {
        return button;
    }
}
