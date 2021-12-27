package de.fanta.fancyfirework.commands;

import de.fanta.fancyfirework.FancyFirework;
import de.iani.cubesideutils.bukkit.commands.CommandRouter;

public record CommandRegistration(FancyFirework plugin) {

    public void registerCommands() {
        CommandRouter fancyFireworkRouter = new CommandRouter(plugin.getCommand("fancyfirework"));
        fancyFireworkRouter.addCommandMapping(new EnableToggleCommand(plugin, true), "enable");
        fancyFireworkRouter.addCommandMapping(new EnableToggleCommand(plugin, false), "disable");
        fancyFireworkRouter.addCommandMapping(new SetSpawnRateCommand(plugin), "setSpawnRate");
        fancyFireworkRouter.addCommandMapping(new FireWorkGiveCommand(plugin), "givefirework");

    }
}
