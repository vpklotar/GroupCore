/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groupcore;

import java.util.ArrayList;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

/**
 *
 * @author Tim
 */
public interface GroupCommand {
    public int GetMinArgs(); // What is the minimum amount of args?
    public void Process(PlayerCommandPreprocessEvent event, ArrayList<String> args); // When all criterias for the command was filled and the command is fired
    public String GetErrorMessage(); // If there was an error
    public String GetDescription(); // Get description for the /help list
    public String GetName(); // Get name for the /help list
}
