package com.keith.bloodtech.block.custom;

import com.keith.bloodtech.block.entity.CentrifugeBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class CentrifugeBlock extends BaseEntityBlock {
    public CentrifugeBlock(Properties properties) {
        super(properties);
    }
    public static final MapCodec<CentrifugeBlock> CODEC = simpleCodec(CentrifugeBlock::new);
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
        return new CentrifugeBlockEntity(blockPos, blockState);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(state.getBlock() != newState.getBlock()){
            if(level.getBlockEntity(pos) instanceof CentrifugeBlockEntity centrifugeBlockEntity){
                centrifugeBlockEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.getBlockEntity(pos) instanceof CentrifugeBlockEntity centrifugeBlockEntity){
            //insert item
            if(!stack.isEmpty()|| centrifugeBlockEntity.inventory.getStackInSlot(0).is(stack.getItem())){
                centrifugeBlockEntity.inventory.insertItem(0, stack.copy(), false);
                stack.shrink(1);
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
            }
            else if(stack.isEmpty() || (centrifugeBlockEntity.inventory.getStackInSlot(0).is(stack.getItem()))){
                ItemStack ItemInCentrifuge = centrifugeBlockEntity.inventory.extractItem(0,1,false);
                player.setItemInHand(InteractionHand.MAIN_HAND,ItemInCentrifuge);
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
            }
        }
        return ItemInteractionResult.SUCCESS;
    }
}
