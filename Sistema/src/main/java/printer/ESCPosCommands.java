package printer;

public class ESCPosCommands {

    public static final byte[] INIT = {0x1B, 0x40};

    public static final byte[] NEGRITA_ON = {0x1B, 0x45, 0x01};
    public static final byte[] NEGRITA_OFF = {0x1B, 0x45, 0x00};

    public static final byte[] CENTRAR = {0x1B, 0x61, 0x01};
    public static final byte[] IZQUIERDA = {0x1B, 0x61, 0x00};

    public static final byte[] CORTE = {0x1D, 0x56, 0x41, 0x10};
}