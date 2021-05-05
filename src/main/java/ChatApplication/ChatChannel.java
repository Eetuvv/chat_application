package ChatApplication;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatChannel {

    private final ArrayList<ChatMessage> messages;
    public HashMap<String, ChatChannel> channels;
    String currentChannel;

    public ChatChannel() {
        this.channels = new HashMap<>();
        this.messages = new ArrayList<>();
        this.currentChannel = "Yleinen";
    }

    public void addDefaultChannels() {
        // Add some example channels with different sports
        this.channels.put("Yleinen", new ChatChannel());
        addMessageToChannel("Yleinen", new ChatMessage("Admin", "Täällä on yleistä keskustelua.", "01.01.2021 00:00:00"));
        this.channels.put("Jalkapallo", new ChatChannel());
        addMessageToChannel("Jalkapallo", new ChatMessage("Admin", "Täällä keskustellaan jalkapallosta.", "01.01.2021 00:00:00"));

        this.channels.put("Jääkiekko", new ChatChannel());
        addMessageToChannel("Jääkiekko", new ChatMessage("Admin", "Täällä keskustellaan jääkiekosta.", "01.01.2021 00:00:00"));

        this.channels.put("Tennis", new ChatChannel());
        addMessageToChannel("Tennis", new ChatMessage("Admin", "Täällä keskustellaan tenniksestä.", "01.01.2021 00:00:00"));

        this.channels.put("Hiihto", new ChatChannel());
        addMessageToChannel("Hiihto", new ChatMessage("Admin", "Täällä keskustellaan hiihdosta.", "01.01.2021 00:00:00"));

        this.channels.put("Jääpallo", new ChatChannel());
        addMessageToChannel("Jääpallo", new ChatMessage("Admin", "Täällä keskustellaan jääpallosta.", "01.01.2021 00:00:00"));

        this.channels.put("Yleisurheilu", new ChatChannel());
        addMessageToChannel("Yleisurheilu", new ChatMessage("Admin", "Täällä keskustellaan yleisurheilusta.", "01.01.2021 00:00:00"));

        this.channels.put("Salibandy", new ChatChannel());
        addMessageToChannel("Salibandy", new ChatMessage("Admin", "Täällä keskustellaan salibandysta.", "01.01.2021 00:00:00"));

        this.channels.put("Koripallo", new ChatChannel());
        addMessageToChannel("Koripallo", new ChatMessage("Admin", "Täällä keskustellaan koripallosta.", "01.01.2021 00:00:00"));
    }

    public void addChannel(String channel) {
        this.channels.put(channel, new ChatChannel());
    }

    public boolean addMessageToChannel(String channel, ChatMessage msg) {
        if (this.channels.containsKey(channel)) {
            this.channels.get(channel).messages.add(msg);
        }
        return false;
    }

    public ArrayList<ChatMessage> getMessagesFromChannel(String channel) {
        if (this.channels.containsKey(channel)) {
            return this.channels.get(channel).messages;
        }
        return null;
    }

    public ArrayList listChannels() {
        ArrayList<String> channelList = new ArrayList<>();

        for (String key : channels.keySet()) {
            channelList.add(key);
        }
        return channelList;
    }

    public String getCurrentChannel() {
        return this.currentChannel;
    }

    public void setCurrentChannel(String channel) {
        this.currentChannel = channel;
    }
}
