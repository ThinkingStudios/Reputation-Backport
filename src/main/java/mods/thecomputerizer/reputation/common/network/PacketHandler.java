package mods.thecomputerizer.reputation.common.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import mods.thecomputerizer.reputation.common.ModDefinitions;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

	private  static  final  String  PROTOCOL_VERSION  =  "1.0";
	public static final SimpleChannel NETWORK_INSTANCE = NetworkRegistry.ChannelBuilder
			.named(ModDefinitions.getResource("main_channel"))
			.clientAcceptedVersions ( PROTOCOL_VERSION :: equals)
			.serverAcceptedVersions ( PROTOCOL_VERSION :: equals)
			.networkProtocolVersion (() ->  PROTOCOL_VERSION )
			.simpleChannel ();
	public static int disc = 0;

	public static void initPackets() {
		NETWORK_INSTANCE.registerMessage(disc++, SyncFactionsMessage.class, SyncFactionsMessage::encode, SyncFactionsMessage::new, SyncFactionsMessage::handle);
		NETWORK_INSTANCE.registerMessage(disc++, SyncReputationMessage.class, SyncReputationMessage::encode, SyncReputationMessage::new, SyncReputationMessage::handle);
		NETWORK_INSTANCE.registerMessage(disc++, SyncFactionPlayersMessage.class, SyncFactionPlayersMessage::encode, SyncFactionPlayersMessage::new, SyncFactionPlayersMessage::handle);
		NETWORK_INSTANCE.registerMessage(disc++, SetIconMessage.class, SetIconMessage::encode, SetIconMessage::new, SetIconMessage::handle);
		NETWORK_INSTANCE.registerMessage(disc++, FleeIconMessage.class, FleeIconMessage::encode, FleeIconMessage::new, FleeIconMessage::handle);
		NETWORK_INSTANCE.registerMessage(disc++, ChatIconMessage.class, ChatIconMessage::encode, ChatIconMessage::new, ChatIconMessage::handle);
		NETWORK_INSTANCE.registerMessage(disc++, SyncChatIconsMessage.class, SyncChatIconsMessage::encode, SyncChatIconsMessage::new, SyncChatIconsMessage::handle);
	}

	public static void sendTo(Object message, ServerPlayer player) {
		NETWORK_INSTANCE.sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
	}

	@OnlyIn(Dist.CLIENT)
	public static void sendToServer(Object message) {
		NETWORK_INSTANCE.sendToServer(message);
	}

}
