package weibo4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.stream.ImageInputStream;
import java.io.Closeable;
import java.io.IOException;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-8-16
 * Time: 下午3:46
 * </pre>
 */
public class CloseUtils {
    private static final Logger LOG = LoggerFactory.getLogger(CloseUtils.class);
    private static final boolean debug = LOG.isDebugEnabled();

    public static void close(Closeable resource) {
        if (null != resource) {
            try {
                resource.close();
            } catch (IOException e) {
                LOG.warn("Close resource error:", e);
            }
        }
    }

    public static void close(ImageInputStream resource) {
        if (null != resource) {
            try {
                resource.close();
            } catch (IOException e) {
                LOG.warn("Close resource error:", e);
            }
        }
    }

}
