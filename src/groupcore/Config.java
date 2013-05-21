/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groupcore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author Tim
 */
public class Config {
    public YamlConfiguration config = new YamlConfiguration();
    public Map<String, Object> old = new HashMap<>();
    private File configFile = null;
    public Core core;
    
    Config(String config, Core core) {
        this.core = core;
        this.configFile = new File(this.core.getDataFolder().getAbsolutePath()+File.separatorChar+config+".yml");
        
        if(this.configFile.exists()) try {
            this.config.load(configFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.LoadOld();
    }
    
    private void LoadOld(){
        this.old = this.config.getValues(true);
    }
    
    public void reload(){
        try {
            this.config.load(this.configFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void save(){
        if(this.configFile != null && !this.old.equals(this.config.getValues(true))) try {
            this.config.save(this.configFile);
            this.old = this.config.getValues(true);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setValue(String path, Object value){
        this.config.set(path, value);
    }
    
    public void setDefault(String path, String value){
        if(!this.config.contains(path))this.config.set(path, value);
    }
    
    public String getString(String path){
        return (String) this.config.get(path).toString();
    }
    
    public Object getObject(String path){
        return this.config.get(path).toString();
    }
    
    public HashMap<String, String> getChildren(String parent){
        HashMap<String, String> c = new HashMap<String, String>();
        
        for(String s : this.config.getKeys(true)){
            if(s.startsWith(parent)) c.put(s.replace(parent, ""), this.config.getString(s));
        }
        
        return c;
    }
    
    public void SetList(String path, ArrayList<String> list){
        this.config.set(path, Arrays.asList(list.toArray()));
    }
    
    public void SetDefaultList(String path, ArrayList<String> list){
        if(!this.config.contains(path)) {
            this.SetList(path, list);
        }
    }
    
    public List<String> GetList(String path){
        return this.config.getStringList(path);
    }
    
    public HashMap<String, String> GetValues(String parent, boolean deep){
        HashMap<String, String> r = new HashMap<>();
        for(String s : this.config.getConfigurationSection(parent).getValues(deep).keySet()){
            r.put(s, this.getString(parent+s));
        }
        return r;
    }
    
}
