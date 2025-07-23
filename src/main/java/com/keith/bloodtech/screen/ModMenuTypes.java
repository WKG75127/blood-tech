package com.keith.bloodtech.screen;

import com.keith.bloodtech.BloodTech;
import com.keith.bloodtech.screen.custom.CentrifugeMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS=
            DeferredRegister.create(Registries.MENU, BloodTech.MODID);
    public  static final DeferredHolder<MenuType<?>, MenuType<CentrifugeMenu>> CENTRIFUGE_MENU = registerMenuType("centrifuge_menu", CentrifugeMenu::new);
    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name,()-> IMenuTypeExtension.create(factory));
    }
}
