package com.neurotechnology.Templates;

public class Minutia {
		private int X; ///< X coordinate of this #NFMinutia.
		private int Y; ///< Y coordinate of this #NFMinutia.
		private MinutiaType Type; ///< #NFMinutiaType of this #NFMinutia.
		private int Angle; ///< Angle of this #NFMinutia.
		private int Quality; ///< Quality of this #NFMinutia.
		private int Curvature; ///< Ridge curvature this #NFMinutia.
		private int G; ///< G (ridge density) of this #NFMinutia.
		
		public void setG(int g) {
			G = g;
		}
		public int getG() {
			return G;
		}
		public void setCurvature(int curvature) {
			Curvature = curvature;
		}
		public int getCurvature() {
			return Curvature;
		}
		public void setQuality(int quality) {
			Quality = quality;
		}
		public int getQuality() {
			return Quality;
		}
		public void setAngle(int angle) {
			Angle = angle;
		}
		public int getAngle() {
			return Angle;
		}
		public void setType(MinutiaType type) {
			Type = type;
		}
		
		public void setType(int value){
				switch (value){
					case 0 : Type = MinutiaType.nfmtUnknown;	
						break;
					case 1 : Type = MinutiaType.nfmtEnd;
						break;
					case 2 : Type = MinutiaType.nfmtBifurcation;
						break;
				}
		}
		public MinutiaType getType() {
			return Type;
		}
		public void setY(int y) {
			Y = y;
		}
		public int getY() {
			return Y;
		}
		public void setX(int x) {
			X = x;
		}
		public int getX() {
			return X;
		}
}
