package events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class GuildVoiceJoinEvent extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot())
			return;
		Member member = event.getMember();
		Guild g = member.getGuild();
		System.out.println(member.getId());
		if (!member.getVoiceState().isGuildMuted() && !member.getVoiceState().isSelfMuted()
				&& !member.getVoiceState().isSelfDeafened() && !member.getVoiceState().isSuppressed()) {
			g.kickVoiceMember(member).queue();
		}
	}
}
