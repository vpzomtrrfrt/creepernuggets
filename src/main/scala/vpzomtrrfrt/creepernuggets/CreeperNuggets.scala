package vpzomtrrfrt.creepernuggets

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.{Mod, SidedProxy}

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
}
