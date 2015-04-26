package me.Elliott_.Progression.menus;

import me.Elliott_.Progression.world.WorldConfiguration.WorldConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

public class AddBuilderPromt extends StringPrompt {

    public String getPromptText(ConversationContext context) {
        return ChatColor.YELLOW + "Please enter the name of the player you wish to make a " + ChatColor.RED + "builder" + ChatColor.YELLOW + ", type " + ChatColor.AQUA + "quit" + ChatColor.GREEN + " to abort";
    }

    @Override
    public Prompt acceptInput(ConversationContext conversationContext, String s) {
        if (conversationContext.getForWhom() instanceof Player) {
            if (Bukkit.getPlayer(s) != null) {
                WorldConfiguration.addBuilder(((Player) conversationContext.getForWhom()).getWorld(), Bukkit.getPlayer(s));
                conversationContext.getForWhom().sendRawMessage(ChatColor.GREEN + "Added  " + ChatColor.GOLD + s + ChatColor.GREEN + " as a builder");
            }

        }
        return Prompt.END_OF_CONVERSATION;
    }

}
