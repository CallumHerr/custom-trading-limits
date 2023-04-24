package one.callum.customtradinglimits.handlers;

import one.callum.customtradinglimits.Custom_trading_limits;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class TradeAcquiredHandler implements Listener {
    private final Custom_trading_limits plugin;
    private int maxTrades;

    /**
     * Set the variables that will be needed in later functions
     * @param plugin The main class
     */
    public TradeAcquiredHandler(Custom_trading_limits plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        this.maxTrades = plugin.getConfig().getInt("maxTrades");
    }

    /**
     * Sets the trades max uses to the amount predefined by server owner when a new trade is generated
     * @param event information on the VillagerAcquireTradeEvent
     */
    @EventHandler
    public void onNewTrade(VillagerAcquireTradeEvent event) {
        event.getRecipe().setMaxUses(this.maxTrades);
    }

    /**
     * Sets all villager trades to have the max uses in specified world on the server
     * @param world the world in which to update villager trades
     */
    public void setTrades(World world) {
        //find only the villager entities
        for (Entity ent : world.getEntities()) {
            if (ent instanceof Villager) {
                //Create new mutable list
                List<MerchantRecipe> recipes = new ArrayList<>(((Villager) ent).getRecipes());
                for (MerchantRecipe rec : recipes) {
                    //Resets the current number of uses and sets the new max defined in config
                    rec.setMaxUses(this.maxTrades);
                    rec.setUses(0);
                }
                //Sets the current villagers trades
                ((Villager) ent).setRecipes(recipes);
            }
        }
    }

    /**
     * Sets the maxTrades property and saves it in config then updates villager trades in all worlds.
     * @param maxTrades new limit for villager trades
     */
    public void setMaxTrades(int maxTrades) {
        this.maxTrades = maxTrades;
        this.plugin.getConfig().set("maxTrades", maxTrades);
        this.plugin.saveConfig();

        //Set trades to new max in all worlds on server
        for (World world : Bukkit.getWorlds()) {
            setTrades(world);
        }
    }
}
