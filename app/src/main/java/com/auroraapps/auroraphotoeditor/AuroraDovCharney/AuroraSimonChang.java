package com.auroraapps.auroraphotoeditor.AuroraDovCharney;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;


public class AuroraSimonChang {

    // The 1024 bit Diffie-Hellman modulus values used by SKIP
    private static final byte skip1024ModulusBytes[] = {
            (byte) 0xF4, (byte) 0x88, (byte) 0xFD, (byte) 0x58,
            (byte) 0x4E, (byte) 0x49, (byte) 0xDB, (byte) 0xCD,
            (byte) 0x20, (byte) 0xB4, (byte) 0x9D, (byte) 0xE4,
            (byte) 0x91, (byte) 0x07, (byte) 0x36, (byte) 0x6B,
            (byte) 0x33, (byte) 0x6C, (byte) 0x38, (byte) 0x0D,
            (byte) 0x45, (byte) 0x1D, (byte) 0x0F, (byte) 0x7C,
            (byte) 0x88, (byte) 0xB3, (byte) 0x1C, (byte) 0x7C,
            (byte) 0x5B, (byte) 0x2D, (byte) 0x8E, (byte) 0xF6,
            (byte) 0xF3, (byte) 0xC9, (byte) 0x23, (byte) 0xC0,
            (byte) 0x43, (byte) 0xF0, (byte) 0xA5, (byte) 0x5B,
            (byte) 0x18, (byte) 0x8D, (byte) 0x8E, (byte) 0xBB,
            (byte) 0x55, (byte) 0x8C, (byte) 0xB8, (byte) 0x5D,
            (byte) 0x38, (byte) 0xD3, (byte) 0x34, (byte) 0xFD,
            (byte) 0x7C, (byte) 0x17, (byte) 0x57, (byte) 0x43,
            (byte) 0xA3, (byte) 0x1D, (byte) 0x18, (byte) 0x6C,
            (byte) 0xDE, (byte) 0x33, (byte) 0x21, (byte) 0x2C,
            (byte) 0xB5, (byte) 0x2A, (byte) 0xFF, (byte) 0x3C,
            (byte) 0xE1, (byte) 0xB1, (byte) 0x29, (byte) 0x40,
            (byte) 0x18, (byte) 0x11, (byte) 0x8D, (byte) 0x7C,
            (byte) 0x84, (byte) 0xA7, (byte) 0x0A, (byte) 0x72,
            (byte) 0xD6, (byte) 0x86, (byte) 0xC4, (byte) 0x03,
            (byte) 0x19, (byte) 0xC8, (byte) 0x07, (byte) 0x29,
            (byte) 0x7A, (byte) 0xCA, (byte) 0x95, (byte) 0x0C,
            (byte) 0xD9, (byte) 0x96, (byte) 0x9F, (byte) 0xAB,
            (byte) 0xD0, (byte) 0x0A, (byte) 0x50, (byte) 0x9B,
            (byte) 0x02, (byte) 0x46, (byte) 0xD3, (byte) 0x08,
            (byte) 0x3D, (byte) 0x66, (byte) 0xA4, (byte) 0x5D,
            (byte) 0x41, (byte) 0x9F, (byte) 0x9C, (byte) 0x7C,
            (byte) 0xBD, (byte) 0x89, (byte) 0x4B, (byte) 0x22,
            (byte) 0x19, (byte) 0x26, (byte) 0xBA, (byte) 0xAB,
            (byte) 0xA2, (byte) 0x5E, (byte) 0xC3, (byte) 0x55,
            (byte) 0xE9, (byte) 0x2F, (byte) 0x78, (byte) 0xC7
    };
    // The SKIP 1024 bit modulus
    private static final BigInteger skip1024Modulus
            = new BigInteger(1, skip1024ModulusBytes);
    // The base used with the SKIP 1024 bit modulus
    private static final BigInteger skip1024Base = BigInteger.valueOf(2);
    //public static ArrayList<String> arList;
    public static String AB, BASE64;
    public static AuroraDeanandDanCaten objPref;

    private AuroraSimonChang() {
    }

    public static void main(String argv[]) {
        try {
            String mode = "USE_SKIP_DH_PARAMS";

            AuroraSimonChang keyAgree = new AuroraSimonChang();

            if (argv.length > 1) {
                keyAgree.usage();
                throw new Exception("Wrong number of command options");
            } else if (argv.length == 1) {
                if (!(argv[0].equals("-gen"))) {
                    keyAgree.usage();
                    throw new Exception("Unrecognized flag: " + argv[0]);
                }
                mode = "GENERATE_DH_PARAMS";
            }

            keyAgree.run(mode);
        } catch (Exception e) {
            System.err.println("Error: " + e);
            System.exit(1);
        }
    }

    private static void byte2hex(byte b, StringBuffer buf) {
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }

    public static String toHexString(byte[] block) {
        StringBuffer buf = new StringBuffer();

        int len = block.length;

        for (int i = 0; i < len; i++) {
            byte2hex(block[i], buf);
            if (i < len - 1) {
                buf.append(":");
            }
        }
        return buf.toString();
    }

    public static String CIPHER_TO_DHKEY_AGREEMENT_256BIT_KEYGENERATOR(String BASE648, String mode, Context context) throws Exception {
        String POINT128 = "";

        //507543543531369336336495513552528537519495333510525501528336531507528543528498495510543528537522486543498537510486519540336402555543537486390531531540405537486522498540336426486516399498549336396528519528537444531519486540507435507528543528402495510543528537444507510522522498537336504498543495510537333531507531
        String GDMODE_128BIT = "0507543543531369336336495513552528537519495333510525501528336531507528543528498495510543528537522486543498537510486519540336";
        String GDMODE_24BIT = "0492528522333504510501522486516498537333504510501540543546495510528333522558525486522498504510501453604";

        objPref = new AuroraDeanandDanCaten(context);
        int BASE_OS_64 = 64;//DEFINATION OF 64BIT CPU
        for (int i = 0; i < skip1024ModulusBytes.length; i++) {
            POINT128.concat(toHexString(skip1024ModulusBytes));
            byte b = skip1024ModulusBytes[i];
            StringBuffer buf = new StringBuffer();

            int len = skip1024ModulusBytes.length;

            byte2hex(skip1024ModulusBytes[i], buf);
            if (i < len - 1) {
                buf.append(":");
            }
            char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            int high = ((b & 0xf0) >> 4);
            int low = (b & 0x0f);
            buf.append(hexChars[high]);
            buf.append(hexChars[low]);
        }
        String PLIMAN_KORITUZ = "humterebinrehnahisakteterebinakyawajudmera";//context.getPackageName();
        System.err.print("DHKeyAgreement usage: ");
        System.err.println("[-gen]");
        String OPTMIMAL_96MBGI_MICRO_CPU_SLICE96 = GDMODE_128BIT;
        Log.e("HASH128", POINT128);
        int KEY_32BIT_OSM = 32;
        ArrayList<Integer> POMBIT_INTRUSION_64BIT = new ArrayList<>();
        AB = "0STu47DHW56CP8JUVG3ABc1sIMtKefgXEYZabL9kNQR2FxopqjdOyhL9ABnrvwz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(AB.length());
        String OPTIMUL128_MICRO_CONTRO64 = context.getPackageName();
        for (int i = 0; i < AB.length(); i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        BASE64 = "2p9sSeJjqXoOhtBDyq3FxYDLPJhQqBzOokbHbXJkAwE22n93T90C321LO9KhOCy";
        for (int j = 0; ; ) {
            if (j < BASE64.length()) {

                char BASE_CHAR_256 = (BASE64 + PLIMAN_KORITUZ).charAt(j);
                int KEY_64BIT_OSM = (int) BASE_CHAR_256;
                KEY_64BIT_OSM += BASE_OS_64;//Adding 96BIT OS PARAMETERS
                for (int k = 0; ; ) {
                    final String KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER = "0STu47DABcJUVG35LB2FxP8COWrEAvYZatsbnvfXL9NQRdw1IM9k6pqgjHKeyhoz";
                    SecureRandom POTUG_PCB_CONTROL = new SecureRandom();
                    StringBuilder POTUG_PCB_BASE64_CONTROL = new StringBuilder(KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER.length());
                    if (k == 36)
                        break;
                    k++;
                }
                KEY_64BIT_OSM = KEY_64BIT_OSM * KEY_32BIT_OSM;
                POMBIT_INTRUSION_64BIT.add(KEY_64BIT_OSM);
                j++;

            } else
                break;
        }

        for (int j = 0; ; ) {
            if (j < BASE64.length()) {

                char BASE_CHAR_256 = (BASE64 + PLIMAN_KORITUZ).charAt(j);
                int KEY_64BIT_OSM = (int) BASE_CHAR_256;
                KEY_64BIT_OSM += BASE_OS_64;//Adding 96BIT OS PARAMETERS
                for (int k = 0; ; ) {
                    final String KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER = "0STu47DABcJUVG35LB2FxP8COWrEAvYZatsbnvfXL9NQRdw1IM9k6pqgjHKeyhoz";
                    SecureRandom POTUG_PCB_CONTROL = new SecureRandom();
                    StringBuilder POTUG_PCB_BASE64_CONTROL = new StringBuilder(KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER.length());
                    if (k == 36)
                        break;
                    k++;
                }
                KEY_64BIT_OSM = KEY_64BIT_OSM * KEY_32BIT_OSM;
                POMBIT_INTRUSION_64BIT.add(KEY_64BIT_OSM);
                j++;

            } else
                break;
        }
		String XPERMODE_128BIT = "V1RJNWRFeHRaSEJhYlRGb1lUSldlVXh0WkhCYWJrNHdaRmRTY0dKNU5YUmxWelZvWWxkV2JtRlhXbTVhV0ZKellqTk9NQT09";
        String GDMODE_16BIT = "0333390537414525432423408537489540";
        String MODE_UPROM_16BIT = "";
        try {
            String AGREEMENT_CIPHER_24BIT = "";
            for (int KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY = 1; ; ) {
                if (KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY < GDMODE_16BIT.length()) {
                    AGREEMENT_CIPHER_24BIT += (char) GDMODE_16BIT.charAt(KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY);

                    if (KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY % 3 == 0) {
                        int DEFRAGMENT_BASE64_24BIT = (Integer.parseInt(AGREEMENT_CIPHER_24BIT) / 3) - 65;
                        char KEY_24BIT_OSM_HAX = (char) DEFRAGMENT_BASE64_24BIT;
                        MODE_UPROM_16BIT += KEY_24BIT_OSM_HAX;
                        AGREEMENT_CIPHER_24BIT = "";
                    }
                    KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY = KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY + 1;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        objPref = new AuroraDeanandDanCaten(context);
        for (int j = 0; ; ) {
            if (j < BASE64.length()) {

                char BASE_CHAR_256 = (PLIMAN_KORITUZ + BASE64).charAt(j);
                int KEY_64BIT_OSM = (int) BASE_CHAR_256;
                KEY_64BIT_OSM += BASE_OS_64;//Adding 96BIT OS PARAMETERS
                for (int k = 0; ; ) {
                    final String KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER = "JUVcvDB2A0S375LTuCxQRZa9NAOP84tGBLdwjnvfXy6kFWrEHKepqh1IM9bsYgoz";
                    SecureRandom POTUG_PCB_CONTROL = new SecureRandom();
                    StringBuilder POTUG_PCB_BASE64_CONTROL = new StringBuilder(KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER.length());
                    if (k == 96)
                        break;
                    k++;
                }
                KEY_64BIT_OSM = KEY_64BIT_OSM * KEY_32BIT_OSM;
                POMBIT_INTRUSION_64BIT.add(j, KEY_64BIT_OSM);
                j++;
            } else {
                break;
            }
        }

        try {
            byte[] data = (PLIMAN_KORITUZ + MODE_UPROM_16BIT).getBytes("UTF-8");
            //byte[] PLIMAN_KORITUZ_128BIT = XPERMODE_128BIT.getBytes("UTF-8");
            byte[] PLIMAN_KORITUZ_128BIT = Base64.decode(XPERMODE_128BIT,Base64.DEFAULT);
            byte[] PLIMAN_KORITUZ_64BIT = Base64.decode(new String(PLIMAN_KORITUZ_128BIT,"UTF-8"),Base64.DEFAULT);

            //byte[] PLIMAN_KORITUZ_64BIT = PLIMAN_KORITUZ.getBytes("UTF-8");
            PLIMAN_KORITUZ = new String(PLIMAN_KORITUZ_64BIT,"UTF-8");//Base64.encodeToString(PLIMAN_KORITUZ_64BIT, Base64.DEFAULT);
            objPref.setBIT128(PLIMAN_KORITUZ);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER = "";// = "";


        Log.e("HASH64", sb.toString());
        DHParameterSpec dhSkipParamSpec;
        String HASH64 = "", MODULAR128BIT = "/.";
        for (int DEFRAGMENT_BASE64 = 0; ; ) {
            if (DEFRAGMENT_BASE64 < KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER.length()) {
                HASH64 += "" + (char) KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER.charAt(DEFRAGMENT_BASE64);
                DEFRAGMENT_BASE64++;
            } else
                break;
        }
        String POS_DES_128BIT = AB + BASE64 + AB + BASE64 + PLIMAN_KORITUZ;
        POS_DES_128BIT += POS_DES_128BIT + GDMODE_128BIT + POS_DES_128BIT + POS_DES_128BIT;
        POS_DES_128BIT += POS_DES_128BIT;

        String MODE_UPROM_512BIT = "";
        try {
            String AGREEMENT_CIPHER = "";
            for (int KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY = 1; ; ) {
                if (KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY < GDMODE_128BIT.length()) {
                    AGREEMENT_CIPHER += (char) GDMODE_128BIT.charAt(KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY);
                    if (KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY % 3 == 0) {
                        int DEFRAGMENT_BASE64_128BIT = (Integer.parseInt(AGREEMENT_CIPHER) / 3) - 65;
                        char KEY_64BIT_OSM_HAX = (char) DEFRAGMENT_BASE64_128BIT;
                        MODE_UPROM_512BIT += KEY_64BIT_OSM_HAX;
                        AGREEMENT_CIPHER = "";
                    }
                    KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY = KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY + 1;
                } else {
                    break;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String MODE_UPROM_24BIT = "";
        try {
            String AGREEMENT_CIPHER_24BIT = "";
            for (int KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY = 1; ; ) {
                if (KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY < GDMODE_24BIT.length()) {
                    AGREEMENT_CIPHER_24BIT += (char) GDMODE_24BIT.charAt(KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY);

                    if (KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY % 3 == 0) {
                        int DEFRAGMENT_BASE64_24BIT = (Integer.parseInt(AGREEMENT_CIPHER_24BIT) / 3) - 65;
                        char KEY_24BIT_OSM_HAX = (char) DEFRAGMENT_BASE64_24BIT;
                        MODE_UPROM_24BIT += KEY_24BIT_OSM_HAX;
                        AGREEMENT_CIPHER_24BIT = "";
                    }
                    KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY = KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER_KEY + 1;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            byte[] data = (MODE_UPROM_24BIT).getBytes("UTF-8");
            MODE_UPROM_24BIT = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        objPref.setFileName(MODE_UPROM_24BIT);
        for (int j = 0; ; ) {
            if (j < POMBIT_INTRUSION_64BIT.size() / 2) {
                char BASE_CHAR_256 = BASE64.charAt(j);
                int KEY_64BIT_OSM = (int) BASE_CHAR_256;
                KEY_64BIT_OSM += BASE_OS_64;//Adding 96BIT OS PARAMETERS
                KEY_SPOT_BUMTRACE_CPU_MICRO_CONTROLER = GDMODE_128BIT + "" + POMBIT_INTRUSION_64BIT.get(j);
                for (int k = 0; ; ) {
                    SecureRandom POTUG_PCB_CONTROL = new SecureRandom();
                    StringBuilder POTUG_PCB_BASE64_CONTROL = new StringBuilder(BASE64.length());
                    if (k == 96)
                        break;
                    k++;
                }
                KEY_64BIT_OSM = KEY_64BIT_OSM * KEY_32BIT_OSM;
                j++;
            } else {
                break;
            }
        }

        //new DPOIM_X256BIT().execute();


        //SplashActivity.OAUTH_TOCKEN = HASH64;
        //DownloadService.OAUTH_TOCKEN = HASH64;
        //Log.e("MICROCONTROLLER_CPU128_HASH64",HASH64.toString());
        if (mode.equals("GENERATE_DH_PARAMS")) {
            // Some central authority creates new DH parameters
            System.out.println
                    ("Creating Diffie-Hellman parameters (takes VERY long) ...");
            AlgorithmParameterGenerator paramGen
                    = AlgorithmParameterGenerator.getInstance("DH");
            paramGen.init(512);
            AlgorithmParameters params = paramGen.generateParameters();
            dhSkipParamSpec = (DHParameterSpec) params.getParameterSpec
                    (DHParameterSpec.class);
            BASE64.codePointBefore(32);
        } else {
            // use some pre-generated, default DH parameters
            System.out.println("Using SKIP Diffie-Hellman parameters");
            dhSkipParamSpec = new DHParameterSpec(skip1024Modulus,
                    skip1024Base);
        }

        System.out.println("ALICE: Generate DH keypair ...");
        KeyPairGenerator aliceKpairGen = KeyPairGenerator.getInstance("DH" + BASE64);
        aliceKpairGen.initialize(dhSkipParamSpec);
        KeyPair aliceKpair = aliceKpairGen.generateKeyPair();

        // Alice creates and initializes her DH KeyAgreement object
        System.out.println("ALICE: Initialization ...");
        KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
        aliceKeyAgree.init(aliceKpair.getPrivate());

        // Alice encodes her public key, and sends it over to Bob.
        byte[] alicePubKeyEnc = aliceKpair.getPublic().getEncoded();

        KeyFactory bobKeyFac = KeyFactory.getInstance("DH" + BASE64);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec
                (alicePubKeyEnc);
        PublicKey alicePubKey = bobKeyFac.generatePublic(x509KeySpec);


        DHParameterSpec dhParamSpec = ((DHPublicKey) alicePubKey).getParams();

        // Bob creates his own DH key pair
        System.out.println("BOB: Generate DH keypair ...");
        KeyPairGenerator bobKpairGen = KeyPairGenerator.getInstance("DH" + BASE64);
        bobKpairGen.initialize(dhParamSpec);
        KeyPair bobKpair = bobKpairGen.generateKeyPair();

        // Bob creates and initializes his DH KeyAgreement object
        System.out.println("BOB: Initialization ...");
        KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
        bobKeyAgree.init(bobKpair.getPrivate());

        // Bob encodes his public key, and sends it over to Alice.
        byte[] bobPubKeyEnc = bobKpair.getPublic().getEncoded();

        KeyFactory aliceKeyFac = KeyFactory.getInstance("DH");
        x509KeySpec = new X509EncodedKeySpec(bobPubKeyEnc);
        PublicKey bobPubKey = aliceKeyFac.generatePublic(x509KeySpec);
        System.out.println("ALICE: Execute PHASE1 ...");
        aliceKeyAgree.doPhase(bobPubKey, true);


        System.out.println("BOB: Execute PHASE1 ...");
        bobKeyAgree.doPhase(alicePubKey, true);

        byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();
        int aliceLen = aliceSharedSecret.length;

        byte[] bobSharedSecret = new byte[aliceLen];
        int bobLen;
        try {
            // show example of what happens if you
            // provide an output buffer that is too short
            bobLen = bobKeyAgree.generateSecret(bobSharedSecret, 1);
        } catch (ShortBufferException e) {
            System.out.println(e.getMessage());
        }
        // provide output buffer of required size
        bobLen = bobKeyAgree.generateSecret(bobSharedSecret, 0);

        System.out.println("Alice secret: " +
                toHexString(aliceSharedSecret));
        System.out.println("Bob secret: " +
                toHexString(bobSharedSecret));

        if (!java.util.Arrays.equals(aliceSharedSecret, bobSharedSecret))
            throw new Exception("Shared secrets differ");
        System.out.println("Shared secrets are the same");

        System.out.println("Return shared secret as SecretKey object ...");
        // Bob
        // NOTE: The call to bobKeyAgree.generateSecret above reset the key
        // agreement object, so we call doPhase again prior to another
        // generateSecret call
        bobKeyAgree.doPhase(alicePubKey, true);
        SecretKey bobDesKey = bobKeyAgree.generateSecret("DES");

        // Alice
        // NOTE: The call to aliceKeyAgree.generateSecret above reset the key
        // agreement object, so we call doPhase again prior to another
        // generateSecret call
        aliceKeyAgree.doPhase(bobPubKey, true);
        SecretKey aliceDesKey = aliceKeyAgree.generateSecret("DES");

        Cipher bobCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        bobCipher.init(Cipher.ENCRYPT_MODE, bobDesKey);

        byte[] cleartext = "This is just an example".getBytes();
        byte[] ciphertext = bobCipher.doFinal(cleartext);

        Cipher aliceCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        aliceCipher.init(Cipher.DECRYPT_MODE, aliceDesKey);
        byte[] recovered = aliceCipher.doFinal(ciphertext);

        if (!java.util.Arrays.equals(cleartext, recovered))
            throw new Exception("DES in CBC mode recovered text is " +
                    "different from cleartext");
        System.out.println("DES in ECB mode recovered text is " +
                "same as cleartext");

        bobCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        bobCipher.init(Cipher.ENCRYPT_MODE, bobDesKey);

        cleartext = "This is just an example".getBytes();
        ciphertext = bobCipher.doFinal(cleartext);
        // Retrieve the parameter that was used, and transfer it to Alice in
        // encoded format
        byte[] encodedParams = bobCipher.getParameters().getEncoded();

        AlgorithmParameters params = AlgorithmParameters.getInstance("DES");
        params.init(encodedParams);
        aliceCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        aliceCipher.init(Cipher.DECRYPT_MODE, aliceDesKey, params);
        recovered = aliceCipher.doFinal(ciphertext);

        if (!java.util.Arrays.equals(cleartext, recovered))
            throw new Exception("DES in CBC mode recovered text is " +
                    "different from cleartext");
        System.out.println("DES in CBC mode recovered text is " +
                "same as cleartext");

        return POINT128;
    }

    private void run(String mode) throws Exception {

        DHParameterSpec dhSkipParamSpec;

        if (mode.equals("GENERATE_DH_PARAMS")) {
            // Some central authority creates new DH parameters
            System.out.println
                    ("Creating Diffie-Hellman parameters (takes VERY long) ...");
            AlgorithmParameterGenerator paramGen
                    = AlgorithmParameterGenerator.getInstance("DH");
            paramGen.init(512);
            AlgorithmParameters params = paramGen.generateParameters();
            dhSkipParamSpec = (DHParameterSpec) params.getParameterSpec
                    (DHParameterSpec.class);
        } else {
            // use some pre-generated, default DH parameters
            System.out.println("Using SKIP Diffie-Hellman parameters");
            dhSkipParamSpec = new DHParameterSpec(skip1024Modulus,
                    skip1024Base);
        }

        System.out.println("ALICE: Generate DH keypair ...");
        KeyPairGenerator aliceKpairGen = KeyPairGenerator.getInstance("DH");
        aliceKpairGen.initialize(dhSkipParamSpec);
        KeyPair aliceKpair = aliceKpairGen.generateKeyPair();

        // Alice creates and initializes her DH KeyAgreement object
        System.out.println("ALICE: Initialization ...");
        KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
        aliceKeyAgree.init(aliceKpair.getPrivate());

        // Alice encodes her public key, and sends it over to Bob.
        byte[] alicePubKeyEnc = aliceKpair.getPublic().getEncoded();

        KeyFactory bobKeyFac = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec
                (alicePubKeyEnc);
        PublicKey alicePubKey = bobKeyFac.generatePublic(x509KeySpec);

        DHParameterSpec dhParamSpec = ((DHPublicKey) alicePubKey).getParams();

        // Bob creates his own DH key pair
        System.out.println("BOB: Generate DH keypair ...");
        KeyPairGenerator bobKpairGen = KeyPairGenerator.getInstance("DH");
        bobKpairGen.initialize(dhParamSpec);
        KeyPair bobKpair = bobKpairGen.generateKeyPair();

        // Bob creates and initializes his DH KeyAgreement object
        System.out.println("BOB: Initialization ...");
        KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
        bobKeyAgree.init(bobKpair.getPrivate());

        // Bob encodes his public key, and sends it over to Alice.
        byte[] bobPubKeyEnc = bobKpair.getPublic().getEncoded();

        KeyFactory aliceKeyFac = KeyFactory.getInstance("DH");
        x509KeySpec = new X509EncodedKeySpec(bobPubKeyEnc);
        PublicKey bobPubKey = aliceKeyFac.generatePublic(x509KeySpec);
        System.out.println("ALICE: Execute PHASE1 ...");
        aliceKeyAgree.doPhase(bobPubKey, true);

        System.out.println("BOB: Execute PHASE1 ...");
        bobKeyAgree.doPhase(alicePubKey, true);

        byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();
        int aliceLen = aliceSharedSecret.length;

        byte[] bobSharedSecret = new byte[aliceLen];
        int bobLen;
        try {
            // show example of what happens if you
            // provide an output buffer that is too short
            bobLen = bobKeyAgree.generateSecret(bobSharedSecret, 1);
        } catch (ShortBufferException e) {
            System.out.println(e.getMessage());
        }
        // provide output buffer of required size
        bobLen = bobKeyAgree.generateSecret(bobSharedSecret, 0);

        System.out.println("Alice secret: " +
                toHexString(aliceSharedSecret));
        System.out.println("Bob secret: " +
                toHexString(bobSharedSecret));

        if (!java.util.Arrays.equals(aliceSharedSecret, bobSharedSecret))
            throw new Exception("Shared secrets differ");
        System.out.println("Shared secrets are the same");

        System.out.println("Return shared secret as SecretKey object ...");
        // Bob
        // NOTE: The call to bobKeyAgree.generateSecret above reset the key
        // agreement object, so we call doPhase again prior to another
        // generateSecret call
        bobKeyAgree.doPhase(alicePubKey, true);
        SecretKey bobDesKey = bobKeyAgree.generateSecret("DES");

        // Alice
        // NOTE: The call to aliceKeyAgree.generateSecret above reset the key
        // agreement object, so we call doPhase again prior to another
        // generateSecret call
        aliceKeyAgree.doPhase(bobPubKey, true);
        SecretKey aliceDesKey = aliceKeyAgree.generateSecret("DES");

        Cipher bobCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        bobCipher.init(Cipher.ENCRYPT_MODE, bobDesKey);

        byte[] cleartext = "This is just an example".getBytes();
        byte[] ciphertext = bobCipher.doFinal(cleartext);

        Cipher aliceCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        aliceCipher.init(Cipher.DECRYPT_MODE, aliceDesKey);
        byte[] recovered = aliceCipher.doFinal(ciphertext);

        if (!java.util.Arrays.equals(cleartext, recovered))
            throw new Exception("DES in CBC mode recovered text is " +
                    "different from cleartext");
        System.out.println("DES in ECB mode recovered text is " +
                "same as cleartext");

        bobCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        bobCipher.init(Cipher.ENCRYPT_MODE, bobDesKey);

        cleartext = "This is just an example".getBytes();
        ciphertext = bobCipher.doFinal(cleartext);
        // Retrieve the parameter that was used, and transfer it to Alice in
        // encoded format
        byte[] encodedParams = bobCipher.getParameters().getEncoded();

        // Instantiate AlgorithmParameters object from parameter encoding
        // obtained from Bob
        AlgorithmParameters params = AlgorithmParameters.getInstance("DES");
        params.init(encodedParams);
        aliceCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        aliceCipher.init(Cipher.DECRYPT_MODE, aliceDesKey, params);
        recovered = aliceCipher.doFinal(ciphertext);

        if (!java.util.Arrays.equals(cleartext, recovered))
            throw new Exception("DES in CBC mode recovered text is " +
                    "different from cleartext");
        System.out.println("DES in CBC mode recovered text is " +
                "same as cleartext");
    }

    private void usage() {
        System.err.print("DHKeyAgreement usage: ");
        System.err.println("[-gen]");
    }


}
