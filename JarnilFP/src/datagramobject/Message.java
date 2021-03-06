package datagramobject;

import java.io.Serializable;

public class Message implements Serializable{
	
	private String message;
	private String sender;
	private String receiver;
	private int ttl;
	private int hop;
    private int id;    
    private long msgTime;     
	public Message(int id, String sender, String receiver, String message, int ttl, int hop){
		setId(id);
		setSender(sender);
		setReceiver(receiver);
		setMessage(message);
		setTtl(ttl);
        setHop(hop);
        setmsgTime();
	}
	
	public void setmsgTime(){
		this.msgTime = System.currentTimeMillis();
	}

	public void setId(int id){
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public int getTtl() {
		return ttl;
	}

	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

        public int getHop() {
            return hop;
        }

        public void setHop(int hop) {
            this.hop = hop;
        }

	
}
