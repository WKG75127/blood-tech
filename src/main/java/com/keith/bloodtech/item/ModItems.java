package com.keith.bloodtech.item;

import com.keith.bloodtech.fluid.ModFluids;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.keith.bloodtech.block.ModBlocks.*;
import static com.keith.bloodtech.BloodTech.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredItem<Item> BLOOD = ITEMS.registerSimpleItem("blood", new Item.Properties());
    public static final DeferredItem<Item> PLASMA = ITEMS.registerSimpleItem("plasma", new Item.Properties());
    public static final DeferredItem<Item> REDCELL = ITEMS.registerSimpleItem("red_cell", new Item.Properties());
    public static final DeferredItem<Item> WHITECELL = ITEMS.registerSimpleItem("white_cell", new Item.Properties());
    public static final DeferredItem<Item> PLATELET = ITEMS.registerSimpleItem("platelet", new Item.Properties());
    public static final DeferredItem<Item> BLOOD_BUCKET = ITEMS.register("blood_bucket",() -> new BucketItem(ModFluids.SOURCE_BLOOD.get(),new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> PLATELET_BIT = ITEMS.registerSimpleItem("platelet_bit", new Item.Properties());
    public static final DeferredItem<Item> VIRUS = ITEMS.registerSimpleItem("virus", new Item.Properties());
    public static final DeferredItem<Item> BUFFY = ITEMS.registerSimpleItem("buffy_shell", new Item.Properties());
    public static final DeferredItem<Item> HEMOGLOBIN = ITEMS.registerSimpleItem("hemoglobin", new Item.Properties());
    public static final DeferredItem<Item> HEART = ITEMS.registerSimpleItem("heart", new Item.Properties());
    public static final DeferredItem<BlockItem> MECHANICAL_HEART_ITEM = ITEMS.registerSimpleBlockItem("heartmachine",MECHANICAL_HEART);
    public static final DeferredItem<BlockItem> CENTRIFUGE_ITEM = ITEMS.registerSimpleBlockItem("centrifuge",CENTRIFUGE_BLOCK);

}
