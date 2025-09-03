import com.pooya.packagers.ISO93HOSTPackager;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;

public class IsoPackUnpackTest {
  public static void main(String[] args) throws ISOException {
    // Create ISO message and set packager
    ISOMsg m = new ISOMsg();
    m.setPackager(new ISO93HOSTPackager());

    // Set MTI and some fields
    m.setMTI("0200"); // financial transaction request
    m.set(2, "4123456789012345"); // PAN
    m.set(3, "000000"); // Processing code
    m.set(4, "000000010000"); // Amount 100.00 (12 numeric, no decimal)
    m.set(11, "123456"); // STAN
    m.set(41, "TERMID01"); // Terminal ID
    m.set(42, "MERCHID00000001"); // Merchant ID
    m.set(49, "840"); // Currency code USD

    // Pack the message
    byte[] packed = m.pack();

    // Print packed bytes as hex
    System.out.println("Packed (hex): " + ISOUtil.hexString(packed));

    // Now unpack into a new ISOMsg and dump
    ISOMsg m2 = new ISOMsg();
    m2.setPackager(new ISO93HOSTPackager());
    m2.unpack(packed);

    System.out.println("\nUnpacked (dump):");
    m2.dump(System.out, "");
  }
}
