package me.blackfur.arsnouveautweak.mixin;

import com.hollingsworth.arsnouveau.common.entity.AmethystGolem;
import com.hollingsworth.arsnouveau.common.entity.goal.amethyst_golem.PickupAmethystGoal;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PickupAmethystGoal.class)
public class PickupAmethystGoalMixin {

    @Shadow
    public AmethystGolem golem;
    @Redirect(
            method = "collectStacks",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/tags/TagKey;)Z"
            ),
            remap = false
    )
    public boolean addCheck(ItemStack i, TagKey<Item> tagKey) {
        return (golem.getHeldStack().isEmpty() || i.getItem() == golem.getHeldStack().getItem()) && i.is(tagKey);
    }
}
