package com.keith.bloodtech.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.List;

public class ResistanceGenBlockEntity extends BlockEntity {
        private int fuelLevel = 0;
        private @Nullable Holder<MobEffect> primaryEffect;
    public ResistanceGenBlockEntity(BlockPos
                                            pos, BlockState blockState) {
        super(ModBlockEntities.RESISTANCE_GENERATOR_BLOCK_ENTITY.get(), pos, blockState);
    }

    public void increaseFeulLevel(int amount){
        fuelLevel += amount;
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if(fuelLevel>0) {
            applyEffect(level, blockPos, primaryEffect);
            fuelLevel-=1;
        }
    }

    public void setPrimaryEffect(@Nullable Holder<MobEffect> primaryEffect) {
        this.primaryEffect = primaryEffect;
    }

    private static void applyEffect(Level level, BlockPos pos,@Nullable Holder<MobEffect> primaryEffect) {
        double d0 = 90;
        int i = 1;

        AABB aabb = (new AABB(pos)).inflate(d0).expandTowards((double) 0.0F, (double) level.getHeight(), (double) 0.0F);
        List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, aabb);
        if(primaryEffect != null) {
            for (LivingEntity mobs : list) {
                mobs.addEffect(new MobEffectInstance(primaryEffect, 20, i, true, true));
            }
        }
    }

}
