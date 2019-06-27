package fr.pumpmykins.zutils.event;

import java.util.List;

import fr.pumpmykins.zutils.utils.TpRequest;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public class TpaEventHandler {

	private List<TpRequest> tprequest;
	
	public TpaEventHandler(List<TpRequest> tprequest) {
		
		this.tprequest = tprequest;
	}

	@SubscribeEvent
	public void playerDisconnect(PlayerLoggedOutEvent event) {
		
		for(int i = 0; i < this.tprequest.size(); i++) {
			
			TpRequest tr = this.tprequest.get(i);
			
			if(tr.getSender().equals(event.player) || tr.getReceiver().equals(event.player)) {
				
				this.tprequest.remove(i);
			}
		}
	}
}
