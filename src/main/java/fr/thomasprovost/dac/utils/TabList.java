package fr.thomasprovost.dac.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TabList {

    public static void setTabListHeaderFooter(Player player) {
        String header = ChatColor.YELLOW + "• §f§lDé à coudre " + ChatColor.YELLOW + "•\n" +
                "§f     Bon jeu §l" + player.getName() + " §fsur nos serveurs !\n§r";
        String footer = "\n§eTwitter: §f@pvst_tom\n§eDiscord: §fhttps://discord.gg/JQaN4qtFCf\n§eSite: §fgithub.com/thomas-provost\n§r";

        sendPacket(player, createPacket(header, footer));
    }

    private static Object createPacket(String header, String footer) {
        try {
            Class<?> packetClass = Class.forName("net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter");
            Object packet = packetClass.newInstance();

            if (header != null) {
                Field headerField = packetClass.getDeclaredField("a");
                headerField.setAccessible(true);
                headerField.set(packet, createChatComponent(header));
            }

            if (footer != null) {
                Field footerField = packetClass.getDeclaredField("b");
                footerField.setAccessible(true);
                footerField.set(packet, createChatComponent(footer));
            }

            return packet;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Object createChatComponent(String text) {
        try {
            Class<?> chatComponentTextClass = Class.forName("net.minecraft.server.v1_8_R3.ChatComponentText");
            Constructor<?> chatComponentTextConstructor = chatComponentTextClass.getConstructor(String.class);
            Object chatComponentText = chatComponentTextConstructor.newInstance(text);

            return chatComponentText;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void sendPacket(Player player, Object packet) {
        try {
            Object playerConnection = getPlayerConnection(player);

            Method sendPacketMethod = playerConnection.getClass().getMethod("sendPacket", Class.forName("net.minecraft.server.v1_8_R3.Packet"));
            sendPacketMethod.invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object getPlayerConnection(Player player) {
        try {
            Method getHandleMethod = player.getClass().getMethod("getHandle");
            Object playerHandle = getHandleMethod.invoke(player);

            Field playerConnectionField = playerHandle.getClass().getField("playerConnection");
            Object playerConnection = playerConnectionField.get(playerHandle);

            return playerConnection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



}
