package com.keith.bloodtech.block.entity;

import com.keith.bloodtech.BloodTech;
import com.keith.bloodtech.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, BloodTech.MODID);

    public static final Supplier<BlockEntityType<CentrifugeBlockEntity>> CENTRIFUGE_BLOCK_ENTITY = BLOCK_ENTITIES.register("centrifuge_block_entity", () -> BlockEntityType.Builder.of(
            CentrifugeBlockEntity::new, ModBlocks.CENTRIFUGE_BLOCK.get()).build(null)
    );
    }
