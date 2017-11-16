package com.neurotechnology.Fingers;

import java.util.EnumSet;

public enum ImpressionType {
	nfitLiveScanPlain (0, "Live-scan plain"), //< Live-scan plain
	nfitLiveScanRolled (1, "Live-scan rolled"), //< Live-scan rolled
	nfitNonliveScanPlain (2, "Nonlive-scan plain"), //< Nonlive-scan plain
	nfitNonliveScanRolled (3, "Nonlive-scan rolled"), //< Nonlive-scan rolled
	nfitLatentImpression (4, "Latent impression"), //< Latent impression
	nfitLatentTracing (5, "Latent tracing"), //< Latent tracing
	nfitLatentPhoto (6, "Latent photo"), //< Latent photo
	nfitLatentLift (7, "Latent lift"), //< Latent lift
	nfitSwipe (8, "Swipe"), //< Live-scanned fingerprint by sliding the finger across a "swipe" sensor.
	nfitLiveScanContactless (9, "Live scan contactless"); //< Live-scanned fingerprint using contactless device.
	
	private final int index;
	private final String desc;
	ImpressionType(int index, String desc)
	{
		this.index = index;
		this.desc = desc;
	}
	
	public int eval() {
		return index;
	}

	public String toString() {
		return desc;
	}
	
	public static ImpressionType parse(int value) throws Exception {
		for (ImpressionType it : EnumSet.allOf(ImpressionType.class))
		{
			if (it.index == value) {
				return it;
			}
		}
		throw new Exception("value out of bounds");
	}
}
