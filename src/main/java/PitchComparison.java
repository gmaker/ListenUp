/**
 * Created by Sean Murphy on 10/30/2017.
 */
public class PitchComparison {

    private float pitch;
    private char[] notes;

    public PitchComparison(){

    }

    public static void main(String[] args){

        double A = 440.0000;

        System.out.println(A*16/9);
        System.out.println(A*9/5);

    }

}

enum Pitches {
    A, Af, As, B, Bf, Bs, C, Cf, Cs, D, Df, Ds,
    E, Ef, Es, F, Ff, Fs, G, Gf, Gs;
}

/**
 * Pitch frequency ratios, where f is the original frequency:
 *
 * whole tone 9/8
 * half tone 256/243
 *
 * minor 2nd       256/243f
 * major 2nd       9/8f
 * minor 3rd       6/5f
 * major 3rd       5/4f
 * perfect 4th     4/3f
 * tritone         64/45f OR 45/32f OR 4073/2880f (last one is most accurate)
 * perfect 5th     3/2f
 * minor 6th       8/5f
 * major 6th       5/3f OR 27/16f
 * minor 7th       16/9f OR 9/5f (first one seems to be more accurate)
 * major 7th       15/8f
 * perfect octave  2f
 *
 * RANGES:
 *
 * Bass - C2-E4
 * Tenor - C3-C5
 * Alto - G3-G5
 * Soprano - C4-C6
 */

