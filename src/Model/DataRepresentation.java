package Model;

import java.util.Map;

/**
 *
 * @author Nicole Hernandez
 */
public class DataRepresentation {

    private String unit;

    Map<String, Double> bitSI;
    Map<String, Double> isoIec;
    Map<String, Double> bytes;

    public DataRepresentation() {

        isoIec = Map.of(
                "KiB", Math.pow(2, 10),
                "MiB", Math.pow(2, 20),
                "GiB", Math.pow(2, 30),
                "TiB", Math.pow(2, 40),
                "PiB", Math.pow(2, 50),
                "EiB", Math.pow(2, 60),
                "ZiB", Math.pow(2, 70),
                "YiB", Math.pow(2, 80)
        );

        bitSI = Map.of(
                "bit", 1.0,
                "Kb", 1e3,
                "Mb", 1e6,
                "Gb", 1e9,
                "Tb", 1e12,
                "Pb", 1e15,
                "Eb", 1e18,
                "Zb", 1e21,
                "Yb", 1e24
        );

        bytes = Map.of(
                "Byte", 1.0,
                "KB", 1e3,
                "MB", 1e6,
                "GB", 1e9,
                "TB", 1e12,
                "PB", 1e15,
                "EB", 1e18,
                "ZB", 1e21,
                "YB", 1e24
        );
    }

    public DataRepresentation(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return unit;
    }

    public double convert(double amount, String fromUnit, String toUnit) {
        // Primero pasaremos todo a bit para que nos facilite 
        // la conversion de todas las unidades.
        double valueInBits = toBits(amount, fromUnit);

        return fromBits(valueInBits, toUnit);
    }

    // Convertimos la Unidad en bits y la multiplicamos por la cantidad.
    private double toBits(double amount, String fromUnit) {
        if (fromUnit.charAt(1) == 'b' || fromUnit.equals("bit")) {
            return amount * bitSI.get(fromUnit);
        }
        if (fromUnit.charAt(1) == 'B' || fromUnit.equals("Byte")) {
            return amount * bytes.get(fromUnit) * 8;
        }
        return amount * isoIec.get(fromUnit) * 8;

    }

    // Convertimos la segunda unidad a Bits
    // (cantidad*(Unidad en bits))/(Unidad en bits)
    private double fromBits(double bits, String toUnit) {
        // Acedemos al valor del mapa directamente con la clave(key).
        if (toUnit.charAt(1) == 'b' || toUnit.equals("bit")) {
            return bits / bitSI.get(toUnit);
        }
        if (toUnit.charAt(1) == 'B' || toUnit.equals("Byte")) {
            return bits / (bytes.get(toUnit) * 8);
        }
        return bits / (isoIec.get(toUnit) * 8);
    }
    
    public boolean isDecimal(String decimal){
        long itspoint = 0;
        for(int i=0; i<decimal.length(); i++){
            if(itspoint>1){
                return false;
            }
            if(Character.isLetter(decimal.charAt(i))){
                return false;
            }
            if(decimal.charAt(i)=='.'){
                itspoint++;
            }
            if(decimal.charAt(i)==' '){
                return false;
            }
        }
       return true;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
