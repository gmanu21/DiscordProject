package events;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnJoin extends ListenerAdapter {

	@Override
	public void onGenericGuildVoice(GenericGuildVoiceEvent event) {
		String channelId = "";

		List<GuildChannel> channels = event.getGuild().getChannels();

		Map<String, String> channelData = new LinkedHashMap<>();

		// For Getting all channels Names_Ids Of Channel
		for (int i = 0; i < channels.size(); i++) {
			GuildChannel guildChannel = channels.get(i);
			channelData.put(guildChannel.getName(), guildChannel.getId());
		}

		try {

			for (int c = 0; c < channelData.size(); c++) {
				// channelData.entrySet().
				if (!channelData.get("General").isEmpty()) {
					channelId = channelData.get("General");

					// get a list of members in the VoiceChannel
					VoiceChannel voiceChannel = event.getGuild().getVoiceChannelById(channelId);
					List<Member> membersInChannel = voiceChannel.getMembers();

					long timeJoined = event.getMember().getTimeJoined().toInstant().toEpochMilli();
					
					System.out.println("member joined "+timeJoined);
					for (Member member : membersInChannel) {
						//Activity activity = member.getActivity();
						// here we have to get all the events so we can get what is happening
						
						
						System.out.println(member.getId());
						System.out.println(event.getVoiceState().isSendingVideo());
						System.out.println(event.getVoiceState().isStream());
						System.out.println(member.getActivities());
						System.out.println(event.getMember().getVoiceState());

						TimeUnit.SECONDS.sleep(10);

						if (!event.getVoiceState().isSendingVideo() && !event.getVoiceState().isStream()) {

							TimeUnit.SECONDS.sleep(20);
							System.out.println("sleep done 7 sec");

							System.out.println(event.getVoiceState().isSendingVideo());
							System.out.println(event.getVoiceState().isStream());
							if ((!event.getVoiceState().isSendingVideo() && !event.getVoiceState().isStream())) {
								System.out.println("Kicked user");
								event.getGuild().kickVoiceMember(member).queue();
							}
							
						}
					}
					System.out.println("Process Ended");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
