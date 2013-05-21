/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groupcore;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Tim
 */
public class GroupCoreAPI {
    private Core core;
    private CommandHandler cmdHandler;
    
    public GroupCoreAPI(Core core){
        this.core = core;
        
        this.cmdHandler = new CommandHandler(this.core);
    }
    
    public String GetConfigPath(){
        return this.core.getDataFolder().getAbsolutePath()+File.separatorChar;
    }
    
    public Config GetMainConfig(){
        return this.core.config;
    }
    
    public boolean Has(Player p, String node){
        return this.core.pm.overlay.Has(p, node);
    }
    
    public PermissionsManager GetPermissionsManager(){
        return this.core.pm;
    }
    
    public void RegisterHook(JavaPlugin plugin){
        this.core.info(plugin.getDescription().getName()+"("+plugin.getDescription().getVersion()+") hook loaded!");
        
        this.core.config.setDefault("Extentions.Enabled."+plugin.getDescription().getName(), "true");
        if("false".equals(this.core.config.getString("Extentions.Enabled."+plugin.getDescription().getName()))) {
            plugin.getPluginLoader().disablePlugin(plugin);
        }
        
        this.core.config.save();
    }
    
    public CommandHandler GetCommandHandler(){
        return this.cmdHandler;
    }
    
    public String GetExtentionConfigPath(JavaPlugin plugin){
        return this.GetConfigPath()+plugin.getDescription().getName()+File.separatorChar;
    }
    
    public Config GetExtentionConfig(JavaPlugin plugin, String Name){
        return new Config(plugin.getDescription().getName()+File.separatorChar+Name, this.core);
    }
    
    public String addColor(String s) {
        s = s.replace("&0", ChatColor.BLACK + "");
        s = s.replace("&1", ChatColor.DARK_BLUE + "");
        s = s.replace("&2", ChatColor.DARK_GREEN + "");
        s = s.replace("&3", ChatColor.DARK_AQUA + "");
        s = s.replace("&4", ChatColor.DARK_RED + "");
        s = s.replace("&5", ChatColor.DARK_PURPLE + "");
        s = s.replace("&6", ChatColor.GOLD + "");
        s = s.replace("&7", ChatColor.GRAY + "");
        s = s.replace("&8", ChatColor.DARK_GRAY + "");
        s = s.replace("&9", ChatColor.BLUE + "");
        s = s.replace("&a", ChatColor.GREEN + "");
        s = s.replace("&b", ChatColor.AQUA + "");
        s = s.replace("&c", ChatColor.RED + "");
        s = s.replace("&e", ChatColor.YELLOW + "");
        s = s.replace("&f", ChatColor.WHITE + "");
        return s;
    }

    public String removeColor(String s) {
        s = s.replace("&0", "");
        s = s.replace("&1", "");
        s = s.replace("&2", "");
        s = s.replace("&3", "");
        s = s.replace("&4", "");
        s = s.replace("&5", "");
        s = s.replace("&6", "");
        s = s.replace("&7", "");
        s = s.replace("&8", "");
        s = s.replace("&9", "");
        s = s.replace("&a", "");
        s = s.replace("&b", "");
        s = s.replace("&c", "");
        s = s.replace("&e", "");
        s = s.replace("&f", "");
        
        s = s.replace(ChatColor.BLACK + "", "");
        s = s.replace(ChatColor.DARK_BLUE + "", "");
        s = s.replace(ChatColor.DARK_GREEN + "", "");
        s = s.replace(ChatColor.DARK_AQUA + "", "");
        s = s.replace(ChatColor.DARK_RED + "", "");
        s = s.replace(ChatColor.DARK_PURPLE + "", "");
        s = s.replace(ChatColor.GOLD + "", "");
        s = s.replace(ChatColor.GRAY + "", "");
        s = s.replace(ChatColor.DARK_GRAY + "", "");
        s = s.replace(ChatColor.BLUE + "", "");
        s = s.replace(ChatColor.GREEN + "", "");
        s = s.replace(ChatColor.AQUA + "", "");
        s = s.replace(ChatColor.RED + "", "");
        s = s.replace(ChatColor.YELLOW + "", "");
        s = s.replace(ChatColor.WHITE + "", "");
        
        return s;
    }
}
