package checksum;

/**
 *
 * @author Administrateur
 */
public class CheckSum {

    /**
     * @param args 
     */
    public static void main(String[] args) {
        // message 
        String message = "40400011123240371133FF4101";
        String checksum = CRC_CCITT_16(message, 0x1021,0xFFFF, true );
        
        System.out.println("le checcksum CRC_CCITT_16 bite attendu est: "+ checksum);
         System.out.println();
        
        
    }
       
/**
 sachant que me ssage doit etre en hexa ont met hexaMessahe a true (booelean)
 * le polynome est 1021 
 * le mesage de fin est 0xFFFF
 * 
     * @param inputStr
     * @param CRC
     * @param Polynome
     * @param HexMessage
     * @return 
 */
public static String CRC_CCITT_16(String inputStr, int Polynome,  int CRC, boolean HexMessage) {
      

    int strLen = inputStr.length();     
    int[] intArray;

    if (HexMessage) {
          if (strLen % 2 != 0) {
             
            inputStr = inputStr.substring(0, strLen - 1) + "0"  + inputStr.substring(strLen - 1, strLen);
                   
            strLen++;
        }

           intArray = new int[strLen / 2];
           int    ctr = 0;
        for (int n = 0; n < strLen; n += 2) {
            intArray[ctr] = Integer.valueOf(inputStr.substring(n, n + 2), 16);
            ctr++;
        }
        
    } else {
        intArray = new int[inputStr.getBytes().length];
        int ctr=0;
        for(byte b : inputStr.getBytes()){
            intArray[ctr] = b;
            ctr++;
        }
         return inputStr;
    }

    // mon code de  calcule du CRC-CCITT-16 bite tous en respectant le princite du registe à bit 
    for (int b : intArray) {
        for (int i = 0; i < 8; i++) {
            boolean bit = ((b >> (7 - i) & 1) == 1);
            boolean c15 = ((CRC >> 15 & 1) == 1);
            CRC <<= 1;
            if (c15 ^ bit)
                CRC ^= Polynome;
        }
    }

    CRC &= 0xFFFF;
    return Integer.toHexString(CRC).toUpperCase();
} 
}
