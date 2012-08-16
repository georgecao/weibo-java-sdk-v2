package weibo4j.model;

import com.sun.imageio.plugins.bmp.BMPImageReader;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import com.sun.imageio.plugins.png.PNGImageReader;
import weibo4j.util.CloseUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 临时存储上传图片的内容，格式，文件信息等
 */
public class ImageItem {
    private static final String GIF_TYPE = "image/gif";
    private static final String JPG_TYPE = "image/jpeg";
    private static final String PNG_TYPE = "image/png";
    private static final String BMP_TYPE = "application/x-bmp";
    private byte[] content;
    private String name;
    private String contentType;
    private static Set<String> supportedImgTypes;

    static {
        HashSet<String> types = new HashSet<String>();
        types.add(GIF_TYPE);
        types.add(JPG_TYPE);
        types.add(PNG_TYPE);
        types.add(BMP_TYPE);
        supportedImgTypes = Collections.unmodifiableSet(types);
    }

    public ImageItem(byte[] content) throws WeiboException {
        this(Constants.UPLOAD_MODE, content);
    }

    private boolean isSupported(String imgType) {
        return (null != imgType && supportedImgTypes.contains(imgType));
    }

    private ImageItem(String name, byte[] content) throws WeiboException {
        String imgType = getContentType(content);
        if (isSupported(imgType)) {
            this.content = content;
            this.name = name;
            this.contentType = imgType;
        } else {
            throw new WeiboException("Unsupported image type " + imgType + ", Only Support JPG ,GIF,PNG!");
        }
    }

    public byte[] getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public static String getContentType(byte[] mapObj) {
        String type = "";
        ByteArrayInputStream byteArray = null;
        MemoryCacheImageInputStream stream = null;
        try {
            byteArray = new ByteArrayInputStream(mapObj);
            stream = new MemoryCacheImageInputStream(byteArray);
            Iterator itr = ImageIO.getImageReaders(stream);
            while (itr.hasNext()) {
                ImageReader reader = (ImageReader) itr.next();
                if (reader instanceof GIFImageReader) {
                    type = GIF_TYPE;
                } else if (reader instanceof JPEGImageReader) {
                    type = JPG_TYPE;
                } else if (reader instanceof PNGImageReader) {
                    type = PNG_TYPE;
                } else if (reader instanceof BMPImageReader) {
                    type = BMP_TYPE;
                }
            }
        } finally {
            CloseUtils.close(byteArray);
            CloseUtils.close(stream);
        }
        return type;
    }
}
