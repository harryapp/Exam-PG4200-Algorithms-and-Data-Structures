package ex5;

import java.util.HashMap;


public class ChessMoveCompressor {

    public static byte[] compress(String chessMoves) {

        BitWriter writer = new BitWriter();

        writer.write(chessMoves.length());

        for (int i = 0; i < chessMoves.length(); i++) {
            char c = chessMoves.charAt(i);

            HashMap<Character, String> bitMap = new HashMap<>();
            bitMap.put('0', "00000");
            bitMap.put('1', "00001");
            bitMap.put('2', "00010");
            bitMap.put('3', "00011");
            bitMap.put('4', "00100");
            bitMap.put('5', "00101");
            bitMap.put('6', "00110");
            bitMap.put('7', "00111");
            bitMap.put('8', "01000");
            bitMap.put('9', "01001");
            bitMap.put('a', "01010");
            bitMap.put('b', "01011");
            bitMap.put('c', "01100");
            bitMap.put('d', "01101");
            bitMap.put('e', "01110");
            bitMap.put('f', "01111");
            bitMap.put('g', "10000");
            bitMap.put('h', "10001");
            bitMap.put('p', "10010");
            bitMap.put('r', "10011");
            bitMap.put('k', "10100");
            bitMap.put('l', "10101");
            bitMap.put('q', "10110");
            bitMap.put('!', "10111");

            if (bitMap.containsKey(c)) {
                String bitValue = bitMap.get(c);
                for (int j = 0; j < bitValue.length(); j++) {
                    if (bitValue.charAt(j) == '0')
                        writer.write(false);
                    else if (bitValue.charAt(j) == '1')
                        writer.write(true);
                    else
                        throw new IllegalArgumentException("Unrecognized bit value: " + bitValue.charAt(j));
                }
            } else {
                throw new IllegalArgumentException("Unrecognized character: " + c);
            }
        }

        return writer.extract();
    }

    public static String decompress(byte[] data) {

        BitReader reader = new BitReader(data);

        int n = reader.readInt();

        StringBuffer buffer = new StringBuffer(n);

        HashMap<String, Character> charMap = new HashMap<>();
        charMap.put("00000", '0');
        charMap.put("00001", '1');
        charMap.put("00010", '2');
        charMap.put("00011", '3');
        charMap.put("00100", '4');
        charMap.put("00101", '5');
        charMap.put("00110", '6');
        charMap.put("00111", '7');
        charMap.put("01000", '8');
        charMap.put("01001", '9');
        charMap.put("01010", 'a');
        charMap.put("01011", 'b');
        charMap.put("01100", 'c');
        charMap.put("01101", 'd');
        charMap.put("01110", 'e');
        charMap.put("01111", 'f');
        charMap.put("10000", 'g');
        charMap.put("10001", 'h');
        charMap.put("10010", 'p');
        charMap.put("10011", 'r');
        charMap.put("10100", 'k');
        charMap.put("10101", 'l');
        charMap.put("10110", 'q');
        charMap.put("10111", '!');

        for (int i = 0; i < n; i++) {

            StringBuilder bitBuffer = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                boolean bit = reader.readBoolean();
                if (bit)
                    bitBuffer.append("1");
                else
                    bitBuffer.append("0");
            }
            buffer.append(charMap.get(bitBuffer.toString()));
        }

        return buffer.toString();
    }

}
