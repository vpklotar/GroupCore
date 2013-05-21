/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groupcore;

import java.util.ArrayList;
import org.bukkit.plugin.Plugin;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 *
 * @author Tim
 */
public class PermissionsManager {
    private Core core;
    public PermissionManager pex = null;
    public com.nijiko.permissions.PermissionHandler perm = null;
    private String Using = "";
    public String Using(){
        return this.Using;
    }
    public Permissions overlay;
    
    public PermissionsManager(Core core){
        this.core = core;
        
        if(this.setupPermissions()) {
            this.core.info("Permissions Manager hooked in to "+this.Using+"!");
        }else{
            this.core.info("No permissions system found! Using OP flags!");
        }
        
        
        this.overlay = new Permissions(this);
    }
    
    private boolean setupPermissions()
    {
        
        if(this.PluginEnabled("PermissionsEx")){
            this.pex = PermissionsEx.getPermissionManager();
            if(pex != null){
                this.Using = "PermissionsEx";
                return true;
            }
        }else if(this.PluginEnabled("Permissions")){
            this.perm = ((com.nijikokun.bukkit.Permissions.Permissions) this.core.getServer().getPluginManager().getPlugin("Permissions")).getHandler();
            if(this.perm != null){
                this.Using = "Permissions";
                return true;
            }
        }
        
        return false;
    }
    
    private boolean PluginEnabled(String name){
        for(Plugin s : this.core.getServer().getPluginManager().getPlugins()){
            if(s.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
