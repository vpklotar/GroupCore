/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groupcore;

import java.util.logging.Logger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Tim
 */
public class Core extends JavaPlugin{
    public GroupCoreAPI GroupCoreAPI;
    private static Plugin instance;
    public Config config;
    public PermissionsManager pm;
    
    public Core(){
        instance = this;
    }
    
    @Override
    public void onEnable(){
        this.GroupCoreAPI = new GroupCoreAPI(this);
        this.pm = new PermissionsManager(this);
        
        this.SetupConfigs();
        
        this.info("Loaded!");
    }
    
    @Override
    public void onDisable(){
        this.info("Unloaded!");
    }
    
    public void info(String msg){
        Logger.getLogger("Minecraft").info("["+this.getDescription().getName().toString()+" v. "+this.getDescription().getVersion().toString()+"] "+msg);
    }
    
    private void SetupConfigs(){
        this.config = new Config("config", this);
        
        this.config.save();
    }
    
    //API stuff
    public static boolean isAvailable() {
        Plugin plugin = instance;
        return plugin.isEnabled() && ((Core) plugin).GroupCoreAPI != null;
    }

    public static GroupCoreAPI GetAPI() {
        if (!isAvailable()) {
                return null;
        }
        return ((Core) instance).GroupCoreAPI;
    }
    
}
