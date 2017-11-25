package eus.julenugalde.sandbox.sound;

public enum Note {
	SILENCE, 
	A0,	A0_SHARP, B0, C0, C0_SHARP, D0, D0_SHARP, E0, F0, F0_SHARP, G0, G0_SHARP, 
	A1,	A1_SHARP, B1, C1, C1_SHARP, D1, D1_SHARP, E1, F1, F1_SHARP, G1, G1_SHARP, 
	A2,	A2_SHARP, B2, C2, C2_SHARP, D2, D2_SHARP, E2, F2, F2_SHARP, G2, G2_SHARP, 
	A3,	A3_SHARP, B3, C3, C3_SHARP, D3, D3_SHARP, E3, F3, F3_SHARP, G3, G3_SHARP, 
	A4,	A4_SHARP, B4, C4, C4_SHARP, D4, D4_SHARP, E4, F4, F4_SHARP, G4, G4_SHARP, 
	A5,	A5_SHARP, B5, C5, C5_SHARP, D5, D5_SHARP, E5, F5, F5_SHARP, G5, G5_SHARP, 
	A6,	A6_SHARP, B6, C6, C6_SHARP, D6, D6_SHARP, E6, F6, F6_SHARP, G6, G6_SHARP;
        
    public double frequency() {
    	int n = this.ordinal();
    	if (n > 0) {
    		double exp = ((double)n-1) / 12d;
    		return 27.5d * Math.pow(2d, exp);
    	}
    	return 0;
    }
}
