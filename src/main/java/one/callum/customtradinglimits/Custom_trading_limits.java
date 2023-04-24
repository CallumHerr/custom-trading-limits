package one.callum.customtradinglimits;

import one.callum.customtradinglimits.commands.SetMaxTrades;
import one.callum.customtradinglimits.handlers.TradeAcquiredHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class Custom_trading_limits extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        TradeAcquiredHandler tradeEvent = new TradeAcquiredHandler(this);

        //Set the trades in all worlds on the server
        for (World world : Bukkit.getWorlds()) {
            tradeEvent.setTrades(world);
        }

        getCommand("setmaxtrades").setExecutor(new SetMaxTrades(tradeEvent));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
