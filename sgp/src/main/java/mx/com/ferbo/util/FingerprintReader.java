import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.swing.JOptionPane;

import com.digitalpersona.uareu.Engine;
import com.digitalpersona.uareu.Fid;
import com.digitalpersona.uareu.Fmd;
import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.ReaderCollection;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;

public class FingerprintReader {

    private static Reader reader = null;
    private static Fmd fmd = null;

    public static void main(String[] args) {
        try {
            ReaderCollection readers = UareUGlobal.GetReaders();
            if (readers.size() <= 0) {
                throw new UareUException(0xffffffff, "No se encontraron lectores");
            }
            reader = readers.get(0);
            reader.Open(Reader.Priority.COOPERATIVE);

            Engine engine = UareUGlobal.GetEngine();

            while (true) {
                try {
                    fmd = engine.CreateFmd(
                            reader.Capture(Fid.Format.ANSI_381_2004,
                                    Reader.ImageProcessing.IMAGE_PROCESSING_DEFAULT, 500,
                                    reader.GetMaxDiscardedImages()),
                            Fmd.Format.ANSI_381_2004);
                } catch (UareUException e) {
                    throw e;
                }

                JOptionPane.showMessageDialog(null, "Huella capturada con éxito");
                break;
            }

        } catch (UareUException e) {
            JOptionPane.showMessageDialog(null, "Error al capturar huella: " + e.toString());
        } finally {
            try {
                if (reader != null)
                    reader.Close();
            } catch (UareUException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el lector: " + e.toString());
            }
        }
    }

}
