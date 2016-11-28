package vpzomtrrfrt.creepernuggets

import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.monster.EntityCreeper
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.living.LivingDropsEvent
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry.ItemStackHolder
import net.minecraftforge.fml.common.{Mod, SidedProxy}

import scala.util.Random

@Mod(name = CreeperNuggets.NAME, modid = CreeperNuggets.MODID, modLanguage = "scala")
object CreeperNuggets {
  final val NAME = "Creeper Nuggets"
  final val MODID = "creepernuggets"

  @SidedProxy(clientSide = "vpzomtrrfrt.creepernuggets.ClientProxy",
    serverSide = "vpzomtrrfrt.creepernuggets.CommonProxy")
  var proxy: CommonProxy = _

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    proxy.preInit()
    MinecraftForge.EVENT_BUS.register(this)
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    proxy.init()
  }

  @SubscribeEvent
  def onDrops(event: LivingDropsEvent): Unit = {
    if(event.entity.isInstanceOf[EntityCreeper]) {
      event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(ItemCreeperNugget, new Random().nextInt(4))))
    }
  }
}
