package com.keith.bloodtech.block.entity;

import com.keith.bloodtech.item.ModItems;
import com.keith.bloodtech.recipe.CentrifugeRecipe;
import com.keith.bloodtech.recipe.CentrifugeRecipeInput;
import com.keith.bloodtech.recipe.ModRecipes;
import com.keith.bloodtech.screen.custom.CentrifugeMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class CentrifugeBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler inventory = new ItemStackHandler(5){

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide){
                level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(), 3);
            }
        }
    };
    private static final int INPUT_SLOT = 0;
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
    List<ItemStack> outputs = new ArrayList<ItemStack>();
    public CentrifugeBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CENTRIFUGE_BLOCK_ENTITY.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> CentrifugeBlockEntity.this.progress;
                    case 1 -> CentrifugeBlockEntity.this.maxProgress;
                    default -> 0;
                };

            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: CentrifugeBlockEntity.this.progress = value;
                    case 1: CentrifugeBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        };
    }
    @Override
    public Component getDisplayName() {
        return Component.literal("Centrifuge");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new CentrifugeMenu(i, inventory, this, this.data);
    }

    public void drops(){
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots();i++){
            inv.setItem(i, inventory.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putInt("centrifuge.progress", progress);
        tag.putInt("centrifuge.max_progress", maxProgress);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("centrifuge.progress");
        maxProgress = tag.getInt("centrifuge.max_progress");
    }
    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level, blockPos, blockState);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 72;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }
    private void craftItem() {
        inventory.extractItem(INPUT_SLOT, 1, false);
        for(ItemStack output: outputs){
            setOutput(output);
        }
    }
    private void setOutput(ItemStack item){
        for(int output_slot = 1;output_slot<=4;output_slot++){
            if (canInsertAmountIntoOutputSlot(item.getCount(),output_slot) && canInsertItemIntoOutputSlot(item,output_slot)){
                inventory.setStackInSlot(output_slot, new ItemStack(item.getItem(),
                        inventory.getStackInSlot(output_slot).getCount() + item.getCount()));
                return;
            }
        }
    }
    private boolean hasRecipe() {
        Optional<RecipeHolder<CentrifugeRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }
        outputs = recipe.get().value().getResultItemStack();
        return canFitAtLeastOneOutput(outputs);
    }

    private Optional<RecipeHolder<CentrifugeRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.CENTRIFUGE_TYPE.get(), new CentrifugeRecipeInput(inventory.getStackInSlot(INPUT_SLOT)), level);
    }

    private boolean canFitAtLeastOneOutput(List<ItemStack> list){
        for(ItemStack i:list){
            if(canInsertItemIntoOutput(i, i.getCount())){
                return true;
            }
        }
        return false;
    }
    private boolean canInsertItemIntoOutput(ItemStack output, int count){
        for(int output_slot = 1;output_slot<=4;output_slot++){
            if (canInsertAmountIntoOutputSlot(count,output_slot) && canInsertItemIntoOutputSlot(output,output_slot)){
                return true;
            }
        }
        return false;
    }
    private boolean canInsertItemIntoOutputSlot(ItemStack output, int slot) {
        return inventory.getStackInSlot(slot).isEmpty() ||
                inventory.getStackInSlot(slot).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count, int slot) {
        int maxCount = inventory.getStackInSlot(slot).isEmpty() ? 64 : inventory.getStackInSlot(slot).getMaxStackSize();
        int currentCount = inventory.getStackInSlot(slot).getCount();

        return maxCount >= currentCount + count;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}