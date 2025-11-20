package com.keith.bloodtech.block.custom;

import com.keith.bloodtech.BloodTech;
import com.keith.bloodtech.block.entity.CentrifugeBlockEntity;
import com.keith.bloodtech.block.entity.ModBlockEntities;
import com.keith.bloodtech.block.entity.ResistanceGenBlockEntity;
import com.keith.bloodtech.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ResistanceGenBlock extends BaseEntityBlock {
    public ResistanceGenBlock(Properties properties) {
        super(properties);
    }
    public static final MapCodec<ResistanceGenBlock> CODEC = simpleCodec(ResistanceGenBlock::new);
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
    /*Block Entity */
    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ResistanceGenBlockEntity(blockPos, blockState);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (level.getBlockEntity(pos) instanceof ResistanceGenBlockEntity resistancegenblockentity) {
            if (!level.isClientSide) {
                if(stack.is(ModItems.BLOOD_BUCKET)) {
                    resistancegenblockentity.increaseFeulLevel(320);
                    player.setItemInHand(player.getUsedItemHand(),new ItemStack(Items.BUCKET));
                    return ItemInteractionResult.SUCCESS;
                }
                if(stack.is(ModItems.WEAK_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.WEAKNESS);
                }if(stack.is(ModItems.RESISTANCE_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.DAMAGE_RESISTANCE);
                }if(stack.is(ModItems.ABSORPTION_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.ABSORPTION);
                }if(stack.is(ModItems.GLOWING_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.GLOWING);
                }if(stack.is(ModItems.BLIND_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.BLINDNESS);
                }if(stack.is(ModItems.HASTE_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.DIG_SPEED);
                }if(stack.is(ModItems.WATER_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.WATER_BREATHING);
                }if(stack.is(ModItems.INVIS_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.INVISIBILITY);
                }if(stack.is(ModItems.FATIGUE_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.DIG_SLOWDOWN);
                }if(stack.is(ModItems.JUMP_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.JUMP);
                }if(stack.is(ModItems.NAUSEA_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.CONFUSION);
                }if(stack.is(ModItems.POISON_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.POISON);
                }if(stack.is(ModItems.SPEED_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.MOVEMENT_SPEED);
                }if(stack.is(ModItems.SLOW_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.MOVEMENT_SLOWDOWN);
                }if(stack.is(ModItems.STRENGTH_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.DAMAGE_BOOST);
                }if(stack.is(ModItems.REGEN_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.REGENERATION);
                }if(stack.is(ModItems.VISION_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.NIGHT_VISION);
                }if(stack.is(ModItems.WITHER_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.WITHER);
                }if(stack.is(ModItems.LEVITATION_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.LEVITATION);
                }if(stack.is(ModItems.SLOW_FALL_CHARM)){
                    resistancegenblockentity.setPrimaryEffect(MobEffects.SLOW_FALLING);
                }if(stack.is(ModItems.FIRE_RESISTANCE_CHARM)) {
                    resistancegenblockentity.setPrimaryEffect(MobEffects.FIRE_RESISTANCE);
                }
            }
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()) {
            return null;
        }

        return createTickerHelper(blockEntityType, ModBlockEntities.RESISTANCE_GENERATOR_BLOCK_ENTITY.get(),
                (level1, blockPos, blockState, blockEntity) -> blockEntity.tick(level1, blockPos, blockState));
    }
}

