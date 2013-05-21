/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groupcore;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

/**
 *
 * @author Tim
 */
public class CommandHandler implements Listener{
    private Core core; // Core pointer
    private HashMap<String, GroupCommand> Commands = new HashMap<>(); // Current registered commands
    
    public CommandHandler(Core core){
        this.core = core; // Set the core pointer
        this.core.getServer().getPluginManager().registerEvents(this, core); // Register this is a event listener
    }
    
    // Register a command to this CommandHandler
    public void RegisterCommand(String Command, Object instance){
        if(instance instanceof GroupCommand){ // Is it a valid command?
            this.Commands.put(Command.trim().toLowerCase(), (GroupCommand) instance); // Add command to command list
            Bukkit.getHelpMap().addTopic(this.GenerateHelpTopic((GroupCommand) instance));
        }
    }
    
    private HelpTopic GenerateHelpTopic(final GroupCommand cmd){
        HelpTopic r = new HelpTopic() {
            
            @Override
            public String getName(){
                return cmd.GetName();
            }
            
            @Override
            public boolean canSee(CommandSender cs) {
                return true;
            }
            
            @Override
            public String getShortText(){
                return cmd.GetDescription();
            }
        };
        
        
        
        return r;
    }
    
    // Was a command entered? Process it in that case!
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event){
        String[] args = event.getMessage().split(" "); // Get command with args
        if(this.Commands.containsKey(args[0].trim().toLowerCase())){ // Is this command registered?
            if(args.length-1 >= this.Commands.get(args[0].trim().toLowerCase()).GetMinArgs()){ // Is there enough args
                this.Commands.get(args[0].trim().toLowerCase()).Process(event, this.RemoveFirstElement(args)); // Then process the event!
            }else{
                event.getPlayer().sendMessage(this.Commands.get(args[0].trim().toLowerCase()).GetErrorMessage()); // Send back error message otherwise!
            }
            event.setCancelled(true); // If the command was registered, cancel process!
        }
    }
    
    private ArrayList<String> RemoveFirstElement(String[] arr){
        ArrayList<String> r = new ArrayList<>();
        boolean add = false;
        
        for(String str : arr){
            if(str != null && add){
                r.add(str.trim());
            }
            if(!add) add = true;
        }
        
        return r;
    }
}
