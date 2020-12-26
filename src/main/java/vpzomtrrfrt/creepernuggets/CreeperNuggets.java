package vpzomtrrfrt.creepernuggets;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Random;

@Mod(CreeperNuggets.MODID)
public class CreeperNuggets {
    public static final String MODID = "creepernuggets";

    public CreeperNuggets() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> evt) {
        System.out.println("registerItems called");
        evt.getRegistry().registerAll(ItemCreeperNugget.INSTANCE);
    }

    @SubscribeEvent
    public void onDrops(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityCreeper) {
            event.getDrops().add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, new ItemStack(ItemCreeperNugget.INSTANCE, new Random().nextInt(4))));
        }
    }
}
