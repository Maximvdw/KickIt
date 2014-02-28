package be.maximvdw.kickit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

/**
 * KickIt
 * 
 * KickIt is een proof of concept plugin gemaakt voor Niels om aan te tonen dat
 * het wel degelijk mogelijk is om items te trappen.
 * 
 * @project KickIt
 * @version 02-2014
 * @category Proof Of Concept Mechanics
 * @author Maxim Van de Wynckel (Maximvdw)
 * @site http://www.mvdw-software.be/
 */
public class KickIt extends JavaPlugin implements Listener {

	/**
	 * On plugin enable
	 */
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	/**
	 * On item pickup
	 * 
	 * Deze event zal gestart worden wanneer een gebruiken een item opraapt. Wij
	 * gaan deze event gebruiken om een trap van een item te simuleren.
	 * 
	 * @param event
	 *            PlayerPickupItemEvent
	 */
	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent event) {
		Player player = event.getPlayer(); // Verkrijg de speler
		Item item = event.getItem(); // Verkrijg item

		/** Controlleer of het gunpowder is */
		Material itemType = item.getItemStack().getType();
		if (itemType == Material.SULPHUR) {
			// Zorg dat het event niet uitgevoerd word
			event.setCancelled(true);
			// Geef het item een trap
			Vector eyeVector = player.getEyeLocation().getDirection()
					.normalize();
			// Vermeerder de vector in alle richtingen
			Vector itemVector = eyeVector.multiply(1.5D);
			// We willen niet dat het item helemaal naar omhoog schiet dus
			// verlaag de Y vector
			itemVector.setY(0.8D);
			// Pas de velocity toe
			item.setVelocity(itemVector);
		}
	}
}
