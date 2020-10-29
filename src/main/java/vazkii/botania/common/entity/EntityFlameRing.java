/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [Jun 26, 2014, 12:31:10 AM (GMT)]
 */
package vazkii.botania.common.entity;

import com.gamerforea.botania.ModUtils;
import com.gamerforea.eventhelper.fake.FakePlayerContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.MathHelper;

import javax.annotation.Nonnull;

import elucent.albedo.lighting.ILightProvider;
import elucent.albedo.lighting.Light;

import java.util.List;
import java.util.Random;

public class EntityFlameRing extends Entity {

	// TODO gamerforEA code start
	public final FakePlayerContainer fake = ModUtils.NEXUS_FACTORY.wrapFake(this);
	// TODO gamerforEA code end

	public EntityFlameRing(World world) {
		super(world);
	}

	@Override
	protected void entityInit() {
		setSize(0F, 0F);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();

		float radius = 5F;
		// TODO gamerforEA code start
		if (this.world.isRemote)
		{
			Random random = this.world.rand;
			float renderRadius = radius - random.nextFloat();

			for (int i = 0; i < Math.min(90, this.ticksExisted); i++)
			{
				if (random.nextInt(this.ticksExisted < 90 ? 8 : 20) == 0)
				{
					float a = i;
					if (a % 2 == 0)
						a = 45 + a;

					float rad = (float) (a * 4 * Math.PI / 180F);
					float radCos = net.minecraft.util.math.MathHelper.cos(rad);
					float radSin = net.minecraft.util.math.MathHelper.sin(rad);

					double x = radCos * renderRadius;
					double z = radSin * renderRadius;

					Botania.proxy.wispFX(this.posX + x, this.posY - 0.2, this.posZ + z, 1F, random.nextFloat() * 0.25F, random.nextFloat() * 0.25F, 0.65F + random.nextFloat() * 0.45F, (random.nextFloat() - 0.5F) * 0.15F, 0.055F + random.nextFloat() * 0.025F, (random.nextFloat() - 0.5F) * 0.15F);

					float gs = random.nextFloat() * 0.15F;
					float smokeRadius = renderRadius - random.nextFloat() * renderRadius * 0.9F;
					x = radCos * smokeRadius;
					z = radSin * smokeRadius;
					Botania.proxy.wispFX(this.posX + x, this.posY - 0.2, this.posZ + z, gs, gs, gs, 0.65F + random.nextFloat() * 0.45F, -0.155F - random.nextFloat() * 0.025F);
				}
			}

			if (random.nextInt(20) == 0)
				this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1F, 1F, false);
		}
		// TODO gamerforEA code end

		if(world.isRemote)
			return;

		if(ticksExisted >= 300) {
			setDead();
			return;
		}

		if(ticksExisted > 45) {
			AxisAlignedBB boundingBox = new AxisAlignedBB(posX, posY, posZ, posX, posY, posZ).grow(radius, radius, radius);
			List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, boundingBox);

			if(entities.isEmpty())
				return;

			for(EntityLivingBase entity : entities) {
				if(entity == null || MathHelper.pointDistancePlane(posX, posY, entity.posX, entity.posY) > radius)
					continue;

				// TODO gamerforEA code start
				if (this.fake.cantAttack(entity))
					continue;
				// TODO gamerforEA code end

				entity.setFire(4);
			}
		}
	}

	@Override
	public boolean attackEntityFrom(@Nonnull DamageSource par1DamageSource, float par2) {
		return false;
	}

	@Override
	protected void readEntityFromNBT(@Nonnull NBTTagCompound var1) {
		// TODO gamerforEA code start
		this.fake.readFromNBT(var1);
		// TODO gamerforEA code end
	}

	@Override
	protected void writeEntityToNBT(@Nonnull NBTTagCompound var1) {
		// TODO gamerforEA code start
		this.fake.writeToNBT(var1);
		// TODO gamerforEA code end
	}
}