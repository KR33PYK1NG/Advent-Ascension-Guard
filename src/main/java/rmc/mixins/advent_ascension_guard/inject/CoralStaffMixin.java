package rmc.mixins.advent_ascension_guard.inject;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.item.weapon.staff.CoralStaff;
import rmc.libs.event_factory.EventFactory;

/**
 * Developed by RMC Team, 2021
 * @author KR33PY
 */
@Mixin(value = CoralStaff.class)
public abstract class CoralStaffMixin {

    @Inject(method = "Lnet/tslat/aoa3/item/weapon/staff/CoralStaff;checkPreconditions(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;)Ljava/util/ArrayList;",
            remap = false,
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD,
            at = @At(value = "TAIL"))
    private void guardCoralPlacement(LivingEntity caster, ItemStack staff, CallbackInfoReturnable<ArrayList<BlockPos>> mixin, ArrayList<BlockPos> coralPositions) {
        for (BlockPos pos : coralPositions) {
            if (!EventFactory.testBlockBreak(EventFactory.convert(caster), caster.level, pos)) {
                mixin.setReturnValue(null);
                break;
            }
        }
    }

}