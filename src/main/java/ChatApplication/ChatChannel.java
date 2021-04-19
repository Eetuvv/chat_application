package ChatApplication;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatChannel {
    
    private ArrayList<ChatMessage> messages;
    HashMap<String, ChatChannel> channels;
    String currentChannel;
    
    public ChatChannel () {
        this.channels = new HashMap<>();
        this.messages = new ArrayList<>();
        this.currentChannel = "Yleinen";
    }
    
    public void addDefaultChannels() {
        this.channels.put("Yleinen", new ChatChannel());
        addMessageToChannel("Yleinen", new ChatMessage("Täällä on yleistä keskustelua.", "00:00:00"));
        this.channels.put("Jalkapallo", new ChatChannel());
        addMessageToChannel("Jalkapallo", new ChatMessage("Täällä keskustellaan jalkapallosta.", "00:00:00"));
        
        this.channels.put("Jääkiekko", new ChatChannel());
        addMessageToChannel("Jääkiekko", new ChatMessage("Täällä keskustellaan jääkiekosta.", "00:00:00"));
        
        this.channels.put("Tennis", new ChatChannel());
        addMessageToChannel("Tennis", new ChatMessage("Täällä keskustellaan tenniksestä.", "00:00:00"));
        
        this.channels.put("Hiihto", new ChatChannel());
        addMessageToChannel("Hiihto", new ChatMessage("Täällä keskustellaan hiihdosta.", "00:00:00"));
        
        this.channels.put("Jääpallo", new ChatChannel());
        addMessageToChannel("Jääpallo", new ChatMessage("Täällä keskustellaan jääpallosta.", "00:00:00"));
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
