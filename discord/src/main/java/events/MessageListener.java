package events;

import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter
{
 

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
      
            System.out.printf("[PM] %s: %s\n", 
                                    event.getMessage().getContentDisplay());
            
            System.out.println(event.getChannelType().PRIVATE);
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                     event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());

      
       
    }
}
