package com.keith.bloodtech.item;

import com.keith.bloodtech.fluid.ModFluids;
import com.keith.bloodtech.item.custom.Syringe;
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
    public static final DeferredItem<Item> SPEED_CHARM = ITEMS.registerSimpleItem("speed_charm", new Item.Properties());
    public static final DeferredItem<Item> SLOW_CHARM = ITEMS.registerSimpleItem("slow_charm", new Item.Properties());
    public static final DeferredItem<Item> HASTE_CHARM = ITEMS.registerSimpleItem("haste_charm", new Item.Properties());
    public static final DeferredItem<Item> FATIGUE_CHARM = ITEMS.registerSimpleItem("fatigue_charm", new Item.Properties());
    public static final DeferredItem<Item> STRENGTH_CHARM = ITEMS.registerSimpleItem("strength_charm", new Item.Properties());
    public static final DeferredItem<Item> JUMP_CHARM = ITEMS.registerSimpleItem("jump_charm", new Item.Properties());
    public static final DeferredItem<Item> NAUSEA_CHARM = ITEMS.registerSimpleItem("nausea_charm", new Item.Properties());
    public static final DeferredItem<Item> REGEN_CHARM = ITEMS.registerSimpleItem("regen_charm", new Item.Properties());
    public static final DeferredItem<Item> RESISTANCE_CHARM = ITEMS.registerSimpleItem("resistance_charm", new Item.Properties());
    public static final DeferredItem<Item> FIRE_RESISTANCE_CHARM = ITEMS.registerSimpleItem("fire_resistance_charm", new Item.Properties());
    public static final DeferredItem<Item> WATER_CHARM = ITEMS.registerSimpleItem("water_charm", new Item.Properties());
    public static final DeferredItem<Item> INVIS_CHARM = ITEMS.registerSimpleItem("invis_charm", new Item.Properties());
    public static final DeferredItem<Item> BLIND_CHARM = ITEMS.registerSimpleItem("blind_charm", new Item.Properties());
    public static final DeferredItem<Item> VISION_CHARM = ITEMS.registerSimpleItem("vision_charm", new Item.Properties());
    public static final DeferredItem<Item> WEAK_CHARM = ITEMS.registerSimpleItem("weak_charm", new Item.Properties());
    public static final DeferredItem<Item> POISON_CHARM = ITEMS.registerSimpleItem("poison_charm", new Item.Properties());
    public static final DeferredItem<Item> WITHER_CHARM = ITEMS.registerSimpleItem("wither_charm", new Item.Properties());
    public static final DeferredItem<Item> ABSORPTION_CHARM = ITEMS.registerSimpleItem("absorption_charm", new Item.Properties());
    public static final DeferredItem<Item> LEVITATION_CHARM = ITEMS.registerSimpleItem("levitation_charm", new Item.Properties());
    public static final DeferredItem<Item> SLOW_FALL_CHARM = ITEMS.registerSimpleItem("slow_fall_charm", new Item.Properties());
    public static final DeferredItem<Item> GLOWING_CHARM = ITEMS.registerSimpleItem("glowing_charm", new Item.Properties());
    public static final DeferredItem<Item> BLANK_CHARM = ITEMS.registerSimpleItem("blank_charm", new Item.Properties());
    public static final DeferredItem<Item> SYRINGE = ITEMS.register("syringe",() -> new Syringe(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> BLOOD_SYRINGE = ITEMS.registerSimpleItem("blood_syringe", new Item.Properties().stacksTo(1));


    public static final DeferredItem<BlockItem> MECHANICAL_HEART_ITEM = ITEMS.registerSimpleBlockItem("heartmachine",MECHANICAL_HEART);
    public static final DeferredItem<BlockItem> CENTRIFUGE_ITEM = ITEMS.registerSimpleBlockItem("centrifuge",CENTRIFUGE_BLOCK);
    public static final DeferredItem<BlockItem> RESISTANCE_GENERATOR_ITEM = ITEMS.registerSimpleBlockItem("resistance_generator_item",RESISTANCE_GENERATOR);
}
