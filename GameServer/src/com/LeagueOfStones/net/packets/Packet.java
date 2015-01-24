package com.LeagueOfStones.net.packets;

import com.LeagueOfStones.net.GameServer;


public abstract class Packet {
	
	public static enum PacketTypes {
		INVALID(-1), 
        LOGIN(00), 
        DISCONNECT(01),
        ENQUEUE(02),
        STARTGAME(03),
        GAMEWON(04),
        GAMELOST(05),
        ATTACK(06),
        PLAYCARD(07),
        CARDDIED(10),
        CARDUPDATE(11),
        AREYOUTHERE(12);

        private int packetId;

        private PacketTypes(int packetId) {
            this.packetId = packetId;
        }

        public int getId() {
            return packetId;
        }
    }

    public byte packetId;

    public Packet(int packetId) {
        this.packetId = (byte) packetId;
    }

    //public abstract void writeData(GameClient client);

    public abstract void writeData(GameServer server);

    //gets the string after the first two numbers
    public String readData(byte[] data) {
        String message = new String(data).trim();
        return message.substring(2);
    }

    public abstract byte[] getData();

    //gets the packet type depending of first two numbers leading in the packet name
    public static PacketTypes lookupPacket(String packetId) {
        try {
            return lookupPacket(Integer.parseInt(packetId));
        } catch (NumberFormatException e) {
            return PacketTypes.INVALID;
        }
    }

    public static PacketTypes lookupPacket(int id) {
        for (PacketTypes p : PacketTypes.values()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return PacketTypes.INVALID;
    }
}
