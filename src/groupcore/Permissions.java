/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groupcore;

import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.entity.Player;

/**
 *
 * @author Tim
 */
public class Permissions {
    private PermissionsManager manager;
    String using;
    
    public Permissions(PermissionsManager manager){
        this.manager = manager;
        this.using = this.manager.Using();
    }
    
    public boolean Has(Player p, String node) {
        if("PermissionsEx".equals(using)){
            return this.manager.pex.has(p, node);
        }else if("Permissions".equals(using)){
            return this.manager.perm.has(p, node);
        }
        
        return p.isOp();
    }
    
    public String GetPrefix(Player p){
        if("PermissionsEx".equals(using)){
            return this.manager.pex.getUser(p).getPrefix();
        }else if("Permissions".equals(using)){
            return this.manager.perm.getGroupPrefix(this.manager.perm.getPrimaryGroup(p.getPlayer().getName(), p.getPlayer().getWorld().getName()), p.getWorld().getName());
        }
        
        return "";
    }
    
    public ArrayList<String> GetNodes(Player p){
        ArrayList<String> r = new ArrayList<>();
        
        if("PermissionsEx".equals(using)){
            r.addAll(Arrays.asList(this.manager.pex.getUser(p).getPermissions(p.getWorld().getName())));
        }
        
        return r;
    }
    
}
