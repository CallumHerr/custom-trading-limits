package one.callum.customtradinglimits.commands;

import one.callum.customtradinglimits.handlers.TradeAcquiredHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

public class SetMaxTrades implements CommandExecutor {
    private final TradeAcquiredHandler tradeEvent;

    /**
     * Sets the TradeAcquiredHandler instance so that the maxUses can be set to the event class
     * @param tradeEvent a TradeAcquiredHandler instance
     */
    public SetMaxTrades(TradeAcquiredHandler tradeEvent) {
        this.tradeEvent = tradeEvent;
    }

    /**
     * Sets the max trades across the server if valid integer is provided
     * @param sender Issuer of the command
     * @param command The command ran
     * @param label The command alias that was used to issue the command
     * @param args The arguments provided when command was issued
     * @return boolean, true if the command was a success, false if the command failed.
     */
    @Override
    public boolean onCommand(@NonNull CommandSender sender,@NonNull Command command,@NonNull String label, String[] args) {
        //If player gives a valid number it will set the trades and return true, if invalid number will return false.
        try {
            int maxUses = Integer.parseInt(args[0]);
            this.tradeEvent.setMaxTrades(maxUses);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
