package me.Elliott_.Progression.menus.promts;

import me.Elliott_.Progression.world.GenerateWorld;
import org.bukkit.ChatColor;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;

public class CreateWorldPrompt extends StringPrompt {

    public String getPromptText(ConversationContext context) {
        return ChatColor.GREEN + "Please enter the name of your world, type " + ChatColor.AQUA + "quit" + ChatColor.GREEN + " to abort";
    }

    @Override
    public Prompt acceptInput(ConversationContext conversationContext, String s) {
        conversationContext.getForWhom().sendRawMessage(ChatColor.GREEN + "Generating world " + ChatColor.GOLD + s + ChatColor.GREEN + "...");
        if (conversationContext.getForWhom() instanceof Player) {
            GenerateWorld.generateWorld((Player) conversationContext.getForWhom(), s);
        }
        return Prompt.END_OF_CONVERSATION;
    }

}
