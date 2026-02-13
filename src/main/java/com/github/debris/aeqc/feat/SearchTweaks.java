package com.github.debris.aeqc.feat;

import appeng.api.stacks.GenericStack;
import appeng.client.gui.AEBaseScreen;
import appeng.client.gui.me.common.MEStorageScreen;
import appeng.client.gui.me.patternaccess.PatternAccessTermScreen;
import com.github.debris.aeqc.reference.ExtendedAEReference;
import com.github.debris.aeqc.reference.TomStorageReference;
import com.github.debris.aeqc.util.AccessUtil;
import com.github.debris.aeqc.util.JeiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

public class SearchTweaks {
    public static boolean searchHovered(Minecraft client) {
        return execute(client, textField -> {
            GenericStack stack = JeiUtil.getHoveredStack();
            if (stack == null) return false;

            textField.setValue(stack.what().getDisplayName().getString());
            return true;
        });
    }

    public static boolean clear(Minecraft client) {
        return execute(client, textField -> {
            textField.setValue("");
            return true;
        });
    }

    private static boolean execute(Minecraft client, Function<EditBox, Boolean> action) {
        Screen screen = client.screen;
        if (!(screen instanceof AbstractContainerScreen<?> containerScreen)) return false;
        EditBox textField = findTextField(containerScreen);
        if (textField == null) return false;
        if (textField.isFocused()) return false;

        return action.apply(textField);
    }

    @SuppressWarnings({"deprecation", "RedundantIfStatement"})
    @Nullable
    private static EditBox findTextField(AbstractContainerScreen<?> screen) {
        MenuType<?> menuType = AccessUtil.getMenuType(screen.getMenu());
        ResourceLocation key = menuType != null ? BuiltInRegistries.MENU.getKey(menuType) : null;

        if (screen instanceof AEBaseScreen<?> aeBaseScreen) {
            AbstractWidget widget = findTextField_ae(aeBaseScreen, key);
            if (widget instanceof EditBox textField) {
                return textField;
            }
        }

        EditBox modWidget = findTextField_mod(screen, key);
        if (modWidget != null) {
            return modWidget;
        }

        return null;
    }

    @Nullable
    private static AbstractWidget findTextField_ae(AEBaseScreen<?> screen, @Nullable ResourceLocation key) {
        Map<String, AbstractWidget> widgets = getWidgets(screen);

        // normal, crafting, pattern encoding
        if (screen instanceof MEStorageScreen<?>) {
            return widgets.get("search");
        }

        if (screen instanceof PatternAccessTermScreen<?>) {
            return widgets.get("search");
        }


        if (ExtendedAEReference.PatternAccessTerminals.stream().anyMatch(x -> x.equals(key))) {
            return widgets.get("search_out");
        }

        return null;
    }

    @Nullable
    private static EditBox findTextField_mod(AbstractContainerScreen<?> screen, @Nullable ResourceLocation key) {
        if (TomStorageReference.Terminals.stream().anyMatch(x -> x.equals(key))) {
            return screen.children().stream()
                    .filter(x -> x instanceof EditBox)
                    .map(x -> (EditBox) x)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    private static Map<String, AbstractWidget> getWidgets(AEBaseScreen<?> screen) {
        return AccessUtil.getWidgets(AccessUtil.getWidgetContainer(screen));
    }
}
