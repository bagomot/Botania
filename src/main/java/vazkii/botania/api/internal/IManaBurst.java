/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [Jan 31, 2014, 4:36:13 PM (GMT)]
 */
package vazkii.botania.api.internal;

import com.gamerforea.botania.ModUtils;
import com.gamerforea.eventhelper.fake.FakePlayerContainer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Interface for the Mana Burst entity. This can safely be casted to EntityThrowable.
 */
public interface IManaBurst {

	boolean isFake();

	void setMotion(double x, double y, double z);

	int getColor();

	void setColor(int color);

	int getMana();

	void setMana(int mana);

	int getStartingMana();

	void setStartingMana(int mana);

	int getMinManaLoss();

	void setMinManaLoss(int minManaLoss);

	float getManaLossPerTick();

	void setManaLossPerTick(float mana);

	float getGravity();

	void setGravity(float gravity);

	BlockPos getBurstSourceBlockPos();

	void setBurstSourceCoords(BlockPos pos);

	ItemStack getSourceLens();

	void setSourceLens(ItemStack lens);

	boolean hasAlreadyCollidedAt(BlockPos pos);

	void setCollidedAt(BlockPos pos);

	int getTicksExisted();

	void setFake(boolean fake);

	void setShooterUUID(UUID uuid);

	UUID getShooterUUID();

	void ping();

	// TODO gamerforEA code start
	@Nullable
	default FakePlayerContainer getFakePlayerContainer()
	{
		if (this instanceof Entity)
			return ModUtils.NEXUS_FACTORY.wrapFake((Entity) this);
		if (this instanceof TileEntity)
			return ModUtils.NEXUS_FACTORY.wrapFake((TileEntity) this);
		if (this instanceof World)
			return ModUtils.NEXUS_FACTORY.wrapFake((World) this);
		return null;
	}
	// TODO gamerforEA code end

}
