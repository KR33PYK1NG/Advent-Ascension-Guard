package rmc.mixins.advent_ascension_guard.inject;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.util.math.EntityRayTraceResult;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import rmc.libs.event_factory.EventFactory;

/**
 * Developed by RMC Team, 2021
 * @author KR33PY
 */
@Mixin(value = BaseBullet.class)
public abstract class BaseBulletMixin {

    @Inject(method = "Lnet/tslat/aoa3/entity/projectile/gun/BaseBullet;onHitEntity(Lnet/minecraft/util/math/EntityRayTraceResult;)V",
            cancellable = true,
            at = @At(value = "HEAD"))
    private void guardShotImpact(EntityRayTraceResult rayTrace, CallbackInfo mixin) {
        if (!EventFactory.testEntityInteract(EventFactory.convert(((BaseBullet)(Object) this).getOwner()), ((BaseBullet)(Object) this).level, rayTrace.getEntity())) {
            mixin.cancel();
        }
    }

}