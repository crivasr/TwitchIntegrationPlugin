package tk.camikase.TwitchIntegration.utils;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.FluidCollisionMode;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Note;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.entity.Villager;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class FakePlayer implements Player {

    private UUID uuid = null;

    public FakePlayer(UUID u) {
        uuid = u;
    }

    public void setUUID(UUID u) {
        uuid = u;
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    @Override
    public PlayerInventory getInventory() {
        throw new UnsupportedOperationException("Unimplemented method 'getInventory'");
    }

    @Override
    public Inventory getEnderChest() {
        throw new UnsupportedOperationException("Unimplemented method 'getEnderChest'");
    }

    @Override
    public MainHand getMainHand() {
        throw new UnsupportedOperationException("Unimplemented method 'getMainHand'");
    }

    @Override
    public boolean setWindowProperty(Property prop, int value) {
        throw new UnsupportedOperationException("Unimplemented method 'setWindowProperty'");
    }

    @Override
    public int getEnchantmentSeed() {
        throw new UnsupportedOperationException("Unimplemented method 'getEnchantmentSeed'");
    }

    @Override
    public void setEnchantmentSeed(int seed) {
        throw new UnsupportedOperationException("Unimplemented method 'setEnchantmentSeed'");
    }

    @Override
    public InventoryView getOpenInventory() {
        throw new UnsupportedOperationException("Unimplemented method 'getOpenInventory'");
    }

    @Override
    public InventoryView openInventory(Inventory inventory) {
        throw new UnsupportedOperationException("Unimplemented method 'openInventory'");
    }

    @Override
    public InventoryView openWorkbench(Location location, boolean force) {
        throw new UnsupportedOperationException("Unimplemented method 'openWorkbench'");
    }

    @Override
    public InventoryView openEnchanting(Location location, boolean force) {
        throw new UnsupportedOperationException("Unimplemented method 'openEnchanting'");
    }

    @Override
    public void openInventory(InventoryView inventory) {
        throw new UnsupportedOperationException("Unimplemented method 'openInventory'");
    }

    @Override
    public InventoryView openMerchant(Villager trader, boolean force) {
        throw new UnsupportedOperationException("Unimplemented method 'openMerchant'");
    }

    @Override
    public InventoryView openMerchant(Merchant merchant, boolean force) {
        throw new UnsupportedOperationException("Unimplemented method 'openMerchant'");
    }

    @Override
    public void closeInventory() {
        throw new UnsupportedOperationException("Unimplemented method 'closeInventory'");
    }

    @Override
    public ItemStack getItemInHand() {
        throw new UnsupportedOperationException("Unimplemented method 'getItemInHand'");
    }

    @Override
    public void setItemInHand(ItemStack item) {
        throw new UnsupportedOperationException("Unimplemented method 'setItemInHand'");
    }

    @Override
    public ItemStack getItemOnCursor() {
        throw new UnsupportedOperationException("Unimplemented method 'getItemOnCursor'");
    }

    @Override
    public void setItemOnCursor(ItemStack item) {
        throw new UnsupportedOperationException("Unimplemented method 'setItemOnCursor'");
    }

    @Override
    public boolean hasCooldown(Material material) {
        throw new UnsupportedOperationException("Unimplemented method 'hasCooldown'");
    }

    @Override
    public int getCooldown(Material material) {
        throw new UnsupportedOperationException("Unimplemented method 'getCooldown'");
    }

    @Override
    public void setCooldown(Material material, int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'setCooldown'");
    }

    @Override
    public int getSleepTicks() {
        throw new UnsupportedOperationException("Unimplemented method 'getSleepTicks'");
    }

    @Override
    public boolean sleep(Location location, boolean force) {
        throw new UnsupportedOperationException("Unimplemented method 'sleep'");
    }

    @Override
    public void wakeup(boolean setSpawnLocation) {
        throw new UnsupportedOperationException("Unimplemented method 'wakeup'");
    }

    @Override
    public Location getBedLocation() {
        throw new UnsupportedOperationException("Unimplemented method 'getBedLocation'");
    }

    @Override
    public GameMode getGameMode() {
        throw new UnsupportedOperationException("Unimplemented method 'getGameMode'");
    }

    @Override
    public void setGameMode(GameMode mode) {
        throw new UnsupportedOperationException("Unimplemented method 'setGameMode'");
    }

    @Override
    public boolean isBlocking() {
        throw new UnsupportedOperationException("Unimplemented method 'isBlocking'");
    }

    @Override
    public boolean isHandRaised() {
        throw new UnsupportedOperationException("Unimplemented method 'isHandRaised'");
    }

    @Override
    public ItemStack getItemInUse() {
        throw new UnsupportedOperationException("Unimplemented method 'getItemInUse'");
    }

    @Override
    public int getExpToLevel() {
        throw new UnsupportedOperationException("Unimplemented method 'getExpToLevel'");
    }

    @Override
    public float getAttackCooldown() {
        throw new UnsupportedOperationException("Unimplemented method 'getAttackCooldown'");
    }

    @Override
    public boolean discoverRecipe(NamespacedKey recipe) {
        throw new UnsupportedOperationException("Unimplemented method 'discoverRecipe'");
    }

    @Override
    public int discoverRecipes(Collection<NamespacedKey> recipes) {
        throw new UnsupportedOperationException("Unimplemented method 'discoverRecipes'");
    }

    @Override
    public boolean undiscoverRecipe(NamespacedKey recipe) {
        throw new UnsupportedOperationException("Unimplemented method 'undiscoverRecipe'");
    }

    @Override
    public int undiscoverRecipes(Collection<NamespacedKey> recipes) {
        throw new UnsupportedOperationException("Unimplemented method 'undiscoverRecipes'");
    }

    @Override
    public boolean hasDiscoveredRecipe(NamespacedKey recipe) {
        throw new UnsupportedOperationException("Unimplemented method 'hasDiscoveredRecipe'");
    }

    @Override
    public Set<NamespacedKey> getDiscoveredRecipes() {
        throw new UnsupportedOperationException("Unimplemented method 'getDiscoveredRecipes'");
    }

    @Override
    public Entity getShoulderEntityLeft() {
        throw new UnsupportedOperationException("Unimplemented method 'getShoulderEntityLeft'");
    }

    @Override
    public void setShoulderEntityLeft(Entity entity) {
        throw new UnsupportedOperationException("Unimplemented method 'setShoulderEntityLeft'");
    }

    @Override
    public Entity getShoulderEntityRight() {
        throw new UnsupportedOperationException("Unimplemented method 'getShoulderEntityRight'");
    }

    @Override
    public void setShoulderEntityRight(Entity entity) {
        throw new UnsupportedOperationException("Unimplemented method 'setShoulderEntityRight'");
    }

    @Override
    public boolean dropItem(boolean dropAll) {
        throw new UnsupportedOperationException("Unimplemented method 'dropItem'");
    }

    @Override
    public float getExhaustion() {
        throw new UnsupportedOperationException("Unimplemented method 'getExhaustion'");
    }

    @Override
    public void setExhaustion(float value) {
        throw new UnsupportedOperationException("Unimplemented method 'setExhaustion'");
    }

    @Override
    public float getSaturation() {
        throw new UnsupportedOperationException("Unimplemented method 'getSaturation'");
    }

    @Override
    public void setSaturation(float value) {
        throw new UnsupportedOperationException("Unimplemented method 'setSaturation'");
    }

    @Override
    public int getFoodLevel() {
        throw new UnsupportedOperationException("Unimplemented method 'getFoodLevel'");
    }

    @Override
    public void setFoodLevel(int value) {
        throw new UnsupportedOperationException("Unimplemented method 'setFoodLevel'");
    }

    @Override
    public int getSaturatedRegenRate() {
        throw new UnsupportedOperationException("Unimplemented method 'getSaturatedRegenRate'");
    }

    @Override
    public void setSaturatedRegenRate(int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'setSaturatedRegenRate'");
    }

    @Override
    public int getUnsaturatedRegenRate() {
        throw new UnsupportedOperationException("Unimplemented method 'getUnsaturatedRegenRate'");
    }

    @Override
    public void setUnsaturatedRegenRate(int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'setUnsaturatedRegenRate'");
    }

    @Override
    public int getStarvationRate() {
        throw new UnsupportedOperationException("Unimplemented method 'getStarvationRate'");
    }

    @Override
    public void setStarvationRate(int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'setStarvationRate'");
    }

    @Override
    public Location getLastDeathLocation() {
        throw new UnsupportedOperationException("Unimplemented method 'getLastDeathLocation'");
    }

    @Override
    public void setLastDeathLocation(Location location) {
        throw new UnsupportedOperationException("Unimplemented method 'setLastDeathLocation'");
    }

    @Override
    public Firework fireworkBoost(ItemStack fireworkItemStack) {
        throw new UnsupportedOperationException("Unimplemented method 'fireworkBoost'");
    }

    @Override
    public double getEyeHeight() {
        throw new UnsupportedOperationException("Unimplemented method 'getEyeHeight'");
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        throw new UnsupportedOperationException("Unimplemented method 'getEyeHeight'");
    }

    @Override
    public Location getEyeLocation() {
        throw new UnsupportedOperationException("Unimplemented method 'getEyeLocation'");
    }

    @Override
    public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
        throw new UnsupportedOperationException("Unimplemented method 'getLineOfSight'");
    }

    @Override
    public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
        throw new UnsupportedOperationException("Unimplemented method 'getTargetBlock'");
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
        throw new UnsupportedOperationException("Unimplemented method 'getLastTwoTargetBlocks'");
    }

    @Override
    public Block getTargetBlockExact(int maxDistance) {
        throw new UnsupportedOperationException("Unimplemented method 'getTargetBlockExact'");
    }

    @Override
    public Block getTargetBlockExact(int maxDistance, FluidCollisionMode fluidCollisionMode) {
        throw new UnsupportedOperationException("Unimplemented method 'getTargetBlockExact'");
    }

    @Override
    public RayTraceResult rayTraceBlocks(double maxDistance) {
        throw new UnsupportedOperationException("Unimplemented method 'rayTraceBlocks'");
    }

    @Override
    public RayTraceResult rayTraceBlocks(double maxDistance, FluidCollisionMode fluidCollisionMode) {
        throw new UnsupportedOperationException("Unimplemented method 'rayTraceBlocks'");
    }

    @Override
    public int getRemainingAir() {
        throw new UnsupportedOperationException("Unimplemented method 'getRemainingAir'");
    }

    @Override
    public void setRemainingAir(int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'setRemainingAir'");
    }

    @Override
    public int getMaximumAir() {
        throw new UnsupportedOperationException("Unimplemented method 'getMaximumAir'");
    }

    @Override
    public void setMaximumAir(int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'setMaximumAir'");
    }

    @Override
    public int getArrowCooldown() {
        throw new UnsupportedOperationException("Unimplemented method 'getArrowCooldown'");
    }

    @Override
    public void setArrowCooldown(int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'setArrowCooldown'");
    }

    @Override
    public int getArrowsInBody() {
        throw new UnsupportedOperationException("Unimplemented method 'getArrowsInBody'");
    }

    @Override
    public void setArrowsInBody(int count) {
        throw new UnsupportedOperationException("Unimplemented method 'setArrowsInBody'");
    }

    @Override
    public int getMaximumNoDamageTicks() {
        throw new UnsupportedOperationException("Unimplemented method 'getMaximumNoDamageTicks'");
    }

    @Override
    public void setMaximumNoDamageTicks(int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'setMaximumNoDamageTicks'");
    }

    @Override
    public double getLastDamage() {
        throw new UnsupportedOperationException("Unimplemented method 'getLastDamage'");
    }

    @Override
    public void setLastDamage(double damage) {
        throw new UnsupportedOperationException("Unimplemented method 'setLastDamage'");
    }

    @Override
    public int getNoDamageTicks() {
        throw new UnsupportedOperationException("Unimplemented method 'getNoDamageTicks'");
    }

    @Override
    public void setNoDamageTicks(int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'setNoDamageTicks'");
    }

    @Override
    public Player getKiller() {
        throw new UnsupportedOperationException("Unimplemented method 'getKiller'");
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect) {
        throw new UnsupportedOperationException("Unimplemented method 'addPotionEffect'");
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect, boolean force) {
        throw new UnsupportedOperationException("Unimplemented method 'addPotionEffect'");
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> effects) {
        throw new UnsupportedOperationException("Unimplemented method 'addPotionEffects'");
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType type) {
        throw new UnsupportedOperationException("Unimplemented method 'hasPotionEffect'");
    }

    @Override
    public PotionEffect getPotionEffect(PotionEffectType type) {
        throw new UnsupportedOperationException("Unimplemented method 'getPotionEffect'");
    }

    @Override
    public void removePotionEffect(PotionEffectType type) {
        throw new UnsupportedOperationException("Unimplemented method 'removePotionEffect'");
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        throw new UnsupportedOperationException("Unimplemented method 'getActivePotionEffects'");
    }

    @Override
    public boolean hasLineOfSight(Entity other) {
        throw new UnsupportedOperationException("Unimplemented method 'hasLineOfSight'");
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        throw new UnsupportedOperationException("Unimplemented method 'getRemoveWhenFarAway'");
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {
        throw new UnsupportedOperationException("Unimplemented method 'setRemoveWhenFarAway'");
    }

    @Override
    public EntityEquipment getEquipment() {
        throw new UnsupportedOperationException("Unimplemented method 'getEquipment'");
    }

    @Override
    public void setCanPickupItems(boolean pickup) {
        throw new UnsupportedOperationException("Unimplemented method 'setCanPickupItems'");
    }

    @Override
    public boolean getCanPickupItems() {
        throw new UnsupportedOperationException("Unimplemented method 'getCanPickupItems'");
    }

    @Override
    public boolean isLeashed() {
        throw new UnsupportedOperationException("Unimplemented method 'isLeashed'");
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        throw new UnsupportedOperationException("Unimplemented method 'getLeashHolder'");
    }

    @Override
    public boolean setLeashHolder(Entity holder) {
        throw new UnsupportedOperationException("Unimplemented method 'setLeashHolder'");
    }

    @Override
    public boolean isGliding() {
        throw new UnsupportedOperationException("Unimplemented method 'isGliding'");
    }

    @Override
    public void setGliding(boolean gliding) {
        throw new UnsupportedOperationException("Unimplemented method 'setGliding'");
    }

    @Override
    public boolean isSwimming() {
        throw new UnsupportedOperationException("Unimplemented method 'isSwimming'");
    }

    @Override
    public void setSwimming(boolean swimming) {
        throw new UnsupportedOperationException("Unimplemented method 'setSwimming'");
    }

    @Override
    public boolean isRiptiding() {
        throw new UnsupportedOperationException("Unimplemented method 'isRiptiding'");
    }

    @Override
    public boolean isSleeping() {
        throw new UnsupportedOperationException("Unimplemented method 'isSleeping'");
    }

    @Override
    public boolean isClimbing() {
        throw new UnsupportedOperationException("Unimplemented method 'isClimbing'");
    }

    @Override
    public void setAI(boolean ai) {
        throw new UnsupportedOperationException("Unimplemented method 'setAI'");
    }

    @Override
    public boolean hasAI() {
        throw new UnsupportedOperationException("Unimplemented method 'hasAI'");
    }

    @Override
    public void attack(Entity target) {
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void swingMainHand() {
        throw new UnsupportedOperationException("Unimplemented method 'swingMainHand'");
    }

    @Override
    public void swingOffHand() {
        throw new UnsupportedOperationException("Unimplemented method 'swingOffHand'");
    }

    @Override
    public void setCollidable(boolean collidable) {
        throw new UnsupportedOperationException("Unimplemented method 'setCollidable'");
    }

    @Override
    public boolean isCollidable() {
        throw new UnsupportedOperationException("Unimplemented method 'isCollidable'");
    }

    @Override
    public Set<UUID> getCollidableExemptions() {
        throw new UnsupportedOperationException("Unimplemented method 'getCollidableExemptions'");
    }

    @Override
    public <T> T getMemory(MemoryKey<T> memoryKey) {
        throw new UnsupportedOperationException("Unimplemented method 'getMemory'");
    }

    @Override
    public <T> void setMemory(MemoryKey<T> memoryKey, T memoryValue) {
        throw new UnsupportedOperationException("Unimplemented method 'setMemory'");
    }

    @Override
    public Sound getHurtSound() {
        throw new UnsupportedOperationException("Unimplemented method 'getHurtSound'");
    }

    @Override
    public Sound getDeathSound() {
        throw new UnsupportedOperationException("Unimplemented method 'getDeathSound'");
    }

    @Override
    public Sound getFallDamageSound(int fallHeight) {
        throw new UnsupportedOperationException("Unimplemented method 'getFallDamageSound'");
    }

    @Override
    public Sound getFallDamageSoundSmall() {
        throw new UnsupportedOperationException("Unimplemented method 'getFallDamageSoundSmall'");
    }

    @Override
    public Sound getFallDamageSoundBig() {
        throw new UnsupportedOperationException("Unimplemented method 'getFallDamageSoundBig'");
    }

    @Override
    public Sound getDrinkingSound(ItemStack itemStack) {
        throw new UnsupportedOperationException("Unimplemented method 'getDrinkingSound'");
    }

    @Override
    public Sound getEatingSound(ItemStack itemStack) {
        throw new UnsupportedOperationException("Unimplemented method 'getEatingSound'");
    }

    @Override
    public boolean canBreatheUnderwater() {
        throw new UnsupportedOperationException("Unimplemented method 'canBreatheUnderwater'");
    }

    @Override
    public EntityCategory getCategory() {
        throw new UnsupportedOperationException("Unimplemented method 'getCategory'");
    }

    @Override
    public void setInvisible(boolean invisible) {
        throw new UnsupportedOperationException("Unimplemented method 'setInvisible'");
    }

    @Override
    public boolean isInvisible() {
        throw new UnsupportedOperationException("Unimplemented method 'isInvisible'");
    }

    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        throw new UnsupportedOperationException("Unimplemented method 'getAttribute'");
    }

    @Override
    public void damage(double amount) {
        throw new UnsupportedOperationException("Unimplemented method 'damage'");
    }

    @Override
    public void damage(double amount, Entity source) {
        throw new UnsupportedOperationException("Unimplemented method 'damage'");
    }

    @Override
    public double getHealth() {
        throw new UnsupportedOperationException("Unimplemented method 'getHealth'");
    }

    @Override
    public void setHealth(double health) {
        throw new UnsupportedOperationException("Unimplemented method 'setHealth'");
    }

    @Override
    public double getAbsorptionAmount() {
        throw new UnsupportedOperationException("Unimplemented method 'getAbsorptionAmount'");
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        throw new UnsupportedOperationException("Unimplemented method 'setAbsorptionAmount'");
    }

    @Override
    public double getMaxHealth() {
        throw new UnsupportedOperationException("Unimplemented method 'getMaxHealth'");
    }

    @Override
    public void setMaxHealth(double health) {
        throw new UnsupportedOperationException("Unimplemented method 'setMaxHealth'");
    }

    @Override
    public void resetMaxHealth() {
        throw new UnsupportedOperationException("Unimplemented method 'resetMaxHealth'");
    }

    @Override
    public Location getLocation() {
        throw new UnsupportedOperationException("Unimplemented method 'getLocation'");
    }

    @Override
    public Location getLocation(Location loc) {
        throw new UnsupportedOperationException("Unimplemented method 'getLocation'");
    }

    @Override
    public void setVelocity(Vector velocity) {
        throw new UnsupportedOperationException("Unimplemented method 'setVelocity'");
    }

    @Override
    public Vector getVelocity() {
        throw new UnsupportedOperationException("Unimplemented method 'getVelocity'");
    }

    @Override
    public double getHeight() {
        throw new UnsupportedOperationException("Unimplemented method 'getHeight'");
    }

    @Override
    public double getWidth() {
        throw new UnsupportedOperationException("Unimplemented method 'getWidth'");
    }

    @Override
    public BoundingBox getBoundingBox() {
        throw new UnsupportedOperationException("Unimplemented method 'getBoundingBox'");
    }

    @Override
    public boolean isInWater() {
        throw new UnsupportedOperationException("Unimplemented method 'isInWater'");
    }

    @Override
    public World getWorld() {
        throw new UnsupportedOperationException("Unimplemented method 'getWorld'");
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        throw new UnsupportedOperationException("Unimplemented method 'setRotation'");
    }

    @Override
    public boolean teleport(Location location) {
        throw new UnsupportedOperationException("Unimplemented method 'teleport'");
    }

    @Override
    public boolean teleport(Location location, TeleportCause cause) {
        throw new UnsupportedOperationException("Unimplemented method 'teleport'");
    }

    @Override
    public boolean teleport(Entity destination) {
        throw new UnsupportedOperationException("Unimplemented method 'teleport'");
    }

    @Override
    public boolean teleport(Entity destination, TeleportCause cause) {
        throw new UnsupportedOperationException("Unimplemented method 'teleport'");
    }

    @Override
    public List<Entity> getNearbyEntities(double x, double y, double z) {
        throw new UnsupportedOperationException("Unimplemented method 'getNearbyEntities'");
    }

    @Override
    public int getEntityId() {
        throw new UnsupportedOperationException("Unimplemented method 'getEntityId'");
    }

    @Override
    public int getFireTicks() {
        throw new UnsupportedOperationException("Unimplemented method 'getFireTicks'");
    }

    @Override
    public int getMaxFireTicks() {
        throw new UnsupportedOperationException("Unimplemented method 'getMaxFireTicks'");
    }

    @Override
    public void setFireTicks(int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'setFireTicks'");
    }

    @Override
    public void setVisualFire(boolean fire) {
        throw new UnsupportedOperationException("Unimplemented method 'setVisualFire'");
    }

    @Override
    public boolean isVisualFire() {
        throw new UnsupportedOperationException("Unimplemented method 'isVisualFire'");
    }

    @Override
    public int getFreezeTicks() {
        throw new UnsupportedOperationException("Unimplemented method 'getFreezeTicks'");
    }

    @Override
    public int getMaxFreezeTicks() {
        throw new UnsupportedOperationException("Unimplemented method 'getMaxFreezeTicks'");
    }

    @Override
    public void setFreezeTicks(int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'setFreezeTicks'");
    }

    @Override
    public boolean isFrozen() {
        throw new UnsupportedOperationException("Unimplemented method 'isFrozen'");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean isDead() {
        throw new UnsupportedOperationException("Unimplemented method 'isDead'");
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Unimplemented method 'isValid'");
    }

    @Override
    public Server getServer() {
        throw new UnsupportedOperationException("Unimplemented method 'getServer'");
    }

    @Override
    public boolean isPersistent() {
        throw new UnsupportedOperationException("Unimplemented method 'isPersistent'");
    }

    @Override
    public void setPersistent(boolean persistent) {
        throw new UnsupportedOperationException("Unimplemented method 'setPersistent'");
    }

    @Override
    public Entity getPassenger() {
        throw new UnsupportedOperationException("Unimplemented method 'getPassenger'");
    }

    @Override
    public boolean setPassenger(Entity passenger) {
        throw new UnsupportedOperationException("Unimplemented method 'setPassenger'");
    }

    @Override
    public List<Entity> getPassengers() {
        throw new UnsupportedOperationException("Unimplemented method 'getPassengers'");
    }

    @Override
    public boolean addPassenger(Entity passenger) {
        throw new UnsupportedOperationException("Unimplemented method 'addPassenger'");
    }

    @Override
    public boolean removePassenger(Entity passenger) {
        throw new UnsupportedOperationException("Unimplemented method 'removePassenger'");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public boolean eject() {
        throw new UnsupportedOperationException("Unimplemented method 'eject'");
    }

    @Override
    public float getFallDistance() {
        throw new UnsupportedOperationException("Unimplemented method 'getFallDistance'");
    }

    @Override
    public void setFallDistance(float distance) {
        throw new UnsupportedOperationException("Unimplemented method 'setFallDistance'");
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent event) {
        throw new UnsupportedOperationException("Unimplemented method 'setLastDamageCause'");
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        throw new UnsupportedOperationException("Unimplemented method 'getLastDamageCause'");
    }

    @Override
    public int getTicksLived() {
        throw new UnsupportedOperationException("Unimplemented method 'getTicksLived'");
    }

    @Override
    public void setTicksLived(int value) {
        throw new UnsupportedOperationException("Unimplemented method 'setTicksLived'");
    }

    @Override
    public void playEffect(EntityEffect type) {
        throw new UnsupportedOperationException("Unimplemented method 'playEffect'");
    }

    @Override
    public EntityType getType() {
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

    @Override
    public Sound getSwimSound() {
        throw new UnsupportedOperationException("Unimplemented method 'getSwimSound'");
    }

    @Override
    public Sound getSwimSplashSound() {
        throw new UnsupportedOperationException("Unimplemented method 'getSwimSplashSound'");
    }

    @Override
    public Sound getSwimHighSpeedSplashSound() {
        throw new UnsupportedOperationException("Unimplemented method 'getSwimHighSpeedSplashSound'");
    }

    @Override
    public boolean isInsideVehicle() {
        throw new UnsupportedOperationException("Unimplemented method 'isInsideVehicle'");
    }

    @Override
    public boolean leaveVehicle() {
        throw new UnsupportedOperationException("Unimplemented method 'leaveVehicle'");
    }

    @Override
    public Entity getVehicle() {
        throw new UnsupportedOperationException("Unimplemented method 'getVehicle'");
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        throw new UnsupportedOperationException("Unimplemented method 'setCustomNameVisible'");
    }

    @Override
    public boolean isCustomNameVisible() {
        throw new UnsupportedOperationException("Unimplemented method 'isCustomNameVisible'");
    }

    @Override
    public void setGlowing(boolean flag) {
        throw new UnsupportedOperationException("Unimplemented method 'setGlowing'");
    }

    @Override
    public boolean isGlowing() {
        throw new UnsupportedOperationException("Unimplemented method 'isGlowing'");
    }

    @Override
    public void setInvulnerable(boolean flag) {
        throw new UnsupportedOperationException("Unimplemented method 'setInvulnerable'");
    }

    @Override
    public boolean isInvulnerable() {
        throw new UnsupportedOperationException("Unimplemented method 'isInvulnerable'");
    }

    @Override
    public boolean isSilent() {
        throw new UnsupportedOperationException("Unimplemented method 'isSilent'");
    }

    @Override
    public void setSilent(boolean flag) {
        throw new UnsupportedOperationException("Unimplemented method 'setSilent'");
    }

    @Override
    public boolean hasGravity() {
        throw new UnsupportedOperationException("Unimplemented method 'hasGravity'");
    }

    @Override
    public void setGravity(boolean gravity) {
        throw new UnsupportedOperationException("Unimplemented method 'setGravity'");
    }

    @Override
    public int getPortalCooldown() {
        throw new UnsupportedOperationException("Unimplemented method 'getPortalCooldown'");
    }

    @Override
    public void setPortalCooldown(int cooldown) {
        throw new UnsupportedOperationException("Unimplemented method 'setPortalCooldown'");
    }

    @Override
    public Set<String> getScoreboardTags() {
        throw new UnsupportedOperationException("Unimplemented method 'getScoreboardTags'");
    }

    @Override
    public boolean addScoreboardTag(String tag) {
        throw new UnsupportedOperationException("Unimplemented method 'addScoreboardTag'");
    }

    @Override
    public boolean removeScoreboardTag(String tag) {
        throw new UnsupportedOperationException("Unimplemented method 'removeScoreboardTag'");
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        throw new UnsupportedOperationException("Unimplemented method 'getPistonMoveReaction'");
    }

    @Override
    public BlockFace getFacing() {
        throw new UnsupportedOperationException("Unimplemented method 'getFacing'");
    }

    @Override
    public Pose getPose() {
        throw new UnsupportedOperationException("Unimplemented method 'getPose'");
    }

    @Override
    public SpawnCategory getSpawnCategory() {
        throw new UnsupportedOperationException("Unimplemented method 'getSpawnCategory'");
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        throw new UnsupportedOperationException("Unimplemented method 'setMetadata'");
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        throw new UnsupportedOperationException("Unimplemented method 'getMetadata'");
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        throw new UnsupportedOperationException("Unimplemented method 'hasMetadata'");
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        throw new UnsupportedOperationException("Unimplemented method 'removeMetadata'");
    }

    @Override
    public void sendMessage(String message) {
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }

    @Override
    public void sendMessage(String... messages) {
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }

    @Override
    public void sendMessage(UUID sender, String message) {
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }

    @Override
    public void sendMessage(UUID sender, String... messages) {
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }

    @Override
    public boolean isPermissionSet(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'isPermissionSet'");
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        throw new UnsupportedOperationException("Unimplemented method 'isPermissionSet'");
    }

    @Override
    public boolean hasPermission(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'hasPermission'");
    }

    @Override
    public boolean hasPermission(Permission perm) {
        throw new UnsupportedOperationException("Unimplemented method 'hasPermission'");
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        throw new UnsupportedOperationException("Unimplemented method 'addAttachment'");
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        throw new UnsupportedOperationException("Unimplemented method 'addAttachment'");
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'addAttachment'");
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        throw new UnsupportedOperationException("Unimplemented method 'addAttachment'");
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAttachment'");
    }

    @Override
    public void recalculatePermissions() {
        throw new UnsupportedOperationException("Unimplemented method 'recalculatePermissions'");
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        throw new UnsupportedOperationException("Unimplemented method 'getEffectivePermissions'");
    }

    @Override
    public boolean isOp() {
        throw new UnsupportedOperationException("Unimplemented method 'isOp'");
    }

    @Override
    public void setOp(boolean value) {
        throw new UnsupportedOperationException("Unimplemented method 'setOp'");
    }

    @Override
    public String getCustomName() {
        throw new UnsupportedOperationException("Unimplemented method 'getCustomName'");
    }

    @Override
    public void setCustomName(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'setCustomName'");
    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        throw new UnsupportedOperationException("Unimplemented method 'getPersistentDataContainer'");
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
        throw new UnsupportedOperationException("Unimplemented method 'launchProjectile'");
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
        throw new UnsupportedOperationException("Unimplemented method 'launchProjectile'");
    }

    @Override
    public boolean isConversing() {
        throw new UnsupportedOperationException("Unimplemented method 'isConversing'");
    }

    @Override
    public void acceptConversationInput(String input) {
        throw new UnsupportedOperationException("Unimplemented method 'acceptConversationInput'");
    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        throw new UnsupportedOperationException("Unimplemented method 'beginConversation'");
    }

    @Override
    public void abandonConversation(Conversation conversation) {
        throw new UnsupportedOperationException("Unimplemented method 'abandonConversation'");
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {
        throw new UnsupportedOperationException("Unimplemented method 'abandonConversation'");
    }

    @Override
    public void sendRawMessage(UUID sender, String message) {
        throw new UnsupportedOperationException("Unimplemented method 'sendRawMessage'");
    }

    @Override
    public boolean isOnline() {
        throw new UnsupportedOperationException("Unimplemented method 'isOnline'");
    }

    @Override
    public PlayerProfile getPlayerProfile() {
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerProfile'");
    }

    @Override
    public boolean isBanned() {
        throw new UnsupportedOperationException("Unimplemented method 'isBanned'");
    }

    @Override
    public boolean isWhitelisted() {
        throw new UnsupportedOperationException("Unimplemented method 'isWhitelisted'");
    }

    @Override
    public void setWhitelisted(boolean value) {
        throw new UnsupportedOperationException("Unimplemented method 'setWhitelisted'");
    }

    @Override
    public Player getPlayer() {
        throw new UnsupportedOperationException("Unimplemented method 'getPlayer'");
    }

    @Override
    public long getFirstPlayed() {
        throw new UnsupportedOperationException("Unimplemented method 'getFirstPlayed'");
    }

    @Override
    public long getLastPlayed() {
        throw new UnsupportedOperationException("Unimplemented method 'getLastPlayed'");
    }

    @Override
    public boolean hasPlayedBefore() {
        throw new UnsupportedOperationException("Unimplemented method 'hasPlayedBefore'");
    }

    @Override
    public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'incrementStatistic'");
    }

    @Override
    public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'decrementStatistic'");
    }

    @Override
    public void incrementStatistic(Statistic statistic, int amount) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'incrementStatistic'");
    }

    @Override
    public void decrementStatistic(Statistic statistic, int amount) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'decrementStatistic'");
    }

    @Override
    public void setStatistic(Statistic statistic, int newValue) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'setStatistic'");
    }

    @Override
    public int getStatistic(Statistic statistic) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'getStatistic'");
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'incrementStatistic'");
    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'decrementStatistic'");
    }

    @Override
    public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'getStatistic'");
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material, int amount) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'incrementStatistic'");
    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material, int amount) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'decrementStatistic'");
    }

    @Override
    public void setStatistic(Statistic statistic, Material material, int newValue) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'setStatistic'");
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'incrementStatistic'");
    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'decrementStatistic'");
    }

    @Override
    public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'getStatistic'");
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType, int amount)
            throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'incrementStatistic'");
    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType, int amount) {
        throw new UnsupportedOperationException("Unimplemented method 'decrementStatistic'");
    }

    @Override
    public void setStatistic(Statistic statistic, EntityType entityType, int newValue) {
        throw new UnsupportedOperationException("Unimplemented method 'setStatistic'");
    }

    @Override
    public Map<String, Object> serialize() {
        throw new UnsupportedOperationException("Unimplemented method 'serialize'");
    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {
        throw new UnsupportedOperationException("Unimplemented method 'sendPluginMessage'");
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        throw new UnsupportedOperationException("Unimplemented method 'getListeningPluginChannels'");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    @Override
    public String getDisplayName() {
        throw new UnsupportedOperationException("Unimplemented method 'getDisplayName'");
    }

    @Override
    public void setDisplayName(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'setDisplayName'");
    }

    @Override
    public String getPlayerListName() {
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerListName'");
    }

    @Override
    public void setPlayerListName(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'setPlayerListName'");
    }

    @Override
    public String getPlayerListHeader() {
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerListHeader'");
    }

    @Override
    public String getPlayerListFooter() {
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerListFooter'");
    }

    @Override
    public void setPlayerListHeader(String header) {
        throw new UnsupportedOperationException("Unimplemented method 'setPlayerListHeader'");
    }

    @Override
    public void setPlayerListFooter(String footer) {
        throw new UnsupportedOperationException("Unimplemented method 'setPlayerListFooter'");
    }

    @Override
    public void setPlayerListHeaderFooter(String header, String footer) {
        throw new UnsupportedOperationException("Unimplemented method 'setPlayerListHeaderFooter'");
    }

    @Override
    public void setCompassTarget(Location loc) {
        throw new UnsupportedOperationException("Unimplemented method 'setCompassTarget'");
    }

    @Override
    public Location getCompassTarget() {
        throw new UnsupportedOperationException("Unimplemented method 'getCompassTarget'");
    }

    @Override
    public InetSocketAddress getAddress() {
        throw new UnsupportedOperationException("Unimplemented method 'getAddress'");
    }

    @Override
    public void sendRawMessage(String message) {
        throw new UnsupportedOperationException("Unimplemented method 'sendRawMessage'");
    }

    @Override
    public void kickPlayer(String message) {
        throw new UnsupportedOperationException("Unimplemented method 'kickPlayer'");
    }

    @Override
    public void chat(String msg) {
        throw new UnsupportedOperationException("Unimplemented method 'chat'");
    }

    @Override
    public boolean performCommand(String command) {
        throw new UnsupportedOperationException("Unimplemented method 'performCommand'");
    }

    @Override
    public boolean isOnGround() {
        throw new UnsupportedOperationException("Unimplemented method 'isOnGround'");
    }

    @Override
    public boolean isSneaking() {
        throw new UnsupportedOperationException("Unimplemented method 'isSneaking'");
    }

    @Override
    public void setSneaking(boolean sneak) {
        throw new UnsupportedOperationException("Unimplemented method 'setSneaking'");
    }

    @Override
    public boolean isSprinting() {
        throw new UnsupportedOperationException("Unimplemented method 'isSprinting'");
    }

    @Override
    public void setSprinting(boolean sprinting) {
        throw new UnsupportedOperationException("Unimplemented method 'setSprinting'");
    }

    @Override
    public void saveData() {
        throw new UnsupportedOperationException("Unimplemented method 'saveData'");
    }

    @Override
    public void loadData() {
        throw new UnsupportedOperationException("Unimplemented method 'loadData'");
    }

    @Override
    public void setSleepingIgnored(boolean isSleeping) {
        throw new UnsupportedOperationException("Unimplemented method 'setSleepingIgnored'");
    }

    @Override
    public boolean isSleepingIgnored() {
        throw new UnsupportedOperationException("Unimplemented method 'isSleepingIgnored'");
    }

    @Override
    public Location getBedSpawnLocation() {
        throw new UnsupportedOperationException("Unimplemented method 'getBedSpawnLocation'");
    }

    @Override
    public void setBedSpawnLocation(Location location) {
        throw new UnsupportedOperationException("Unimplemented method 'setBedSpawnLocation'");
    }

    @Override
    public void setBedSpawnLocation(Location location, boolean force) {
        throw new UnsupportedOperationException("Unimplemented method 'setBedSpawnLocation'");
    }

    @Override
    public void playNote(Location loc, byte instrument, byte note) {
        throw new UnsupportedOperationException("Unimplemented method 'playNote'");
    }

    @Override
    public void playNote(Location loc, Instrument instrument, Note note) {
        throw new UnsupportedOperationException("Unimplemented method 'playNote'");
    }

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {
        throw new UnsupportedOperationException("Unimplemented method 'playSound'");
    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {
        throw new UnsupportedOperationException("Unimplemented method 'playSound'");
    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch) {
        throw new UnsupportedOperationException("Unimplemented method 'playSound'");
    }

    @Override
    public void playSound(Location location, String sound, SoundCategory category, float volume, float pitch) {
        throw new UnsupportedOperationException("Unimplemented method 'playSound'");
    }

    @Override
    public void playSound(Entity entity, Sound sound, float volume, float pitch) {
        throw new UnsupportedOperationException("Unimplemented method 'playSound'");
    }

    @Override
    public void playSound(Entity entity, String sound, float volume, float pitch) {
        throw new UnsupportedOperationException("Unimplemented method 'playSound'");
    }

    @Override
    public void playSound(Entity entity, Sound sound, SoundCategory category, float volume, float pitch) {
        throw new UnsupportedOperationException("Unimplemented method 'playSound'");
    }

    @Override
    public void playSound(Entity entity, String sound, SoundCategory category, float volume, float pitch) {
        throw new UnsupportedOperationException("Unimplemented method 'playSound'");
    }

    @Override
    public void stopSound(Sound sound) {
        throw new UnsupportedOperationException("Unimplemented method 'stopSound'");
    }

    @Override
    public void stopSound(String sound) {
        throw new UnsupportedOperationException("Unimplemented method 'stopSound'");
    }

    @Override
    public void stopSound(Sound sound, SoundCategory category) {
        throw new UnsupportedOperationException("Unimplemented method 'stopSound'");
    }

    @Override
    public void stopSound(String sound, SoundCategory category) {
        throw new UnsupportedOperationException("Unimplemented method 'stopSound'");
    }

    @Override
    public void stopSound(SoundCategory category) {
        throw new UnsupportedOperationException("Unimplemented method 'stopSound'");
    }

    @Override
    public void stopAllSounds() {
        throw new UnsupportedOperationException("Unimplemented method 'stopAllSounds'");
    }

    @Override
    public void playEffect(Location loc, Effect effect, int data) {
        throw new UnsupportedOperationException("Unimplemented method 'playEffect'");
    }

    @Override
    public <T> void playEffect(Location loc, Effect effect, T data) {
        throw new UnsupportedOperationException("Unimplemented method 'playEffect'");
    }

    @Override
    public boolean breakBlock(Block block) {
        throw new UnsupportedOperationException("Unimplemented method 'breakBlock'");
    }

    @Override
    public void sendBlockChange(Location loc, Material material, byte data) {
        throw new UnsupportedOperationException("Unimplemented method 'sendBlockChange'");
    }

    @Override
    public void sendBlockChange(Location loc, BlockData block) {
        throw new UnsupportedOperationException("Unimplemented method 'sendBlockChange'");
    }

    @Override
    public void sendBlockChanges(Collection<BlockState> blocks, boolean suppressLightUpdates) {
        throw new UnsupportedOperationException("Unimplemented method 'sendBlockChanges'");
    }

    @Override
    public void sendBlockDamage(Location loc, float progress) {
        throw new UnsupportedOperationException("Unimplemented method 'sendBlockDamage'");
    }

    @Override
    public void sendEquipmentChange(LivingEntity entity, EquipmentSlot slot, ItemStack item) {
        throw new UnsupportedOperationException("Unimplemented method 'sendEquipmentChange'");
    }

    @Override
    public void sendSignChange(Location loc, String[] lines) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'sendSignChange'");
    }

    @Override
    public void sendSignChange(Location loc, String[] lines, DyeColor dyeColor) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'sendSignChange'");
    }

    @Override
    public void sendSignChange(Location loc, String[] lines, DyeColor dyeColor, boolean hasGlowingText)
            throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'sendSignChange'");
    }

    @Override
    public void sendMap(MapView map) {
        throw new UnsupportedOperationException("Unimplemented method 'sendMap'");
    }

    @Override
    public void updateInventory() {
        throw new UnsupportedOperationException("Unimplemented method 'updateInventory'");
    }

    @Override
    public GameMode getPreviousGameMode() {
        throw new UnsupportedOperationException("Unimplemented method 'getPreviousGameMode'");
    }

    @Override
    public void setPlayerTime(long time, boolean relative) {
        throw new UnsupportedOperationException("Unimplemented method 'setPlayerTime'");
    }

    @Override
    public long getPlayerTime() {
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerTime'");
    }

    @Override
    public long getPlayerTimeOffset() {
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerTimeOffset'");
    }

    @Override
    public boolean isPlayerTimeRelative() {
        throw new UnsupportedOperationException("Unimplemented method 'isPlayerTimeRelative'");
    }

    @Override
    public void resetPlayerTime() {
        throw new UnsupportedOperationException("Unimplemented method 'resetPlayerTime'");
    }

    @Override
    public void setPlayerWeather(WeatherType type) {
        throw new UnsupportedOperationException("Unimplemented method 'setPlayerWeather'");
    }

    @Override
    public WeatherType getPlayerWeather() {
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerWeather'");
    }

    @Override
    public void resetPlayerWeather() {
        throw new UnsupportedOperationException("Unimplemented method 'resetPlayerWeather'");
    }

    @Override
    public void giveExp(int amount) {
        throw new UnsupportedOperationException("Unimplemented method 'giveExp'");
    }

    @Override
    public void giveExpLevels(int amount) {
        throw new UnsupportedOperationException("Unimplemented method 'giveExpLevels'");
    }

    @Override
    public float getExp() {
        throw new UnsupportedOperationException("Unimplemented method 'getExp'");
    }

    @Override
    public void setExp(float exp) {
        throw new UnsupportedOperationException("Unimplemented method 'setExp'");
    }

    @Override
    public int getLevel() {
        throw new UnsupportedOperationException("Unimplemented method 'getLevel'");
    }

    @Override
    public void setLevel(int level) {
        throw new UnsupportedOperationException("Unimplemented method 'setLevel'");
    }

    @Override
    public int getTotalExperience() {
        throw new UnsupportedOperationException("Unimplemented method 'getTotalExperience'");
    }

    @Override
    public void setTotalExperience(int exp) {
        throw new UnsupportedOperationException("Unimplemented method 'setTotalExperience'");
    }

    @Override
    public void sendExperienceChange(float progress) {
        throw new UnsupportedOperationException("Unimplemented method 'sendExperienceChange'");
    }

    @Override
    public void sendExperienceChange(float progress, int level) {
        throw new UnsupportedOperationException("Unimplemented method 'sendExperienceChange'");
    }

    @Override
    public boolean getAllowFlight() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllowFlight'");
    }

    @Override
    public void setAllowFlight(boolean flight) {
        throw new UnsupportedOperationException("Unimplemented method 'setAllowFlight'");
    }

    @Override
    public void hidePlayer(Player player) {
        throw new UnsupportedOperationException("Unimplemented method 'hidePlayer'");
    }

    @Override
    public void hidePlayer(Plugin plugin, Player player) {
        throw new UnsupportedOperationException("Unimplemented method 'hidePlayer'");
    }

    @Override
    public void showPlayer(Player player) {
        throw new UnsupportedOperationException("Unimplemented method 'showPlayer'");
    }

    @Override
    public void showPlayer(Plugin plugin, Player player) {
        throw new UnsupportedOperationException("Unimplemented method 'showPlayer'");
    }

    @Override
    public boolean canSee(Player player) {
        throw new UnsupportedOperationException("Unimplemented method 'canSee'");
    }

    @Override
    public void hideEntity(Plugin plugin, Entity entity) {
        throw new UnsupportedOperationException("Unimplemented method 'hideEntity'");
    }

    @Override
    public void showEntity(Plugin plugin, Entity entity) {
        throw new UnsupportedOperationException("Unimplemented method 'showEntity'");
    }

    @Override
    public boolean canSee(Entity entity) {
        throw new UnsupportedOperationException("Unimplemented method 'canSee'");
    }

    @Override
    public boolean isFlying() {
        throw new UnsupportedOperationException("Unimplemented method 'isFlying'");
    }

    @Override
    public void setFlying(boolean value) {
        throw new UnsupportedOperationException("Unimplemented method 'setFlying'");
    }

    @Override
    public void setFlySpeed(float value) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'setFlySpeed'");
    }

    @Override
    public void setWalkSpeed(float value) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'setWalkSpeed'");
    }

    @Override
    public float getFlySpeed() {
        throw new UnsupportedOperationException("Unimplemented method 'getFlySpeed'");
    }

    @Override
    public float getWalkSpeed() {
        throw new UnsupportedOperationException("Unimplemented method 'getWalkSpeed'");
    }

    @Override
    public void setTexturePack(String url) {
        throw new UnsupportedOperationException("Unimplemented method 'setTexturePack'");
    }

    @Override
    public void setResourcePack(String url) {
        throw new UnsupportedOperationException("Unimplemented method 'setResourcePack'");
    }

    @Override
    public void setResourcePack(String url, byte[] hash) {
        throw new UnsupportedOperationException("Unimplemented method 'setResourcePack'");
    }

    @Override
    public void setResourcePack(String url, byte[] hash, String prompt) {
        throw new UnsupportedOperationException("Unimplemented method 'setResourcePack'");
    }

    @Override
    public void setResourcePack(String url, byte[] hash, boolean force) {
        throw new UnsupportedOperationException("Unimplemented method 'setResourcePack'");
    }

    @Override
    public void setResourcePack(String url, byte[] hash, String prompt, boolean force) {
        throw new UnsupportedOperationException("Unimplemented method 'setResourcePack'");
    }

    @Override
    public Scoreboard getScoreboard() {
        throw new UnsupportedOperationException("Unimplemented method 'getScoreboard'");
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException("Unimplemented method 'setScoreboard'");
    }

    @Override
    public WorldBorder getWorldBorder() {
        throw new UnsupportedOperationException("Unimplemented method 'getWorldBorder'");
    }

    @Override
    public void setWorldBorder(WorldBorder border) {
        throw new UnsupportedOperationException("Unimplemented method 'setWorldBorder'");
    }

    @Override
    public boolean isHealthScaled() {
        throw new UnsupportedOperationException("Unimplemented method 'isHealthScaled'");
    }

    @Override
    public void setHealthScaled(boolean scale) {
        throw new UnsupportedOperationException("Unimplemented method 'setHealthScaled'");
    }

    @Override
    public void setHealthScale(double scale) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'setHealthScale'");
    }

    @Override
    public double getHealthScale() {
        throw new UnsupportedOperationException("Unimplemented method 'getHealthScale'");
    }

    @Override
    public Entity getSpectatorTarget() {
        throw new UnsupportedOperationException("Unimplemented method 'getSpectatorTarget'");
    }

    @Override
    public void setSpectatorTarget(Entity entity) {
        throw new UnsupportedOperationException("Unimplemented method 'setSpectatorTarget'");
    }

    @Override
    public void sendTitle(String title, String subtitle) {
        throw new UnsupportedOperationException("Unimplemented method 'sendTitle'");
    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        throw new UnsupportedOperationException("Unimplemented method 'sendTitle'");
    }

    @Override
    public void resetTitle() {
        throw new UnsupportedOperationException("Unimplemented method 'resetTitle'");
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count) {
        throw new UnsupportedOperationException("Unimplemented method 'spawnParticle'");
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count) {
        throw new UnsupportedOperationException("Unimplemented method 'spawnParticle'");
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, T data) {
        throw new UnsupportedOperationException("Unimplemented method 'spawnParticle'");
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, T data) {
        throw new UnsupportedOperationException("Unimplemented method 'spawnParticle'");
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY,
            double offsetZ) {
        throw new UnsupportedOperationException("Unimplemented method 'spawnParticle'");
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
            double offsetY, double offsetZ) {
        throw new UnsupportedOperationException("Unimplemented method 'spawnParticle'");
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY,
            double offsetZ, T data) {
        throw new UnsupportedOperationException("Unimplemented method 'spawnParticle'");
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
            double offsetY, double offsetZ, T data) {
        throw new UnsupportedOperationException("Unimplemented method 'spawnParticle'");
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY,
            double offsetZ, double extra) {
        throw new UnsupportedOperationException("Unimplemented method 'spawnParticle'");
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
            double offsetY, double offsetZ, double extra) {
        throw new UnsupportedOperationException("Unimplemented method 'spawnParticle'");
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY,
            double offsetZ, double extra, T data) {
        throw new UnsupportedOperationException("Unimplemented method 'spawnParticle'");
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX,
            double offsetY, double offsetZ, double extra, T data) {
        throw new UnsupportedOperationException("Unimplemented method 'spawnParticle'");
    }

    @Override
    public AdvancementProgress getAdvancementProgress(Advancement advancement) {
        throw new UnsupportedOperationException("Unimplemented method 'getAdvancementProgress'");
    }

    @Override
    public int getClientViewDistance() {
        throw new UnsupportedOperationException("Unimplemented method 'getClientViewDistance'");
    }

    @Override
    public int getPing() {
        throw new UnsupportedOperationException("Unimplemented method 'getPing'");
    }

    @Override
    public String getLocale() {
        throw new UnsupportedOperationException("Unimplemented method 'getLocale'");
    }

    @Override
    public void updateCommands() {
        throw new UnsupportedOperationException("Unimplemented method 'updateCommands'");
    }

    @Override
    public void openBook(ItemStack book) {
        throw new UnsupportedOperationException("Unimplemented method 'openBook'");
    }

    @Override
    public void openSign(Sign sign) {
        throw new UnsupportedOperationException("Unimplemented method 'openSign'");
    }

    @Override
    public void showDemoScreen() {
        throw new UnsupportedOperationException("Unimplemented method 'showDemoScreen'");
    }

    @Override
    public boolean isAllowingServerListings() {
        throw new UnsupportedOperationException("Unimplemented method 'isAllowingServerListings'");
    }

    @Override
    public Spigot spigot() {
        throw new UnsupportedOperationException("Unimplemented method 'spigot'");
    }

    @Override
    public void sendEquipmentChange(LivingEntity entity, Map<EquipmentSlot, ItemStack> items) {
        throw new UnsupportedOperationException("Unimplemented method 'sendEquipmentChange'");
    }

}
