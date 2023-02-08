package mx.com.ferbo.util;

/*public class FingerprintController {
    
}*/
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.digitalpersona.uareu.Engine;
import com.digitalpersona.uareu.Fid;
import com.digitalpersona.uareu.Fmd;
import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.ReaderCollection;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;

@Named
@ViewScoped
public class FingerprintController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Reader reader;
    private Fmd fmd;

    @PostConstruct
    public void init() {
        try {
            ReaderCollection readers = UareUGlobal.GetReaders();
            if (readers.size() <= 0) {
                throw new UareUException(0xffffffff, "No se encontraron lectores");
            }
            reader = readers.get(0);
            reader.Open(Reader.Priority.COOPERATIVE);
        } catch (UareUException e) {
            // manejar el error
        }
    }

    public void captureFingerprint() {
        try {
            Engine engine = UareUGlobal.GetEngine();

            fmd = engine.CreateFmd(
                    reader.Capture(Fid.Format.ANSI_381_2004,
                            Reader.ImageProcessing.IMAGE_PROCESSING_DEFAULT, 500,
                            reader.GetMaxDiscardedImages()),
                    Fmd.Format.ANSI_381_2004);

            // procesar la huella capturada
        } catch (UareUException e) {
            // manejar el error
        }
    }

    public void closeReader() {
        try {
            if (reader != null)
                reader.Close();
        } catch (UareUException e) {
            // manejar el error
        }
    }

}
