package mn.discord;

import events.OnJoin;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class DiscordBot extends ListenerAdapter {

	public static void main(String[] args) throws Exception {

		JDA jda = JDABuilder.createDefault("Token")
				.enableIntents(GatewayIntent.MESSAGE_CONTENT) // enables explicit access to message.getContentDisplay()
				.addEventListeners(new DiscordBot(), new OnJoin()).setMemberCachePolicy(MemberCachePolicy.ALL)
				.setEnabledIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
						GatewayIntent.SCHEDULED_EVENTS,
						GatewayIntent.GUILD_MEMBERS, 
						GatewayIntent.GUILD_VOICE_STATES)
				.build();

	}

}
